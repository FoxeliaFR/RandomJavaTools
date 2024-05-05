package fr.foxelia.tools.minecraft.bukkit.nms.toast;


import fr.foxelia.tools.minecraft.bukkit.nms.NMSVersionChecker;

import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Use the Toaster to make some Toast. That is a class to create Toast Notifications
 * <br>It uses NMS to create the Toast, so look and adapt the imports to your server version
 * <br>License: CC BY-SA 4.0
 * @author Zarinoow, Foxelia
 * @apiNote Huge thanks to ZockerAxel who allowed me to reverse engineer his plugin CrazyAdvancementsAPI to create this class and accepted to share it with the community.
 * @version 1.1
 */
public class ToastNotification {

    private final ToastType type;
    private final ItemStack icon;
    private final String text;

    /**
     * Creates a Toast Notification with visible information
     * @param icon The Item to display in the Toast
     * @param text The Text to display in the Toast (Use a maximum of 64 characters including spaces)
     * @param type The {@link ToastType} of the Toast
     */
    public ToastNotification(ToastType type, ItemStack icon, String text) {
        this.type = type;
        this.text = text;
        this.icon = icon;
    }

    public void sendToast(Player player) {
        Toaster toaster = getToaster();
        if (toaster == null) {
            player.sendMessage(text);
        } else {
            toaster.sendTo(player);
        }
    }

    public void sendToast(ArrayList<Player> players) {
        for (Player player : players) {
            sendToast(player);
        }
    }

    private Toaster getToaster() {
        String v = NMSVersionChecker.getNMSVersion();
        try {
            return ((Toaster) Class.forName("fr.foxelia.tools.minecraft.nms.toast.versions.Toaster_" + v)
                            .getConstructor()
                            .newInstance())
                    .setToastType(type)
                    .setText(text)
                    .setIcon(icon);
        } catch (Exception e) {
            return null;
        }
    }
}
