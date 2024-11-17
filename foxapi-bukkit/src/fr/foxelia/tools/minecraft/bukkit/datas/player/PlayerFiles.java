package fr.foxelia.tools.minecraft.bukkit.datas.player;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class PlayerFiles {

    // Constantes
    private static final String DEFAULT_CONFIG_FOLDER = "fr/foxelia/foxapi/utilites/datas/defaults";

    private static Map<UUID, PlayerFiles> playerFilesCache = new HashMap<>();

    private final JavaPlugin plugin;
    private final UUID uuid;
    private File playerFolder = null; // /plugins/JavaPlugin/players/UUID/
    private Map<String, FileConfiguration> configurationCache = new HashMap<>();

    // Constructeur
    private PlayerFiles(UUID uuid, JavaPlugin plugin) {
        this.uuid = uuid;
        this.plugin = plugin;
        playerFolder = new File(getPublicFolder(plugin), uuid.toString());
    }

    // Méthodes statiques

    /**
     * Retrieve a player folder from an {@link UUID}.
     *
     * @param uuid The {@link UUID} of the player
     * @param plugin The {@link JavaPlugin} instance
     * @return A {@link PlayerFiles} object to manage the data of the player
     */
    public static PlayerFiles getFilesOf(UUID uuid, JavaPlugin plugin) {
        if (playerFilesCache.containsKey(uuid)) {
            return playerFilesCache.get(uuid);
        } else {
            PlayerFiles playerFiles = new PlayerFiles(uuid, plugin);
            playerFilesCache.put(uuid, playerFiles);
            return playerFiles;
        }
    }

    /**
     * Retrieve a player folder from an {@link OfflinePlayer}.
     *
     * @param player The {@link OfflinePlayer} you want to get the folder
     * @return A {@link PlayerFiles} object to manage the data of the player
     */
    public static PlayerFiles getFilesOf(OfflinePlayer player, JavaPlugin plugin) {
        return getFilesOf(player.getUniqueId(), plugin);
    }

    /**
     * Get the public folder of the datas
     *
     * @return A {@link File} object representing the public folder
     */
    public static File getPublicFolder(JavaPlugin plugin) {
        return new File(plugin.getDataFolder(), "players");
    }

    // Méthodes

    /**
     * Save all cached configuration files for all cached players
     */
    public static void saveAllDatas() {
        for (PlayerFiles playerFiles : playerFilesCache.values()) {
            playerFiles.saveAllConfigs();
        }
    }

    /**
     * Save the configuration file passed in parameter
     *
     * @param fileName The name of the file to save with the extension
     */
    public void saveConfig(String fileName) {
        if (configurationCache.containsKey(fileName)) {
            if (!playerFolder.exists()) {
                playerFolder.mkdirs();
            }
            try {
                configurationCache.get(fileName).save(new File(playerFolder, fileName));
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Cannot save file " + fileName + " for " + uuid.toString() + ". Skipping...");
            }
        }
    }

    /**
     * Saves all cached player configuration files
     *
     * @see PlayerFiles#saveConfig(String)
     */
    public void saveAllConfigs() {
        for (String fileName : configurationCache.keySet()) {
            saveConfig(fileName);
        }
    }

    // Getters

    /**
     * Get the personal player folder of the current player
     *
     * @return A {@link File} object representing the player folder
     */
    public File getPlayerFolder() {
        return playerFolder;
    }

    /**
     * Get a configuration file of a player. If the file doesn't exist, it will be created with the default file.
     *
     * @param fileName The name of the file to get with the extension
     * @return The {@link FileConfiguration} object of the file passed in parameter<br>null if the file is a directory or if no default file is found
     */
    public FileConfiguration getConfig(String fileName) {
        if (configurationCache.containsKey(fileName)) {
            return configurationCache.get(fileName);
        } else {
            File requestedFile = new File(playerFolder, fileName);
            FileConfiguration config = null;
            if (!requestedFile.exists()) {
                InputStream is = plugin.getResource(DEFAULT_CONFIG_FOLDER + "/" + fileName);
                if (is == null) return null;
                config = YamlConfiguration.loadConfiguration(new InputStreamReader(is, StandardCharsets.UTF_8));
            } else {
                if (requestedFile.isDirectory()) return null;
                config = YamlConfiguration.loadConfiguration(requestedFile);
            }
            configurationCache.put(fileName, config);
            return config;
        }
    }
}
