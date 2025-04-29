package fr.foxelia.tools.minecraft.bukkit.datas.database;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class EntityManager<T> {

    private final Connection connection;
    private final boolean fallback;
    private final List<DataUpdateListener<T>> listeners = new ArrayList<>();
    private final Map<String, T> instanceCache = new HashMap<>();

    public EntityManager(Connection connection, boolean fallback) {
        this.connection = connection;
        this.fallback = fallback;
    }

    public void addListener(DataUpdateListener<T> listener) {
        listeners.add(listener);
    }

    public void createTable(Class<T> clazz) throws SQLException {
        if (!clazz.isAnnotationPresent(Table.class)) return;

        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        Table table = clazz.getAnnotation(Table.class);
        sb.append(table.name()).append(" (");

        List<String> columns = getStrings(clazz);

        sb.append(String.join(", ", columns)).append(");");

        if (!fallback && connection != null) {
            try (Statement stmt = connection.createStatement()) {
                System.out.println("Creating table with SQL: " + sb);
                stmt.execute(sb.toString());
            }
        }
    }

    private @NotNull List<String> getStrings(Class<T> clazz) {
        List<String> columns = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Column.class)) continue;

            Column col = field.getAnnotation(Column.class);
            String name = col.name();
            String type = sqlTypeForField(field);
            String def = name + " " + type;

            if (field.isAnnotationPresent(PrimaryKey.class)) {
                def += " PRIMARY KEY";
            }

            columns.add(def);
        }
        return columns;
    }

    private String sqlTypeForField(Field field) {
        Class<?> type = field.getType();
        if (type == String.class) return "VARCHAR(255)";
        if (type == int.class || type == Integer.class) return "INT";
        if (type == long.class || type == Long.class) return "BIGINT";
        if (type == boolean.class || type == Boolean.class) return "BOOLEAN";
        // Ajoute d'autres types ici si besoin
        return "TEXT";
    }

    public void save(T entity) throws IllegalAccessException, SQLException {
        Class<?> clazz = entity.getClass();
        if (!clazz.isAnnotationPresent(Table.class)) return;

        Table table = clazz.getAnnotation(Table.class);
        Map<String, Object> values = new LinkedHashMap<>();
        String keyName = null;
        Object keyValue = null;

        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Column.class)) continue;
            Column column = field.getAnnotation(Column.class);
            field.setAccessible(true);
            values.put(column.name(), field.get(entity));

            if (field.isAnnotationPresent(PrimaryKey.class)) {
                keyName = column.name();
                keyValue = field.get(entity);
            }
        }

        if (fallback || connection == null) {
            instanceCache.put(keyValue.toString(), entity);
            return;
        }

        String columns = String.join(", ", values.keySet());
        String placeholders = String.join(", ", Collections.nCopies(values.size(), "?"));
        String updates = String.join(", ", values.keySet().stream().map(k -> k + " = VALUES(" + k + ")").toList());

        String sql = "INSERT INTO " + table.name() + " (" + columns + ") VALUES (" + placeholders + ") " +
                "ON DUPLICATE KEY UPDATE " + updates;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            int i = 1;
            for (Object v : values.values()) {
                stmt.setObject(i++, v);
            }
            stmt.executeUpdate();
        }

    }

    public void updateField(T entity, String fieldName, Object newValue) {
        try {
            Field field = entity.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object oldValue = field.get(entity);
            field.set(entity, newValue);

            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if (column.sync()) {
                    save(entity);
                    for (DataUpdateListener<T> listener : listeners) {
                        listener.onUpdate(entity, fieldName, oldValue, newValue);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<T> loadAll(Class<T> clazz) throws SQLException, ReflectiveOperationException {
        if (!clazz.isAnnotationPresent(Table.class)) return Collections.emptyList();

        Table table = clazz.getAnnotation(Table.class);
        String sql = "SELECT * FROM " + table.name();

        List<T> result = new ArrayList<>();

        if (fallback || connection == null) {
            return new ArrayList<>(instanceCache.values());
        }

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                T instance = clazz.getDeclaredConstructor().newInstance();

                for (Field field : clazz.getDeclaredFields()) {
                    if (!field.isAnnotationPresent(Column.class)) continue;
                    Column column = field.getAnnotation(Column.class);
                    field.setAccessible(true);
                    Object value = rs.getObject(column.name());
                    field.set(instance, value);
                }

                // Remplir aussi le fallbackCache
                String key = getPrimaryKeyValue(instance, clazz);
                instanceCache.put(key, instance);
                result.add(instance);
            }
        }

        return result;
    }

    private String getPrimaryKeyValue(T instance, Class<?> clazz) throws IllegalAccessException {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                field.setAccessible(true);
                Object value = field.get(instance);
                return String.valueOf(value);
            }
        }
        return null;
    }

    public T getOrLoad(String id, Class<T> clazz) {
        if (instanceCache.containsKey(id)) {
            return instanceCache.get(id);
        }

        try {
            // load single from DB
            String tableName = clazz.getAnnotation(Table.class).name();
            Field pkField = Arrays.stream(clazz.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(PrimaryKey.class))
                    .findFirst().orElseThrow();

            String pkColumn = pkField.getAnnotation(Column.class).name();

            String sql = "SELECT * FROM " + tableName + " WHERE " + pkColumn + " = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setObject(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    T instance = clazz.getDeclaredConstructor().newInstance();
                    for (Field field : clazz.getDeclaredFields()) {
                        if (!field.isAnnotationPresent(Column.class)) continue;
                        field.setAccessible(true);
                        Column column = field.getAnnotation(Column.class);
                        Object value = rs.getObject(column.name());
                        field.set(instance, value);
                    }
                    instanceCache.put(id, instance);
                    return instance;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public T getOrReload(String id, Class<T> clazz) {
        try {
            String tableName = clazz.getAnnotation(Table.class).name();
            Field pkField = Arrays.stream(clazz.getDeclaredFields())
                    .filter(f -> f.isAnnotationPresent(PrimaryKey.class))
                    .findFirst().orElseThrow();

            String pkColumn = pkField.getAnnotation(Column.class).name();

            String sql = "SELECT * FROM " + tableName + " WHERE " + pkColumn + " = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setObject(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    T instance = clazz.getDeclaredConstructor().newInstance();
                    for (Field field : clazz.getDeclaredFields()) {
                        if (!field.isAnnotationPresent(Column.class)) continue;
                        field.setAccessible(true);
                        Column column = field.getAnnotation(Column.class);
                        Object value = rs.getObject(column.name());
                        field.set(instance, value);
                    }
                    instanceCache.put(id, instance); // update cache
                    return instance;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void startSyncTask(JavaPlugin plugin, Class<T> clazz, long intervalTicks) {
        if (fallback || connection == null) return;

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    for (Map.Entry<String, T> entry : instanceCache.entrySet()) {
                        String id = entry.getKey();
                        T currentInstance = entry.getValue();

                        T latest = getOrReload(id, clazz);
                        if (latest == null) continue;

                        for (Field field : clazz.getDeclaredFields()) {
                            if (!field.isAnnotationPresent(Column.class)) continue;
                            field.setAccessible(true);
                            Object oldVal = field.get(currentInstance);
                            Object newVal = field.get(latest);

                            if (!Objects.equals(oldVal, newVal)) {
                                field.set(currentInstance, newVal);
                                for (DataUpdateListener<T> listener : listeners) {
                                    listener.onUpdate(currentInstance, field.getName(), oldVal, newVal);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.runTaskTimerAsynchronously(plugin, intervalTicks, intervalTicks);
    }
}
