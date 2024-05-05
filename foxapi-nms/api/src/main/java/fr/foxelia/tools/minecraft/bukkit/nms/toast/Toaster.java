package fr.foxelia.tools.minecraft.bukkit.nms.toast;


import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Toaster {

    Toaster setToastType(ToastType type);

    Toaster setIcon(ItemStack icon);

    Toaster setText(String text);

    /**
     * Sends the Toast to the target Player
     * @param player The Player to send the Toast to
     */
    default void sendTo(Player player) {
        sendPacket(player, true);
        sendPacket(player, false);
    }
    ;

    /**
     * Sends the Advancement Packet to the Player using NMS
     * @param player The Player to send the Advancement Packet to
     * @param add Whether to add or remove the Advancement
     *            <br>True to add the Advancement
     *            <br>False to remove the Advancement
     * @see #sendTo(Player)
     */
    void sendPacket(Player player, boolean add);
}
