package net.aksyo.listeners;

import net.aksyo.Constant;
import net.aksyo.HunterUHC;
import net.aksyo.game.GameState;
import net.aksyo.game.managers.GameManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameListener implements Listener {

    GameManager gManager = HunterUHC.getInstance().getGameManager();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if(!gManager.isGameState(GameState.WAITING)) {

            if(player.hasPermission(Constant.getBypassPermission("join"))){

                player.setGameMode(GameMode.SPECTATOR);
                event.setJoinMessage(" ");
            } else {
                event.getPlayer().kickPlayer("You cannot join the game");
            }

        } else {
            event.setJoinMessage(Constant.getMessages("playerjoin").replace("%player%", player.getName()));
        }



    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        if(gManager.isGameState(GameState.WAITING) || gManager.isGameState(GameState.ENDING)) {

            event.setQuitMessage(Constant.getMessages("playerquit").replace("%player%", player.getName()));

        } else {

            if(HunterUHC.getInstance().getTeamManager().getUHCPlayer(player) != null) {

            }

        }

    }


}
