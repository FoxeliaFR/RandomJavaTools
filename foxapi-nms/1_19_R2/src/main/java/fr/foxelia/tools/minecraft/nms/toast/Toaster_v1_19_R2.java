package fr.foxelia.tools.minecraft.nms.toast;


import com.google.gson.JsonObject;
import fr.foxelia.tools.minecraft.bukkit.nms.toast.ToastType;
import fr.foxelia.tools.minecraft.bukkit.nms.toast.Toaster;
import java.util.*;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.LootSerializationContext;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.PacketPlayOutAdvancements;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;

public class Toaster_v1_19_R2 implements Toaster {

    /*
     * Statics Constants needed to create the notification
     * Constantes statiques nécessaires à la création de la notification
     */

    private static final MinecraftKey key = new MinecraftKey("foxelia", "toast");
    private static final AdvancementRewards advancementRewards =
            new AdvancementRewards(0, new MinecraftKey[0], new MinecraftKey[0], null);
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
     * User Constants
     * Constantes de l'utilisateur
     */

    private ItemStack icon;
    private String text;
    private ToastType type;

    @Override
    public Toaster setToastType(ToastType type) {
        this.type = type;
        return this;
    }

    @Override
    public Toaster setIcon(org.bukkit.inventory.ItemStack icon) {
        this.icon = CraftItemStack.asNMSCopy(icon);
        return this;
    }

    @Override
    public Toaster setText(String text) {
        this.text = text;
        return this;
    }

    /*
     * Getters
     * Obtenteurs
     */

    private AdvancementFrameType getFrame() {
        return switch (type) {
            case CHALLENGE -> AdvancementFrameType.b;
            case GOAL -> AdvancementFrameType.c;
            default -> AdvancementFrameType.a;
        };
    }

    /*
     * Methods
     * Méthodes
     */

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
        // Create Lists
        List<Advancement> addedAdvancements = new ArrayList<>();
        Set<MinecraftKey> removedAdvancements = new HashSet<>();
        Map<MinecraftKey, AdvancementProgress> progress = new HashMap<>();

        if (add) {
            addedAdvancements.add(generateAdvancement());
            progress.put(key, toastProgress);
        } else {
            removedAdvancements.add(key);
        }

        // Create Packet
        return new PacketPlayOutAdvancements(false, addedAdvancements, removedAdvancements, progress);
    }

    @Override
    public void sendPacket(Player player, boolean add) {
        PacketPlayOutAdvancements packet = constructPacket(add);
        ((CraftPlayer) player).getHandle().b.a(packet);
    }

    /**
     * Generates the Advancement with the provided information in the constructor
     * @return {@link Advancement} The Advancement needed to create the Packet
     */
    private Advancement generateAdvancement() {
        AdvancementDisplay advDisplay = new AdvancementDisplay(
                icon, toChatBaseComponent(text), toChatBaseComponent(""), null, getFrame(), true, false, true);

        return new Advancement(key, null, advDisplay, advancementRewards, criteria, requirements);
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
