package fr.foxelia.tools.minecraft.bukkit.nms.toast;

import com.google.gson.JsonObject;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.LootSerializationContext;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.PacketPlayOutAdvancements;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer; // Adapt to your version
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftItemStack; // Adapt to your version
import org.bukkit.entity.Player;

import java.util.*;

/**
 * Use the Toaster to make some Toast. That is a class to create Toast Notifications
 * <br>It uses NMS to create the Toast, so look and adapt the imports to your server version
 * <br>License: CC BY-SA 4.0
 * @author Zarinoow, Foxelia
 * @apiNote Huge thanks to ZockerAxel who allowed me to reverse engineer his plugin CrazyAdvancementsAPI to create this class and accepted to share it with the community.
 * @version 1.0
 */
public class Toaster {

    /*
     * AdvancementFrameType for easier use
     * Type de cadre pour une utilisation plus facile
     */

    /**
     * A Task Toast is a yellow titled "Advancement made!"
     */

    public static final AdvancementFrameType TASK = AdvancementFrameType.a;

    /**
     * A Goal Toast is a pink titled "Challenge Complete!" and plays a sound
     */
    public static final AdvancementFrameType CHALLENGE = AdvancementFrameType.b;

    /**
     * A Challenge Toast is a yellow titled "Goal Reached!"
     */
    public static final AdvancementFrameType GOAL = AdvancementFrameType.c;

    /*
     * Constants needed to create the notification
     * Constantes nécessaires à la création de la notification
     */

    private static final MinecraftKey key = new MinecraftKey("foxelia", "toast");
    private static final AdvancementRewards advancementRewards = new AdvancementRewards(0, new MinecraftKey[0], new MinecraftKey[0], null);
    private static final String[][] requirements = new String[][] {{"0"}};

    private static final HashMap<String, Criterion> criteria = new HashMap<>();
    private static final AdvancementProgress toastProgress = new AdvancementProgress();

    static {
        criteria.put("0", new Criterion(new CriterionInstance() {
            @Override
            public MinecraftKey a() {
                return new MinecraftKey("", "");
            }

            @Override
            public JsonObject a(LootSerializationContext lootSerializationContext) {
                return null;
            }
        }));
        toastProgress.a(criteria, requirements);
        toastProgress.c("0").b();
    }

    /*
     * Advancement Display
     * Affichage du succès
     */
    private final ItemStack icon;
    private final String title;
    private final String description;
    private final MinecraftKey backgroundTexture;
    private final AdvancementFrameType frameType;

    /*
     * Constructors
     * Constructeurs
     */

    /**
     * Creates a Toast Notification with visible information
     * @param icon The Item to display in the Toast
     * @param text The Text to display in the Toast (Use a maximum of 64 characters including spaces)
     * @param frame The Frame Type of the Toast
     *              <br>Use {@link #TASK} for a Task Toast
     *              <br>Use {@link #CHALLENGE} for a Goal Toast
     *              <br>Use {@link #GOAL} for a Challenge Toast
     */
    public Toaster(org.bukkit.inventory.ItemStack icon, String text, AdvancementFrameType frame) {
        this(icon, text, "", null, frame);
    }

    /**
     * Creates a Toast Notification with useless information
     * It's recommended to use {@link #Toaster(org.bukkit.inventory.ItemStack, String, AdvancementFrameType)} instead
     * I don't know why you would use this constructor
     * @param icon The Item to display in the Toast
     * @param title The Text to display in the Toast
     * @param description The description of the Advancement (not displayed)
     * @param backgroundTexture The path to texture of the Advancement (not displayed)
     * @param frameType The Frame Type of the Toast
     *              <br>Use {@link #TASK} for a Task Toast
     *              <br>Use {@link #CHALLENGE} for a Goal Toast
     *              <br>Use {@link #GOAL} for a Challenge Toast
     * @see #Toaster(org.bukkit.inventory.ItemStack, String, AdvancementFrameType)
     */
    public Toaster(org.bukkit.inventory.ItemStack icon, String title, String description, String backgroundTexture, AdvancementFrameType frameType) {
        this.icon = CraftItemStack.asNMSCopy(icon);
        this.title = title;
        this.description = description;
        if(backgroundTexture != null) this.backgroundTexture = new MinecraftKey(backgroundTexture);
        else this.backgroundTexture = null;
        this.frameType = frameType;
    }

    /*
     * Methods
     * Méthodes
     */

    /**
     * Sends the Toast to the target Player
     * @param player The Player to send the Toast to
     */
    public void sendTo(Player player) {
        sendPacket(player, true);
        sendPacket(player, false);
    }

    /*
     * Zone using NMS relative imports
     * Zone utilisant des imports relatifs à NMS
     */

    /**
     * Constructs the Advancement Packet ready to be sent
     * @param add Whether to add or remove the Advancement
     *            <br>True to add the Advancement
     *            <br>False to remove the Advancement
     * @return {@link PacketPlayOutAdvancements} A AdvancementPacket to be sent to the Player
     */
    public PacketPlayOutAdvancements constructPacket(boolean add) {
        //Create Lists
        List<net.minecraft.advancements.Advancement> addedAdvancements = new ArrayList<>();
        Set<MinecraftKey> removedAdvancements = new HashSet<>();
        Map<MinecraftKey, AdvancementProgress> progress = new HashMap<>();

        if(add) {
            addedAdvancements.add(generateAdvancement());
            progress.put(key, toastProgress);
        } else {
            removedAdvancements.add(key);
        }

        //Create Packet
        return new PacketPlayOutAdvancements(false, addedAdvancements, removedAdvancements, progress);
    }

    /**
     * Sends the Advancement Packet to the Player using NMS
     * @param player The Player to send the Advancement Packet to
     * @param add Whether to add or remove the Advancement
     *            <br>True to add the Advancement
     *            <br>False to remove the Advancement
     * @see #sendTo(Player)
     */
    private void sendPacket(Player player, boolean add) {
        PacketPlayOutAdvancements packet = constructPacket(add);
        ((CraftPlayer) player).getHandle().b.a(packet);
    }

    /**
     * Generates the Advancement with the provided information in the constructor
     * @return {@link net.minecraft.advancements.Advancement} The Advancement needed to create the Packet
     */
    private net.minecraft.advancements.Advancement generateAdvancement() {
        net.minecraft.advancements.AdvancementDisplay advDisplay = new net.minecraft.advancements.AdvancementDisplay(
                icon,
                toChatBaseComponent(title),
                toChatBaseComponent(description),
                backgroundTexture,
                frameType,
                true,
                false,
                true);

        return new net.minecraft.advancements.Advancement(
                key,
                null,
                advDisplay,
                advancementRewards,
                criteria,
                requirements);
    }

    /**
     * Converts a String to a IChatBaseComponent
     * @param text The String to convert
     * @return {@link IChatBaseComponent} The converted String
     */
    private IChatBaseComponent toChatBaseComponent(String text) {
        return IChatBaseComponent.ChatSerializer.a(ComponentSerializer.toString(text));
    }
}
