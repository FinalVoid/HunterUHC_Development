package net.aksyo.game.managers;

import net.aksyo.HunterUHC;
import net.aksyo.game.roles.gameroles.Hunter;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class WorldManager {

    private List<Cage> CAGES = new ArrayList<>();

    public WorldManager() {

    }

    private Location center;
    private int width;
    private World world;
    private WorldBorder border;

    public void initializeMap(Location center, int width) {

        this.center = center;
        this.width = width;
        //TODO make border and map via config (constant)

    }

    public void teleportPlayers() {

        List<Player> playersList = new ArrayList<>(Bukkit.getOnlinePlayers());

        for (double angle = 0, inc = 360 / playersList.size(); angle < 360; angle += inc) {
            double x = Math.sin(angle) * (width - 3);
            double z = Math.cos(angle) * (width - 3);
            if (playersList.size() > 0) {
                new Cage(center.clone().add(x, 3, z)).setCage();
                playersList.remove(0).teleport(center.clone().add(x + 0.5, 4, z + 0.5));
            }
        }
    }

    public void removeCages() {

        CAGES.forEach(Cage::removeCage);

    }

    public void createWorldBorder(World world) {
        this.world = world;
        this.border = world.getWorldBorder();

        border.setCenter(center);
        border.setSize(width * 2);

        border.setWarningDistance(5);
    }

    public void deactivateBorder() {

        HunterUHC.getInstance().getServer().getWorlds().get(0).getWorldBorder().reset();

    }

    //Speed in block per second
    public void startBorderShrink(int finalWidth, double speed) {

        double time = Math.floor((width - finalWidth) / 2);

        border.setSize(finalWidth, (long) time);

    }


    class Cage {

        private Location cageCenter;

        public Cage(Location cageCenter) {
            this.cageCenter = cageCenter;
            CAGES.add(this);
        }

        public void setCage() {

            cageCenter.subtract(0, 1, 0).getBlock().setType(Material.IRON_BLOCK);
            cageCenter.clone().add(1, 0, 0).getBlock().setType(Material.IRON_BLOCK);
            cageCenter.clone().add(0, 0, 1).getBlock().setType(Material.IRON_BLOCK);
            cageCenter.clone().subtract(0,0, 1).getBlock().setType(Material.IRON_BLOCK);
            cageCenter.clone().subtract(1,0, 0).getBlock().setType(Material.IRON_BLOCK);

        }

        public void removeCage() {
            cageCenter.getBlock().setType(Material.AIR);
            cageCenter.clone().add(1, 0, 0).getBlock().setType(Material.AIR);
            cageCenter.clone().add(0, 0, 1).getBlock().setType(Material.AIR);
            cageCenter.clone().subtract(0,0, 1).getBlock().setType(Material.AIR);
            cageCenter.clone().subtract(1,0, 0).getBlock().setType(Material.AIR);
        }



    }




}
