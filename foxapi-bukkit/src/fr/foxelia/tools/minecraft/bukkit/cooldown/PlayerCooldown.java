package fr.foxelia.tools.minecraft.bukkit.cooldown;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * A class that represents all the cooldowns of a player
 * <br><br>License: CC BY-SA 4.0
 * @author Foxelia, Zarinoow
 * @version 1.0
 */
public class PlayerCooldown {

    private final UUID player;

    private Map<CooldownType, Long> triggers = new HashMap<>();

    public PlayerCooldown(UUID player) {
        this.player = player;
    }

    public boolean hasTriggered(CooldownType type, long cooldown) {
        if(triggers.containsKey(type)) {
            if(System.currentTimeMillis() - triggers.get(type) < cooldown) {
                return true;
            } else {
                triggers.remove(type);
                return false;
            }
        }
        return false;
    }

    public void trigger(CooldownType type) {
        long current = System.currentTimeMillis();
        triggers.put(CooldownType.GLOBAL, current);
        triggers.put(type, current);
    }
}
