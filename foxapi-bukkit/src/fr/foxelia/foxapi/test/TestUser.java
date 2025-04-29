package fr.foxelia.foxapi.test;

import fr.foxelia.foxapi.FoxAPI;
import fr.foxelia.tools.minecraft.bukkit.datas.database.Column;
import fr.foxelia.tools.minecraft.bukkit.datas.database.PrimaryKey;
import fr.foxelia.tools.minecraft.bukkit.datas.database.Table;

@Table(name = "test_user")
public class TestUser {

    @PrimaryKey
    @Column(name = "username")
    public String username;

    @Column(name = "points")
    public int points;


    public void setPoints(int points) {
        this.points = points;
        FoxAPI.testUserManager.updateField(this, "points", points); // auto-sync
    }

    public void setUsername(String username) {
        this.username = username;
        FoxAPI.testUserManager.updateField(this, "username", username); // auto-sync
    }

    public int getPoints() {
        return points;
    }

    public String getUsername() {
        return username;
    }
}
