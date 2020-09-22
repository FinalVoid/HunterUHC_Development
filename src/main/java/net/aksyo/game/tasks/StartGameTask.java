package net.aksyo.game.tasks;

import net.aksyo.Constant;
import net.aksyo.HunterUHC;
import net.aksyo.game.GameState;
import net.aksyo.game.managers.GameManager;
import org.bukkit.scheduler.BukkitRunnable;



public class StartGameTask {

    private int minimumPlayers, startTime;
    private HunterUHC hunterUHC = HunterUHC.getInstance();
    private GameManager gManager = hunterUHC.getGameManager();

    public StartGameTask() {
        startTime = 20; //TODO Reput the normal start time
        minimumPlayers = 1; //TODO Reput the normal minimum players
    }

    BukkitRunnable starter = new BukkitRunnable() {

        @Override
        public void run() {

            if (gManager.isGameState(GameState.FROZEN)) return;


            if (startTime > 10) {

                if (startTime % 10 == 0) {
                    Constant.silentBroadcast(Constant.getMessages("start").replace("%time%", String.valueOf(startTime)));
                }

            }

            if (startTime <= 10) {

                if (Constant.getPlayingPlayersAtStart().size() < minimumPlayers) {

                    Constant.silentBroadcast(Constant.getMessages("restart"));
                    startTime = Constant.getStartTime();
                }

                if(startTime == 1) {

                    hunterUHC.getWorldManager().teleportPlayers();
                    hunterUHC.getWorldManager().createWorldBorder(HunterUHC.getInstance().getServer().getWorlds().get(0));
                    gManager.setMovement(false);

                    hunterUHC.getEpisodeManager().runEpisodes();
                    hunterUHC.getTeamManager().distribute(true, gManager.isDebug());

                    getReleaseTask().runTaskTimer(hunterUHC, 20 ,20);

                    cancel();
                }

            }

            startTime--;

        }

    };

    public BukkitRunnable getStarter() {
        return starter;
    }

    private BukkitRunnable getReleaseTask() {

        return new BukkitRunnable() {

            int index = 10;

            @Override
            public void run() {

                if(index == 0) {

                    gManager.setMovement(true);
                    hunterUHC.getWorldManager().removeCages();

                    hunterUHC.getMainGameTask().getMainTask().runTaskTimer(hunterUHC, 20, 20);

                    cancel();
                }

                if(index <= 3 && index != 0) {
                    Constant.silentBroadcast("§6" + index);
                }

                index--;

            }
        };
    }

}
