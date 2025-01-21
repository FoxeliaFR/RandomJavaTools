package fr.foxelia.tools.minecraft.bukkit.datas.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerFiles {

    private static final HashMap<UUID, Map<String, PlayerConfig>> players = new HashMap<>();

    /**
     * Get the configuration of a player
     * @param uuid The UUID of the player
     * @param configName The name of the configuration you want to get
     * @return A {@link PlayerConfig}
     */

    public static PlayerConfig getConfigOrNew(UUID uuid, String configName) {
        if (!players.containsKey(uuid)) {
            players.put(uuid, new HashMap<>());
        }
        if (!players.get(uuid).containsKey(configName)) {
            players.get(uuid).put(configName, new PlayerConfig(configName));
        }
        return players.get(uuid).get(configName);
    }
}
