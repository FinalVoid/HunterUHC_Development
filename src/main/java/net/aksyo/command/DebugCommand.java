package net.aksyo.command;

import net.aksyo.Constant;
import net.aksyo.HunterUHC;
import net.aksyo.game.episodes.IEpisode;
import net.aksyo.game.managers.EpisodeManager;
import net.aksyo.game.tasks.MainGameTask;
import net.aksyo.game.tasks.StartGameTask;
import net.aksyo.player.UHCPlayer;
import net.aksyo.utils.HunterGUI;
import net.aksyo.utils.HunterItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.reflections.Reflections;

import java.util.concurrent.atomic.AtomicInteger;

public class DebugCommand extends HCommand {


    public DebugCommand() {
        super("debug", "Puts the game in debug mode", true);
    }

    @Override
    public void onCommand(Player player, String[] args) {

        if (args.length == 1) {
            if(HunterUHC.getInstance().getGameManager().isDebug()) return;
            player.sendMessage(Constant.getAdminPrefix(false) + "§bDebug is now activated!");
            HunterUHC.getInstance().getGameManager().activateDebug();
            return;

        }

        switch (args[1]) {
            case "distribute" :
                if (args[2].equalsIgnoreCase("true")) {
                    launchDistributeDebug(true, player);
                } else if (args[2].equalsIgnoreCase("false")) {
                    launchDistributeDebug(false, player);
                }
                break;
            case "start" :
                if(args.length == 3) {
                    StartGameTask task = HunterUHC.getInstance().getStartGameTask();
                    if (args[2].equalsIgnoreCase("stop")) {
                        try {
                            if (Bukkit.getScheduler().isQueued(task.getStarter().getTaskId()) || Bukkit.getScheduler().isCurrentlyRunning(task.getStarter().getTaskId())) {

                                HunterUHC.getInstance().getStartGameTask().getStarter().cancel();
                                player.sendMessage(Constant.getAdminPrefix(true) + "§cStopped start task");

                            }
                        } catch (IllegalStateException e) {
                            player.sendMessage(Constant.getAdminPrefix(true) + "§cNo task running");
                        }
                        break;

                    }
                }
                HunterUHC.getInstance().getWorldManager().initializeMap(player.getLocation(), (Integer) Constant.getWorldParameter("border.size"));
                launchStartDebug(player);
                break;

            case "main" :
                if(args.length == 3) {
                    MainGameTask task = HunterUHC.getInstance().getMainGameTask();
                    if (args[2].equalsIgnoreCase("stop")) {
                        try {
                            if (Bukkit.getScheduler().isQueued(task.getMainTask().getTaskId()) || Bukkit.getScheduler().isCurrentlyRunning(task.getMainTask().getTaskId())) {

                                HunterUHC.getInstance().getMainGameTask().getMainTask().cancel();
                                player.sendMessage(Constant.getAdminPrefix(true) + "§cStopped main task");

                            }
                        } catch (IllegalStateException e) {
                            player.sendMessage(Constant.getAdminPrefix(true) + "§cNo task running");
                        }
                        break;

                    }
                }
                launchMainDebug(player);
                break;


            case "border" :
                if (args.length == 3 && args[2].equalsIgnoreCase("shrink")) {
                    HunterUHC.getInstance().getWorldManager().startBorderShrink(10, 2);
                    break;
                } else {
                    HunterUHC.getInstance().getWorldManager().deactivateBorder();
                    player.sendMessage(Constant.getAdminPrefix(true) + "§2De-activated border");
                }
                break;

            case "see" :
                player.sendMessage("§6List of all UHCPlayers : Size §c" + HunterUHC.getInstance().getTeamManager().getUHCPlayers().length);
                for (UHCPlayer uhcPlayer : HunterUHC.getInstance().getTeamManager().getUHCPlayers()) {
                    player.sendMessage("§5" + player.getName());
                }
                break;

            case "episodes" :

                if (args.length == 2) {
                    new EpisodeManager().runEpisodes();
                    player.sendMessage(Constant.getAdminPrefix(true) + "§aLaunched Episode Debug");
                    break;
                } else if (args.length == 3) {
                    launchSingleEpisode(args[2]);
                    player.sendMessage(Constant.getAdminPrefix(true) + "§aLaunched Episode Debug §6" + args[2]);
                    break;
                }

            case "roles" :
                roleGui(player);
                break;

            case "reveal" :
                HunterUHC.getInstance().getTeamManager().revealRoles();
                break;

        }

    }


