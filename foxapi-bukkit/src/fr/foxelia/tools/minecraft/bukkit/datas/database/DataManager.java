package fr.foxelia.tools.minecraft.bukkit.datas.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataManager<T> {
    private final Map<String, T> cache = new ConcurrentHashMap<>();
    private final List<DataListener<T>> listeners = new ArrayList<>();

    public void addListener(DataListener<T> listener) {
        listeners.add(listener);
    }

    public void loadDataFromDatabase() {
        // Charger les données depuis MariaDB dans le cache
    }

    public void set(String key, T value) {
        T old = cache.put(key, value);
        notifyListeners(key, old, value);
        saveToDatabase(key, value);
    }

    public T get(String key) {
        return cache.get(key);
    }

    private void notifyListeners(String key, T oldVal, T newVal) {
        for (DataListener<T> listener : listeners) {
            listener.onDataChanged(key, oldVal, newVal);
        }
    }

    private void saveToDatabase(String key, T value) {
        // Requête UPDATE ou INSERT dans MariaDB
    }
}

