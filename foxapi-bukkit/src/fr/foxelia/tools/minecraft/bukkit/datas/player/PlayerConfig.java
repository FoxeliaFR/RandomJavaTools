package fr.foxelia.tools.minecraft.bukkit.datas.player;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class PlayerConfig {

    /**
     * Name of the configuration
     */
    private final String configurationName;

    /**
     * The configuration of the player
     * Key: The field name
     * Value: The value of the field
     */
    private final Map<String, String> stringConfig = new HashMap<>();
    private final Map<String, List<String>> stringListConfig = new HashMap<>();
    private final Map<String, Integer> intConfig = new HashMap<>();
    private final Map<String, Boolean> booleanConfig = new HashMap<>();
    private final Map<String, ItemStack> isConfig = new HashMap<>();

    private final Set<String> usedKeys = new HashSet<>();

    public PlayerConfig(String configurationName) {
        this.configurationName = configurationName;
    }

    //private static final Map<UUID, config> players = new HashMap<>;

    /**
     * Set a string in the configuration
     * @param keyConfig The name of the value
     * @param value A {@link String} value
     */
    public void setString(String keyConfig, String value) {
        validKey(keyConfig);
        verificationUsedKey(keyConfig);
        stringConfig.put(keyConfig, value);
    }

    /**
     * Set a list of strings in the configuration
     * @param keyConfig The name of the value
     * @param value A {@link List} of {@link String} value
     */
    public void setStringList(String keyConfig, List<String> value) {
        validKey(keyConfig);
        verificationUsedKey(keyConfig);
        stringListConfig.put(keyConfig, value);
    }

    /**
     * Set an integer in the configuration
     * @param keyConfig The name of the value
     * @param value An {@link Integer} value
     */
    public void setInt(String keyConfig, int value) {
        validKey(keyConfig);
        verificationUsedKey(keyConfig);
        intConfig.put(keyConfig, value);
    }

    /**
     * Set an integer in the configuration
     * @param keyConfig The name of the value
     * @param value An {@link Boolean} value
     */
    public void setBoolean(String keyConfig, boolean value) {
        validKey(keyConfig);
        verificationUsedKey(keyConfig);
        booleanConfig.put(keyConfig,value);
    }

    /**
     * Set an ItemStack in the configuration
     * @param keyConfig The name of the value
     * @param value An {@link ItemStack} value
     */
    public void setItemStack(String keyConfig, ItemStack value) {
        validKey(keyConfig);
        verificationUsedKey(keyConfig);
        isConfig.put(keyConfig, value);
    }

    /**
     * Get a string from the configuration
     * @param keyConfig The name of the value
     * @return The value of the field
     */
    @Nullable
    public String getString(String keyConfig) {
        if(usedKeys.contains(keyConfig))return stringConfig.get(keyConfig);
        else return null;
    }

    /**
     * Get a list of strings from the configuration
     * @param keyConfig The name of the value
     * @return The value of the field
     */
    @Nullable
    public List<String> getStringList(String keyConfig) {
        if(usedKeys.contains(keyConfig))return stringListConfig.get(keyConfig);
        else return null;
    }

    /**
     * Get an integer from the configuration
     * @param keyConfig The name of the value
     * @return The value of the field
     */
    @Nullable
    public Integer getInt(String keyConfig) {
        if(usedKeys.contains(keyConfig))return intConfig.get(keyConfig);
        else return null;
    }

    /**
     * Get a boolean from the configuration
     * @param keyConfig The name of the value
     * @return The value of the field
     */
    @Nullable
    public Boolean getBoolean(String keyConfig) {
        if(usedKeys.contains(keyConfig))return booleanConfig.get(keyConfig);
        else return null;
    }

    /**
     * Get an ItemStack from the configuration
     * @param keyConfig The name of the value
     * @return The value of the field
     */
    @Nullable
    public ItemStack getItemStack(String keyConfig) {
        return isConfig.get(keyConfig);
    }

    /**
     * Remove a key from the configuration
     * @param key The key to remove
     */
    public void removeKey(String key){
        if(usedKeys.contains(key)) {
            if (stringConfig.containsKey(key)) stringConfig.remove(key);
            else if (stringListConfig.containsKey(key)) stringListConfig.remove(key);
            else if (intConfig.containsKey(key)) intConfig.remove(key);
            else if (booleanConfig.containsKey(key)) booleanConfig.remove(key);
            else if (isConfig.containsKey(key)) isConfig.remove(key);
            else throw new IllegalArgumentException("Key is already used but the config is not found");
        }else{
            throw new IllegalArgumentException("Key is not found !");
        }
    }

    /**
     * Verify if the key is already used
     * If the key is already used, remove the key from the configuration
     * @param key The key we want to verify
     */
    private void verificationUsedKey(String key){
        if(usedKeys.contains(key)) {
            if(stringConfig.containsKey(key))stringConfig.remove(key);
            else if(stringListConfig.containsKey(key))stringListConfig.remove(key);
            else if(intConfig.containsKey(key))intConfig.remove(key);
            else if(booleanConfig.containsKey(key))booleanConfig.remove(key);
            else if(isConfig.containsKey(key))isConfig.remove(key);
            else throw new IllegalArgumentException("Key is already used but the config is not found");
        }else usedKeys.add(key);
    }

    /**
     * Verify if the key is valid
     * The key must contain only letters, numbers and underscores
     * @param key The key we want to verify
     */
    private void validKey(String key) {
        if(!key.matches("^[a-zA-Z0-9_]*$")) {
            throw new IllegalArgumentException("Key must contain only letters, numbers and underscores");
        }
    }
}
