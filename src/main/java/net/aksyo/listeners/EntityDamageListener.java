package net.aksyo.listeners;

import net.aksyo.HunterUHC;
import net.aksyo.game.GameState;
import net.aksyo.game.managers.GameManager;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.teams.Ants;
import net.aksyo.player.UHCPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {

    GameManager gManager = HunterUHC.getInstance().getGameManager();

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player) {

            if (!gManager.isDamage() ||
                    gManager.isGameState(GameState.FROZEN) ||
                    gManager.isGameState(GameState.WAITING) ||
                    gManager.isGameState(GameState.ENDING)) {
                event.setCancelled(true);
            }

            if(!gManager.isPvp()) {
                if(event.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
                    event.setCancelled(true);
                }
            }

            UHCPlayer uhcPlayer = HunterUHC.getInstance().getTeamManager().getUHCPlayer((Player) event.getEntity());

            if (uhcPlayer != null && uhcPlayer.getRole() != null) {

                if (uhcPlayer.getRole() == RoleType.MERUEM && event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
                    event.setCancelled(true);
                }

                if (uhcPlayer.getTeam() instanceof Ants && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    event.setDamage(event.getDamage() * 0.8);
                }


            }


        }

    }

}
