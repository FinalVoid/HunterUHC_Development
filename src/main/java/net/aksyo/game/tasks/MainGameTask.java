package net.aksyo.game.tasks;

import net.aksyo.Constant;
import net.aksyo.HunterUHC;
import net.aksyo.game.GameState;
import net.aksyo.game.managers.GameManager;
import net.aksyo.game.managers.WorldManager;
import org.bukkit.scheduler.BukkitRunnable;

public class MainGameTask {

    private final GameManager gManager = HunterUHC.getInstance().getGameManager();
    private final WorldManager wManager = HunterUHC.getInstance().getWorldManager();
    private final double damageActivation, pvpActivation, roleReveal, borderShrink;
    private int index = 0;

    public MainGameTask() {
        //TODO Reput the config values, but keep the * 60 to adapt to the tick system
        damageActivation = 1 * 60;
        pvpActivation = Math.floor(1.5 * 60);
        roleReveal = Math.floor(2 * 60);
        borderShrink = Math.floor(2.5 * 60);
    }

    BukkitRunnable mainTask = new BukkitRunnable() {

        //TODO include the broadcast messages

        @Override
        public void run() {

            if(gManager.isGameState(GameState.FROZEN)) return;

            if(index == damageActivation) {
                if(!gManager.isDamage()) {
                    gManager.setDamage(true);
                    Constant.silentBroadcast(Constant.getMessages("damageactivation"));
                }
            }

            if(index == roleReveal) {
                HunterUHC.getInstance().getTeamManager().revealRoles();
            }

            if(index == pvpActivation) {
                if(!gManager.isPvp()) gManager.setPvp(true);
                Constant.silentBroadcast(Constant.getMessages("pvpactivation"));
            }

            if(index == borderShrink) {
                wManager.startBorderShrink((int) Constant.getWorldParameter("border.finalsize"), (double) Constant.getWorldParameter("border.speed"));
                Constant.silentBroadcast(Constant.getMessages("bordershrinking"));
            }


        }
    };


    public BukkitRunnable getMainTask() { return mainTask; }

}
