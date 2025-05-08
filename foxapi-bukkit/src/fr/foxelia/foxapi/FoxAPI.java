package fr.foxelia.foxapi;

import fr.foxelia.foxapi.gui.GUIListener;
import fr.foxelia.foxapi.listeners.cooldown.CooldownMoveListener;
import fr.foxelia.foxapi.listeners.cooldown.CooldownTakeDamageListener;
import fr.foxelia.foxapi.test.TestListener;
import fr.foxelia.foxapi.test.TestUser;
import fr.foxelia.tools.minecraft.bukkit.cooldown.CooldownType;
import fr.foxelia.tools.minecraft.bukkit.datas.database.DatabaseManager;
import fr.foxelia.tools.minecraft.bukkit.datas.database.EntityManager;
import fr.foxelia.tools.minecraft.bukkit.datas.player.PlayerFiles;
import fr.foxelia.tools.minecraft.bukkit.datas.uuid.UUIDFetcher;
import fr.foxelia.tools.minecraft.bukkit.ui.console.color.ColoredConsole;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;

public class FoxAPI extends JavaPlugin {
    private static FoxAPI instance;
    private static DatabaseManager databaseManager;
    public static EntityManager<TestUser> testUserManager;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().log(Level.INFO, ColoredConsole.DARK_GREEN + "======" + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_GREEN + getDescription().getName() + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_GREEN + "Initializing..." + ColoredConsole.RESET);
        getLogger().log(Level.INFO, "");
        getLogger().log(Level.INFO, ColoredConsole.DARK_GREEN + "Version " + getDescription().getVersion() + ColoredConsole.RESET);
        getLogger().log(Level.INFO, "");
        getLogger().log(Level.INFO, ColoredConsole.YELLOW + "By " + getDescription().getAuthors().toString().replace("[", "").replace("]", "") + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_GREEN + "======");

        databaseManager = new DatabaseManager();
        databaseManager.connect(
                getConfig().getString("database.type"),
                getConfig().getString("database.host"),
                getConfig().getInt("database.port"),
                getConfig().getString("database.name"),
                getConfig().getString("database.username"),
                getConfig().getString("database.password")
        );

        testUserManager = new EntityManager<>(databaseManager.getConnection(), false);
        try {
            testUserManager.createTable(TestUser.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            testUserManager.loadAll(TestUser.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }

        testUserManager.startSyncTask(this, TestUser.class, 20);

        getServer().getPluginManager().registerEvents(new TestListener(), this);

        //new UUIDFetcher(new File(PlayerFiles.getPublicFolder(FoxAPI.getInstance()), "uuidfetcher.yml"));

        getServer().getPluginManager().registerEvents(new GUIListener(), this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().log(Level.INFO, ColoredConsole.DARK_RED + "======" + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_RED + getDescription().getName() + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_RED + "Disabling..." + ColoredConsole.RESET);
        getLogger().log(Level.INFO, "");
        getLogger().log(Level.INFO, ColoredConsole.DARK_RED + "Version " + getDescription().getVersion() + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_RED + "Thanks for using FoxAPI!" + ColoredConsole.RESET);
        getLogger().log(Level.INFO, "");
        getLogger().log(Level.INFO, ColoredConsole.YELLOW + "By " + getDescription().getAuthors().toString().replace("[", "").replace("]", "") + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_RED + "======" + ColoredConsole.RESET);

        UUIDFetcher.getInstance().saveConfig();
    }

    public static FoxAPI getInstance() {
        return instance;
    }

    private Set<CooldownType> hooks = new HashSet<>();

    public void hookCooldown(CooldownType... cooldown) {
        for(CooldownType type : cooldown) {
            if(hooks.contains(type)) continue;
            if(type.equals(CooldownType.GLOBAL)) {
                CooldownType[] values = Arrays.stream(CooldownType.values())
                        .filter(t -> !t.equals(CooldownType.GLOBAL))
                        .toArray(CooldownType[]::new);
                for(CooldownType value : values) {
                    hookCooldown(value);
                }
                break;
            }
            hooks.add(type);
            switch (type) {
                case MOVE -> getServer().getPluginManager().registerEvents(new CooldownMoveListener(), this);
                case TAKE_DAMAGE -> getServer().getPluginManager().registerEvents(new CooldownTakeDamageListener(), this);
            }
        }
    }
}
