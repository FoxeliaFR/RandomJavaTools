package fr.foxelia.foxapi.listeners.cooldown;

import fr.foxelia.tools.minecraft.bukkit.cooldown.CooldownManager;
import fr.foxelia.tools.minecraft.bukkit.cooldown.CooldownType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class CooldownTakeDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntity() instanceof Player player) {
            CooldownManager.trigger(player, CooldownType.TAKE_DAMAGE);
        }
    }

}
