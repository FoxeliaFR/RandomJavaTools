package fr.foxelia.tools.minecraft.bukkit.nms;


import org.bukkit.Bukkit;

public class NMSVersionChecker {

    public static String getNMSVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }
}
