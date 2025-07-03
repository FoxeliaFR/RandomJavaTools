package fr.foxelia.tools.minecraft.bukkit.lang;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * I18nPluginInstance is a utility class for managing internationalization (i18n) in a Bukkit plugin.
 * It handles loading, registering, and retrieving language files for translations.
 * The default language file is expected to be named "defaultLanguage.yml" and located in the plugin's resources folder.
 * Other language files can be added to the "lang" folder in the plugin's resources directory.
 * <br><br>License: CC BY-SA 4.0
 *
 * @author Foxelia, Zarinoow
 * @version 1.0
 */
public class I18nPluginInstance {

    private static final String DEFAULT_RESOURCE = "defaultLanguage.yml";

    private final JavaPlugin plugin;
    private final File langFolder;
    private final String[] defaultLanguages;
    private final Map<String, FileConfiguration> i18nMap = new HashMap<>();

    /**
     * Constructs an instance of I18nPluginInstance.
     * Initializes the language folder and loads all language files.
     *
     * @param plugin the Bukkit plugin instance to internationalize
     * @param langCodes all other language codes to register, if any. (e.g., "fr_FR", "en_US", etc.)
     */
    public I18nPluginInstance(JavaPlugin plugin, String... langCodes) {
        this.plugin = plugin;
        this.defaultLanguages = langCodes.length > 0 ? langCodes : new String[0];
        this.langFolder = new File(plugin.getDataFolder(), "lang");

        reloadAll();
    }

    /**
     * Initializes the default language file and ensures the fallback language file exists.
     * Throws an exception if the default language file is not found in the plugin's resources.
     */
    private void init() {
        // Create the lang folder if it doesn't exist
        if(!langFolder.exists()) {
            langFolder.mkdirs();
        }

        // Check if plugin has a default language file
        if(plugin.getResource(DEFAULT_RESOURCE) != null) {
            // Initialize the default language
            FileConfiguration defaultLanguage = initI18n(DEFAULT_RESOURCE);
            if(defaultLanguage != null) {
                i18nMap.put("default", defaultLanguage);
            }
            // Load the default language file into the map
            File fallbackCustom = new File(langFolder, "fallback.yml");
            if(!fallbackCustom.exists()) {
                try {
                    defaultLanguage.save(fallbackCustom);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else loadCustomAndUpdateDefaults(fallbackCustom);
        } else {
            throw new IllegalStateException("Default language file not found in plugin resources. Please ensure that '" + DEFAULT_RESOURCE + "' exists in the resources folder of your plugin.");
        }
    }

    /**
     * Loads a language file from the plugin's resources into memory.
     *
     * @param langPath the path to the language file in the plugin's resources
     * @return the loaded {@link FileConfiguration}, or null if the file is not found
     */
    private FileConfiguration initI18n(String langPath) {
        InputStream is = plugin.getResource(langPath);
        if(is == null) return null;
        InputStreamReader reader = new InputStreamReader(is, StandardCharsets.UTF_8);
        FileConfiguration memoryLocalization = YamlConfiguration.loadConfiguration(reader);
        try {
            reader.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return memoryLocalization;

    }

    /**
     * Loads a custom language file and updates it with missing keys from the default language file.
     *
     * @param defaultFile the custom language file to load and update
     */
    private void loadCustomAndUpdateDefaults(@NotNull File defaultFile) {
        if(!defaultFile.exists()) return;
        String langCode = defaultFile.getName().replace(".yml", "");
        if(langCode.equals("fallback")) langCode = "default";

        FileConfiguration customConfig = YamlConfiguration.loadConfiguration(defaultFile);
        String defaultPath = langCode == "default" ? "defaultLanguage.yml" : "lang/" + langCode + ".yml";
        FileConfiguration defaultConfig = initI18n(defaultPath);
        if(defaultConfig != null) {
            boolean isModified = false;
            for(String key : defaultConfig.getKeys(true)) {
                if(!customConfig.contains(key)) {
                    customConfig.set(key, defaultConfig.get(key));
                    isModified = true;
                }
            }
            if(isModified) {
                try {
                    customConfig.save(defaultFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        i18nMap.put(langCode, customConfig);
    }

    /**
     * Registers language files based on the provided language codes.
     * If a language file does not exist, it is created from the plugin's resources.
     *
     * @param langCodes the language codes to register
     */
    private void register(String... langCodes) {
        for(String code : langCodes) {
            File langFile = new File(langFolder, code + ".yml");
            if(!langFile.exists()) {
                try {
                    FileConfiguration langConfig = initI18n("lang/" + code + ".yml");
                    i18nMap.put(code, langConfig);
                    langConfig.save(langFile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else loadCustomAndUpdateDefaults(langFile);
        }
    }

    /**
     * Loads all custom language files from the language folder.
     * Throws an exception if the language folder does not exist or is not a directory.
     */
    private void loadCustoms() {
        if(langFolder.exists() && langFolder.isDirectory()) {
            for(File file : langFolder.listFiles()) {
                if(file.isFile() && file.getName().endsWith(".yml")) {
                    String langCode = file.getName().replace(".yml", "");
                    if(!i18nMap.containsKey(langCode)) {
                        FileConfiguration customConfig = YamlConfiguration.loadConfiguration(file);
                        i18nMap.put(langCode, customConfig);
                    }
                }
            }
        } else {
            throw new IllegalStateException("Language folder does not exist or is not a directory: " + langFolder.getAbsolutePath());
        }
    }

    /**
     * Reloads all language files, clearing the current map and reinitializing the default and custom files.
     */
    protected void reloadAll() {
        i18nMap.clear();
        init();
        register(defaultLanguages);
        loadCustoms();
    }

    /**
     * Retrieves the default language configuration.
     *
     * @return the {@link FileConfiguration} for the default language
     */
    protected FileConfiguration get() {
        return get("default");
    }

    /**
     * Retrieves the language configuration for the specified language code.
     * Falls back to the default language if the specified code is null or empty.
     *
     * @param langCode the language code to retrieve
     * @return the {@link FileConfiguration} for the specified language, or the default language if not found
     */
    protected FileConfiguration get(String langCode) {
        if(langCode == null || langCode.isEmpty()) {
            return get();
        }
        return i18nMap.getOrDefault(langCode, i18nMap.get("default"));
    }

}
