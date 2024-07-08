package fr.foxelia.foxapi.listeners.cooldown;

import fr.foxelia.tools.minecraft.bukkit.cooldown.CooldownManager;
import fr.foxelia.tools.minecraft.bukkit.cooldown.CooldownType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class CooldownMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (!e.getFrom().getBlock().equals(e.getTo().getBlock())) {
            CooldownManager.trigger(e.getPlayer(), CooldownType.MOVE);
        }
    }

}
