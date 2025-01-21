package fr.foxelia.tools.minecraft.bukkit.datas.player;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Type;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public abstract class PlayerConfigParser {

    private final PlayerConfig playerConfig;

    public PlayerConfigParser(PlayerConfig playerConfig) {
        this.playerConfig = playerConfig;
    }

    protected String parseStringList(List<String> value) {
        StringBuilder builder = new StringBuilder();
        for (String s : value) {
            builder.append(s).append(";,;,;");
        }
        return builder.toString();
    }

    protected List<String> parseStringList(String value) {
        return List.of(value.split(";,;,;"));
    }

    protected String parseItemStack(ItemStack value) {
        Map<String, Object> itemMap = value.serialize();
        Gson gson = new Gson();
        String json = gson.toJson(itemMap);
        byte[] encodedBytes = Base64.getEncoder().encode(json.getBytes());
        return new String(encodedBytes);
    }

    protected ItemStack parseItemStack(String value) {
        byte[] decodedBytes = Base64.getDecoder().decode(value);
        String json = new String(decodedBytes);
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        Map<String, Object> itemMap = gson.fromJson(json, type);
        return ItemStack.deserialize(itemMap);
    }


}
