package fr.foxelia.tools.minecraft.bukkit.cooldown;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Manages the cooldowns of the players
 * <br><br>License: CC BY-SA 4.0
 * @author Foxelia, Zarinoow
 * @version 1.0
 */
public class CooldownManager {

    private static HashMap<UUID, PlayerCooldown> playerCooldown = new HashMap<>();

    /**
     * Checks if the player has triggered a cooldown in the given time
     * @param player The player to check the cooldown for
     * @param context The type of cooldown to check
     * @param cooldown The time in milliseconds to check
     * @return True if the player has triggered does the action in the given time
     */
    public static boolean hasTriggered(Player player, CooldownType context, long cooldown) {
        if(playerCooldown.containsKey(player.getUniqueId())) {
            return playerCooldown.get(player.getUniqueId()).hasTriggered(context, cooldown);
        }
        return false;
    }

    /**
     * Triggers a cooldown for the player
     * <br><br>⚠️ This method is used by the FoxAPI and should not be used by other plugins.
     * Use at your own risk.
     * @param player The player to trigger the cooldown for
     * @param type The type of cooldown to update
     */
    public static void trigger(Player player, CooldownType type) {
        if(!playerCooldown.containsKey(player.getUniqueId())) {
            playerCooldown.put(player.getUniqueId(), new PlayerCooldown(player.getUniqueId()));
        }
        playerCooldown.get(player.getUniqueId()).trigger(type);
    }
}
