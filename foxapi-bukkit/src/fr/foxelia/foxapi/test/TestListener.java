package fr.foxelia.foxapi.test;

import fr.foxelia.foxapi.FoxAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.sql.SQLException;

public class TestListener implements Listener {

    //écrit un message
    @EventHandler
    public void onPlayerChat(PlayerChatEvent event){
        TestUser user = FoxAPI.testUserManager.getOrLoad(event.getPlayer().getName(), TestUser.class);
        if(user != null){
            event.getPlayer().sendMessage("Vous avez " + user.getPoints() + " points. Vous venez d'en gagner 1.");
            user.setPoints(user.getPoints() + 1);
        } else {
            user = new TestUser();
            event.getPlayer().sendMessage("Vous n'avez pas de points. Vous venez d'en gagner 5.");
            user.setUsername(event.getPlayer().getName());
            user.setPoints(5);
        }
    }
}
