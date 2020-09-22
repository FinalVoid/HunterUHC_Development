package net.aksyo;

import net.aksyo.command.CommandHandler;
import net.aksyo.game.managers.EpisodeManager;
import net.aksyo.game.managers.GameManager;
import net.aksyo.game.managers.TeamManager;
import net.aksyo.game.managers.WorldManager;
import net.aksyo.game.tasks.MainGameTask;
import net.aksyo.game.tasks.StartGameTask;
import net.aksyo.listeners.EntityDamageListener;
import net.aksyo.listeners.PlayerMoveListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;
import java.util.logging.Logger;

public class HunterUHC extends JavaPlugin {

    private static HunterUHC instance;

    private Logger logger = getServer().getLogger();
    private PluginManager pm = getServer().getPluginManager();
    private GameManager gameManager;
    private TeamManager teamManager;
    private WorldManager worldManager;
    private EpisodeManager episodeManager;

    private StartGameTask startGameTask;
    private MainGameTask mainGameTask;

    private File roleDirectory = new File(this.getDataFolder() + "/roles");


    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getCommand("hunter").setExecutor(new CommandHandler());

        gameManager = new GameManager();
        teamManager = new TeamManager();
        episodeManager = new EpisodeManager();
        worldManager = new WorldManager();


        startGameTask = new StartGameTask();
        mainGameTask = new MainGameTask();

        registerEvents();
        createDirectory();

        /*if (Constant.getAutoStart()) {
            startGameTask.getStarter().runTaskTimer(this, 20, 20);
        }*/
    }

    @Override
    public void onDisable() {

    }

    public final static HunterUHC getInstance() {
        return instance;
    }

    public void log(String... log) {
        for(String l : log) {
            logger.info(l);
        }
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }

    public EpisodeManager getEpisodeManager() { return episodeManager; }


    public StartGameTask getStartGameTask() {
        return startGameTask;
    }

    public MainGameTask getMainGameTask() {
        return mainGameTask;
    }

    private void registerEvents() {

        pm.registerEvents(new EntityDamageListener(), this);
        pm.registerEvents(new PlayerMoveListener(), this);

    }

    private void createDirectory() {

        if(roleDirectory.exists()) roleDirectory.mkdirs();

    }
}
