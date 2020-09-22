package net.aksyo.listeners;

import net.aksyo.HunterUHC;
import net.aksyo.game.managers.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    GameManager gManager = HunterUHC.getInstance().getGameManager();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if(!gManager.isMovement()) {
            player.teleport(event.getFrom());
        }

    }

}
