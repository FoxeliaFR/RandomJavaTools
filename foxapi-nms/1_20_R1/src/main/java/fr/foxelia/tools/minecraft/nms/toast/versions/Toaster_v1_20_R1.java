package fr.foxelia.tools.minecraft.nms.toast.versions;


import com.google.gson.JsonObject;
import fr.foxelia.tools.minecraft.bukkit.nms.toast.ToastType;
import fr.foxelia.tools.minecraft.bukkit.nms.toast.Toaster;
import java.util.*;
import net.md_5.bungee.chat.ComponentSerializer;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundUpdateAdvancementsPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;

public class Toaster_v1_20_R1 implements Toaster {

    /*
     * Statics Constants needed to create the notification
     * Constantes statiques nécessaires à la création de la notification
     */

    private static final ResourceLocation key = new ResourceLocation("foxelia", "toast");
    private static final AdvancementRewards advancementRewards =
            new AdvancementRewards(0, new ResourceLocation[0], new ResourceLocation[0], null);
    private static final String[][] requirements = new String[][] {{"0"}};

    private static final HashMap<String, Criterion> criteria = new HashMap<>();
    private static final AdvancementProgress toastProgress = new AdvancementProgress();

    static {
        criteria.put("0", new Criterion(new CriterionTriggerInstance() {
            @Override
            public ResourceLocation getCriterion() {
                return new ResourceLocation("", "");
            }

            @Override
            public JsonObject serializeToJson(SerializationContext lootSerializationContext) {
                return null;
            }
        }));
        toastProgress.update(criteria, requirements);
        toastProgress.getCriterion("0").grant();
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

    private FrameType getFrame() {
        return switch (type) {
            case CHALLENGE -> FrameType.CHALLENGE;
            case GOAL -> FrameType.GOAL;
            default -> FrameType.TASK;
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
     * @return {@link ClientboundUpdateAdvancementsPacket} A AdvancementPacket to be sent to the Player
     */
    public ClientboundUpdateAdvancementsPacket constructPacket(boolean add) {
        // Create Lists
        List<Advancement> addedAdvancements = new ArrayList<>();
        Set<ResourceLocation> removedAdvancements = new HashSet<>();
        Map<ResourceLocation, AdvancementProgress> progress = new HashMap<>();

        if (add) {
            addedAdvancements.add(generateAdvancement());
            progress.put(key, toastProgress);
        } else {
            removedAdvancements.add(key);
        }

        // Create Packet
        return new ClientboundUpdateAdvancementsPacket(false, addedAdvancements, removedAdvancements, progress);
    }

    @Override
    public void sendPacket(Player player, boolean add) {
        ClientboundUpdateAdvancementsPacket packet = constructPacket(add);
        ((CraftPlayer) player).getHandle().connection.send(packet);
    }

    /**
     * Generates the Advancement with the provided information in the constructor
     * @return {@link Advancement} The Advancement needed to create the Packet
     */
    private Advancement generateAdvancement() {
        DisplayInfo advDisplay = new DisplayInfo(
                icon, toChatBaseComponent(text), toChatBaseComponent(""), null, getFrame(), true, false, true);

        return new Advancement(
                key,
                null,
                advDisplay,
                advancementRewards,
                criteria,
                requirements,
                false); // Set flag to false to block telemetry events
    }

    /**
     * Converts a String to a Component
     * @param text The String to convert
     * @return {@link Component} The converted String
     */
    private Component toChatBaseComponent(String text) {
        return Component.Serializer.fromJson(ComponentSerializer.toString(text));
    }
}