    private void launchDistributeDebug(boolean complete, Player player) {
        player.sendMessage(Constant.getAdminPrefix(true) + "§bLaunching distribute debug §e" + complete);
        new BukkitRunnable() {
            @Override
            public void run() {
                HunterUHC.getInstance().getTeamManager().distribute(complete, true);

                AtomicInteger phantom = new AtomicInteger();
                AtomicInteger royal = new AtomicInteger();
                AtomicInteger ant = new AtomicInteger();
                AtomicInteger hunter = new AtomicInteger();

                HunterUHC.getInstance().getTeamManager().getGameRoles().forEach(r -> {
                    switch (r) {

                        case HUNTER:
                            hunter.getAndIncrement();
                            break;
                        case PHANTOM:
                            phantom.getAndIncrement();
                            break;
                        case ROYALGUARD:
                            royal.getAndIncrement();
                            break;
                        case ANT:
                            ant.getAndIncrement();
                            break;
                    }
                });

                player.sendMessage(" ");
                player.sendMessage("§eNumber of phantom troop : §b" + phantom.get());
                player.sendMessage("§eNumber of hunters : §b" + hunter.get());
                player.sendMessage("§eNumber of ants : §b" + ant.get());
                player.sendMessage("§eNumber of royal guards : §b" + royal.get());


            }
        }.runTaskLater(HunterUHC.getInstance(), 40);
    }

    private void launchStartDebug(Player player) {
        player.sendMessage(Constant.getAdminPrefix(true) + "§bLaunching start task");
        new BukkitRunnable() {

            @Override
            public void run() {

                HunterUHC.getInstance().getStartGameTask().getStarter().runTaskTimer(HunterUHC.getInstance(), 20, 20);
                player.sendMessage(Constant.getAdminPrefix(true) + "§aLaunched start task");

            }

        }.runTaskLater(HunterUHC.getInstance(), 40);
    }

    private void launchMainDebug(Player player) {
        player.sendMessage(Constant.getAdminPrefix(true) + "§bLaunching main task");
        new BukkitRunnable() {

            @Override
            public void run() {

                HunterUHC.getInstance().getMainGameTask().getMainTask().runTaskTimer(HunterUHC.getInstance(), 20, 20);
                player.sendMessage(Constant.getAdminPrefix(true) + "§aLaunched main task");

            }

        }.runTaskLater(HunterUHC.getInstance(), 40);
    }


    private void launchSingleEpisode(String name) {

        Reflections reflections = new Reflections("net.aksyo.game.episodes");
        Class<? extends IEpisode> ep = reflections.getSubTypesOf(IEpisode.class).stream().filter(e -> e.getSimpleName().equalsIgnoreCase(name)).findFirst().get();

        try {
            IEpisode episode = ep.newInstance();
            for (UHCPlayer player : HunterUHC.getInstance().getTeamManager().getUHCPlayers()) {
                episode.getActions().accept(player);
            }

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    private void roleGui(Player player) {

        HunterGUI gui = new HunterGUI(HunterUHC.getInstance(), "§2Roles", 8);
        int slot = 0;

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();

        for(UHCPlayer uhcPlayer : HunterUHC.getInstance().getTeamManager().getUHCPlayers()) {
            meta.setOwner(uhcPlayer.getPlayer().getName());
            gui.setItem(slot, new HunterItem(skull).name("§3" + uhcPlayer.getPlayer().getDisplayName()).lore(
                    " ",
                    "§bTeam : §e" + uhcPlayer.getTeam().getName(),
                    "§bRole : §e" + uhcPlayer.getRole().get().getName()
            ).create(), (player1, inventoryClickEvent) -> {
                player.closeInventory();
                player.sendMessage(" ");
                player.sendMessage("§bTeam : §e" + uhcPlayer.getTeam().getName());
                player.sendMessage("§bRole : §e" + uhcPlayer.getRole().get().getName());
                player.sendMessage("§bSolo : §e" + uhcPlayer.getRole().get().isSolo());

            });

            slot++;

        }

        gui.setLocked(true);
        gui.open(player);

    }


}
