package fr.foxelia.foxapi.GUI;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(event.getInventory().getHolder() instanceof GUI) {
            GUI gui = (GUI) event.getInventory().getHolder();
            gui.onClick(event);
        }
    }

}
