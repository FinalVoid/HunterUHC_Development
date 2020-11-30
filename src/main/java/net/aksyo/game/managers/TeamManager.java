package net.aksyo.game.managers;

import net.aksyo.Constant;
import net.aksyo.HunterUHC;
import net.aksyo.game.roles.Role;
import net.aksyo.game.roles.RoleType;
import net.aksyo.game.teams.Ants;
import net.aksyo.game.teams.Hunters;
import net.aksyo.game.teams.ITeam;
import net.aksyo.game.teams.Phantom;
import net.aksyo.player.UHCPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;



import java.util.*;
import java.util.stream.Collectors;


public class TeamManager {

    private final Map<ITeam, HashSet<UHCPlayer>> TEAMS = new HashMap<ITeam, HashSet<UHCPlayer>>();
    private final Map<Player, RoleType> deadPlayers = new HashMap<>();
    private final List<RoleType> gameRoles = new ArrayList<>();

    public TeamManager() {
        TEAMS.put(Hunters.getInstance(), new HashSet<UHCPlayer>());
        TEAMS.put(Ants.getInstance(), new HashSet<UHCPlayer>());
        TEAMS.put(Phantom.getInstance(), new HashSet<UHCPlayer>());
    }

    /**
     *
     * @param player The player you want to get under the uhcplayer format
     * @return the uhcplayer - checks the threads StrackTraceElements to check for any code errors || NullPointerEx
     */
    public UHCPlayer getUHCPlayer(Player player) {

        try {
            return TEAMS.values().stream().map(set -> set.stream().filter(uhcp -> uhcp.getPlayer().equals(player)).findFirst().get()).findAny().get();
        } catch (Exception e) {
            HunterUHC.getInstance().log("No UHCPlayer found");
            for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
                System.out.println(element.getClassName() + "   LINE   " + element.getLineNumber() + "   METHOD   " + element.getMethodName());
            }
            return null;
        }

    }

    /**
     *
     * @param team the Iteam you want to get
     * @return the team members under the uhcplayer format
     */
    public HashSet<UHCPlayer> getTeamMembers(ITeam team) {
        return TEAMS.get(team);
    }

    /**
     *
     * @return all the uhcplayers
     */
    public UHCPlayer[] getUHCPlayers() {

        //return TEAMS.values().stream().map(HashSet::iterator).collect(Collectors.toList()).toArray(new UHCPlayer[] {});

        int count = TEAMS.values().stream().mapToInt(HashSet::size).sum();

        UHCPlayer[] players = new UHCPlayer[count];

        int j = 0;

        for(Map.Entry<ITeam, HashSet<UHCPlayer>> entry : TEAMS.entrySet()) {
            for(UHCPlayer p : entry.getValue()) {
                players[j] = p;
                j++;
            }
        }

        return players;

    }

    /**
     *
     * @param player The player to create an uhcplayer
     * @param team The team of the uhcplayer
     * @param roleType The role of the uhcplayer
     */
    public void setTeam(Player player, ITeam team, RoleType roleType) {
        TEAMS.get(team).add(new UHCPlayer(player, roleType));
    }

    /**
     *
     * @param player The UHCPlayer you wish to remove from the TEAMS map (used when players are killed)
     */
    public void removePlayer(UHCPlayer player) {
        TEAMS.get(player.getTeam()).remove(player);
        deadPlayers.put(player.getPlayer(), player.getRole());
    }

    /**
     * Distributes the all the activated roles to the players
     */
    public void distribute(boolean complete, boolean instantDebug) {

        final boolean debug = HunterUHC.getInstance().getGameManager().isDebug() ? true : instantDebug;

        final List<Player> gamePlayers = new ArrayList<>(Bukkit.getOnlinePlayers()); //TODO gameplayers method

        if(debug) HunterUHC.getInstance().log(String.valueOf(gamePlayers.size()));

        final int players = gamePlayers.size();

        List<RoleType> availableRoles = new ArrayList<>();
        final long solos = Arrays.stream(RoleType.values()).filter(r -> r.get().isSolo()).count();

        for (RoleType r : RoleType.values()) {

            Role role = r.get();

            if (role.isActivated()) {
                if (role.isSolo()) {
                    availableRoles.add(r);
                } else {
                    for (int i = 0; i != Math.floor((players - solos) * (role.getPercentage() * 0.01)); i++) {
                        availableRoles.add(r);
                    }
                }
                if (debug) HunterUHC.getInstance().log(role.getName() + " was registered [" + role.getClass().getSimpleName() + "]. List size : " + availableRoles.size() + " [false]");
            }
        }
        if (availableRoles.size() != players && availableRoles.size() > players) {

            HunterUHC.getInstance().getGameManager().freezeGame(false);
            
            throw new IllegalStateException("Too much roles for the amount of players");

        } else if (availableRoles.size() != players && availableRoles.size() < players) {

            int diff = players - availableRoles.size();

            List<RoleType> randomRole = Arrays.stream(RoleType.values()).filter(rl -> {
                Role factor = rl.get();
                return factor.isActivated() && !factor.isSolo();
            }).collect(Collectors.toList());

            for (int i = 0; i != diff; i++) {

                int random = new Random().nextInt(randomRole.size());
                availableRoles.add(randomRole.get(random));
                if(debug) HunterUHC.getInstance().log(randomRole.get(random).get().getName() + " was registered [" + randomRole.get(random).get().getClass().getSimpleName() + "]. List size : " + availableRoles.size() + " [true]");

            }

        }

        gameRoles.addAll(availableRoles);

        if (complete) {

            if(instantDebug) HunterUHC.getInstance().log(String.valueOf(availableRoles.size()));

            for (Player player : gamePlayers) { //TODO finish gamePlayer method in constant

                int roleNumber = new Random().nextInt(availableRoles.size());
                setTeam(player, availableRoles.get(roleNumber).get().getTeam(), availableRoles.get(roleNumber));
                availableRoles.remove(roleNumber);

            }
        }

    }

    /**
     *
     * @return the list of all the roles used for the game
     */
    public List<RoleType> getGameRoles() {
        return gameRoles;
    }

    /**
     * Reveals the role a player has
     */
    public void revealRoles() {

        for (UHCPlayer player : getUHCPlayers()) {

            StringBuilder builder = new StringBuilder().append("\n");
            for(String str : Constant.getRoleRevealMessage()) {
                builder.append(str.replaceAll("&", "§")
                        .replaceAll("%role%", player.getRole().get().getName())
                        .replaceAll("%information%",  player.getRole().get().getInformation().toString())
                        .replaceAll("%description%",  player.getRole().get().getDescription())
                        .replaceAll("%team%", player.getTeam().getName()) + "\n");

            }
            player.getPlayer().sendMessage(builder.toString());

        }

    }

}
