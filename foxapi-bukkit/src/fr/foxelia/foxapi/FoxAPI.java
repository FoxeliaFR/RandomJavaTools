package fr.foxelia.foxapi;

import fr.foxelia.foxapi.listeners.cooldown.CooldownMoveListener;
import fr.foxelia.foxapi.listeners.cooldown.CooldownTakeDamageListener;
import fr.foxelia.tools.minecraft.bukkit.cooldown.CooldownType;
import fr.foxelia.tools.minecraft.bukkit.ui.console.color.ColoredConsole;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Level;

public class FoxAPI extends JavaPlugin {
    private static FoxAPI instance;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().log(Level.INFO, ColoredConsole.DARK_GREEN + "======" + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_GREEN + getDescription().getName() + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_GREEN + "Initializing..." + ColoredConsole.RESET);
        getLogger().log(Level.INFO, "");
        getLogger().log(Level.INFO, ColoredConsole.DARK_GREEN + "Version " + getDescription().getVersion() + ColoredConsole.RESET);
        getLogger().log(Level.INFO, "");
        getLogger().log(Level.INFO, ColoredConsole.YELLOW + "By " + getDescription().getAuthors().toString().replace("[", "").replace("]", "") + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_GREEN + "======");
    }

    @Override
    public void onDisable() {

        getLogger().log(Level.INFO, ColoredConsole.DARK_RED + "======" + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_RED + getDescription().getName() + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_RED + "Disabling..." + ColoredConsole.RESET);
        getLogger().log(Level.INFO, "");
        getLogger().log(Level.INFO, ColoredConsole.DARK_RED + "Version " + getDescription().getVersion() + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_RED + "Thanks for using FoxAPI!" + ColoredConsole.RESET);
        getLogger().log(Level.INFO, "");
        getLogger().log(Level.INFO, ColoredConsole.YELLOW + "By " + getDescription().getAuthors().toString().replace("[", "").replace("]", "") + ColoredConsole.RESET);
        getLogger().log(Level.INFO, ColoredConsole.DARK_RED + "======" + ColoredConsole.RESET);

    }

    public static FoxAPI getInstance() {
        return instance;
    }

    private Set<CooldownType> hooks = new HashSet<>();

    public void hookCooldown(CooldownType... cooldown) {
        for(CooldownType type : cooldown) {
            if(hooks.contains(type)) continue;
            if(type.equals(CooldownType.GLOBAL)) {
                CooldownType[] values = Arrays.stream(CooldownType.values())
                        .filter(t -> !t.equals(CooldownType.GLOBAL))
                        .toArray(CooldownType[]::new);
                for(CooldownType value : values) {
                    hookCooldown(value);
                }
                break;
            }
            hooks.add(type);
            switch (type) {
                case MOVE -> getServer().getPluginManager().registerEvents(new CooldownMoveListener(), this);
                case TAKE_DAMAGE -> getServer().getPluginManager().registerEvents(new CooldownTakeDamageListener(), this);
            }
        }
    }
}
