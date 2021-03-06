package net.aksyo.game.managers;

import net.aksyo.Constant;
import net.aksyo.HunterUHC;
import net.aksyo.game.episodes.*;
import net.aksyo.player.UHCPlayer;
import org.bukkit.scheduler.BukkitRunnable;
import org.reflections.Reflections;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class EpisodeManager {

   private int index = 0;
   private List<IEpisode> sorted = new ArrayList<>();
   private boolean initialized = false;

   //Do not call this constructor more then once
   public EpisodeManager() {
       initializeEpisodes();
   }

    public void runEpisodes() {

        if(!sorted.isEmpty()) {

            sorted.forEach(e -> {

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Constant.silentBroadcast(e.getBroadcastedMessage());
                        Consumer<UHCPlayer> actions = e.getActions();
                        for(UHCPlayer player : HunterUHC.getInstance().getTeamManager().getUHCPlayers()) {
                            actions.accept(player);
                            if(HunterUHC.getInstance().getGameManager().isDebug()) HunterUHC.getInstance().log(e.getName() + " accepted");
                        }
                        if(HunterUHC.getInstance().getGameManager().isDebug()) HunterUHC.getInstance().log(e.getName() + " is active");
                    }
                }.runTaskLater(HunterUHC.getInstance(), e.getStartTime() * 1200);

            });

        }

    }

    public int getTimeAfterNextEpisode(IEpisode ep) {

        for (int i = 0; i < sorted.size(); i++) {

            if (sorted.get(i).getName().equalsIgnoreCase(ep.getName())) {

                try {
                    IEpisode a = ep, b = sorted.get(i + 1);
                    return b.getStartTime() - a.getStartTime();
                }catch (IndexOutOfBoundsException e) {
                    IEpisode a = ep, b = sorted.get(i - 1);
                    return a.getStartTime() - b.getStartTime();
                }

            }
        }

        return 0;
    }


    private void initializeEpisodes() {

       if(!initialized) {

           this.initialized = true;
           Reflections reflections = new Reflections("net.aksyo.game.episodes");

           final List<IEpisode> r = new ArrayList<>();

           reflections.getSubTypesOf(IEpisode.class).forEach(e -> {
               try {
                   r.add(e.newInstance());
               } catch (InstantiationException | IllegalAccessException exception) {
                   exception.printStackTrace();
               }
           });

           for (IEpisode iEpisode : r) {
               HunterUHC.getInstance().log("[EPISODE] REGISTERED : " + iEpisode.getName() + " ST : " + iEpisode.getStartTime());
           }

           sorted =  r.stream().filter(IEpisode::isActivated).sorted(new Comparator<IEpisode>() {
               @Override
               public int compare(IEpisode o1, IEpisode o2) {
                   return o1.getStartTime() - o2.getStartTime();
               }
           }).collect(Collectors.toList());
       }


    }




}
