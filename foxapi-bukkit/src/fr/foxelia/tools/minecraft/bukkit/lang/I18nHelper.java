package fr.foxelia.tools.minecraft.bukkit.lang;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

/**
 * I18nHelper is a utility class for managing internationalization (i18n) in a Bukkit plugin.
 * It provides methods to retrieve translations from the plugin's language files.
 * The default locale is set to "default", which serves as the fallback language.
 * <br><br>License: CC BY-SA 4.0
 * @author Foxelia, Zarinoow
 * @version 1.0
 */
public class I18nHelper {

    private final I18nPluginInstance i18n;

    private String defaultLocale = "default"; // Default locale is set to "default" which is the fallback language

    public I18nHelper(I18nPluginInstance i18n) {
        this.i18n = i18n;
    }

    /**
     * Retrieves a translation for the given key from the default locale.
     * @param key the key for the translation (e.g., "commands.help")
     * @return the translated {@link String} in the default locale, or the fallback language if the key is not found
     */
    public String get(String key) {
        FileConfiguration translations = i18n.get(defaultLocale);
        if(!translations.contains(key)) return i18n.get().getString(key); // Fallback to the fallback language
        return translations.getString(key);
    }

    /**
     * Retrieves a list of translations for the given key from the default locale.
     * @param key the key for the translation (e.g., "tools.magic_wand.description")
     * @return the {@link List} of translated {@link String}s in the default locale, or the fallback language if the key is not found
     */
    public List<String> getList(String key) {
        FileConfiguration translations = i18n.get(defaultLocale);
        if(!translations.contains(key)) return i18n.get().getStringList(key); // Fallback to the fallback language
        return translations.getStringList(key);
    }

    /**
     * Retrieves a translation for the given key from the specified locale.
     * If the key is not found in the specified locale, it falls back to the default locale.
     * @param key the key for the translation (e.g., "welcome_message")
     * @param locale the locale to retrieve the translation from (e.g., "en_US", "fr_FR")
     * @return the translated {@link String} in the specified locale, or the default locale if the key is not found
     */
    public String get(String key, String locale) {
        FileConfiguration translations = i18n.get(locale);
        if (!translations.contains(key)) get(key); // Fallback to the default language
        return translations.getString(key);
    }

    /**
     * Retrieves a list of translations for the given key from the specified locale.
     * If the key is not found in the specified locale, it falls back to the default locale.
     * @param key the key for the translation (e.g., "pets.fox.description")
     * @param locale the locale to retrieve the translation from (e.g., "en_US", "fr_FR")
     * @return the {@link List} of translated {@link String}s in the specified locale, or the default locale if the key is not found
     */
    public List<String> getList(String key, String locale) {
        FileConfiguration translations = i18n.get(locale);
        if (!translations.contains(key)) return getList(key); // Fallback to the default language
        return translations.getStringList(key);
    }

    /**
     * Sets the default locale for translations.
     * This is used to determine which language file to use when no specific locale is provided.
     * @param locale the locale to set as the default (e.g., "en_US", "fr_FR")
     */
    public void setDefaultLocale(String locale) {
        this.defaultLocale = locale;
    }
}
