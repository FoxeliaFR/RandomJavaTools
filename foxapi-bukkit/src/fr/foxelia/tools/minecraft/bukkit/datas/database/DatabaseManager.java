package fr.foxelia.tools.minecraft.bukkit.datas.database;

import fr.foxelia.foxapi.FoxAPI;
import fr.foxelia.tools.minecraft.bukkit.ui.console.color.ColoredConsole;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private Connection connection = null;

    public void connect(String dbType, String host, Integer port, String dbName, String user, String pass) {
        connect(
                "jdbc:" + dbType + "://" + host + ":" + port + "/" + dbName,
                user,
                pass
        );
    }

    public void connect(String url, String user, String pass) {
        try {
            connection = DriverManager.getConnection(url, user, pass);
            FoxAPI.getInstance().getLogger().info(ColoredConsole.translateAlternateColorCodes(ChatColor.GREEN + "✅ Successfully connected to the database!"));
        } catch (SQLException e) {
            FoxAPI.getInstance().getLogger().severe(ColoredConsole.translateAlternateColorCodes(ChatColor.RED + "❌ Failed to connect to the database!"));
            FoxAPI.getInstance().getLogger().severe(ColoredConsole.translateAlternateColorCodes(ChatColor.RED + "Please check your database configuration!"));
            FoxAPI.getInstance().getLogger().severe(ColoredConsole.translateAlternateColorCodes(ChatColor.RED + "Error: " + e.getMessage()));
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

