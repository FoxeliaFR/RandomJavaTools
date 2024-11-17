package fr.foxelia.tools.minecraft.bukkit.datas.uuid;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UUIDFetcher {

    // Constantes
    private static Map<String, UUID> players = new HashMap<>();

    private static UUIDFetcher instance = null;
    private File fetcherFile;
    private FileConfiguration fetcherConfig;

    public UUIDFetcher(File file) {
        instance = this;
        fetcherFile = file;
        loadConfig();
    }

    private void loadConfig() {
        if(!fetcherFile.exists()) {
            if(!fetcherFile.getParentFile().exists()) fetcherFile.getParentFile().mkdirs();
            try {
                fetcherFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        fetcherConfig = YamlConfiguration.loadConfiguration(fetcherFile);
        if(fetcherConfig.getConfigurationSection("players") == null) {
            return;
        }
        for(String key : fetcherConfig.getConfigurationSection("players").getKeys(false)) {
            players.put(key, UUID.fromString(fetcherConfig.getString("players." + key)));
        }
    }

    public void saveConfig() {
        if(fetcherFile == null || fetcherConfig == null) return;
        fetcherConfig.set("players", null);
        for(Map.Entry<String, UUID> en : players.entrySet()) {
            fetcherConfig.set("players." + en.getKey(), en.getValue().toString());
        }
        if(!fetcherFile.exists()) {
            if (!fetcherFile.getParentFile().exists()) fetcherFile.getParentFile().mkdirs();
        } else fetcherFile.delete();
        try {
            fetcherConfig.save(fetcherFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reloadConfig() {
        players.clear();
        loadConfig();
    }


    /*
     * Statics Tools
     */

    public static UUID getUUIDByName(String name) {
        if(players.containsKey(name.toLowerCase())) return players.get(name.toLowerCase());
        return null;
    }

    public static void updatePlayerInfo(OfflinePlayer playerToUpdate) {
        players.put(playerToUpdate.getName().toLowerCase(), playerToUpdate.getUniqueId());
    }

    public static UUIDFetcher getInstance() {
        return instance;
    }
}
