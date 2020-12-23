package net.aksyo;

import net.aksyo.game.managers.TeamManager;
import net.aksyo.game.roles.Role;
import net.aksyo.game.roles.RoleType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public final class Constant {

    private static FileConfiguration config = HunterUHC.getInstance().getConfig();

    public final static String getHuntersTeamName() {
        return config.getString("game.teams.hunter");
    }

    public final static String getAntsTeamName() {
        return config.getString("game.teams.ants");
    }

    public final static String getPhantomTeamName() {
        return config.getString("game.teams.phantom");
    }

    public final static String getRoleName(String roleName) {
        return config.getString("game." + roleName.toLowerCase() + ".name");
    }

    public final static String[] getRoleInformation(String roleName) {
        return config.getString("game." + roleName.toLowerCase()  + ".information").split("%enter%");
    }

    public final static String getRoleDescription(String roleName) {
        return config.getString("game." + roleName.toLowerCase()  + ".description");
    }

    public final static boolean getRoleActivation(String roleName) {
        return config.getBoolean("game." + roleName.toLowerCase()  + ".activated");
    }

    public final static boolean getRoleDeathMessage(String roleName) {
        return config.getBoolean("game." + roleName.toLowerCase()  + ".deathmessage");
    }

    public final static boolean getRoleSole(String roleName) {
        return config.getBoolean("game." + roleName.toLowerCase() + "solo");
    }

    public final static int getRolePercentage(String roleName) {
        return config.getInt("game." + roleName.toLowerCase() + "percentage");
    }

    public final static GameMode getJoinGameMode() {
        return GameMode.valueOf(config.getString("config.join.gamemode").toUpperCase());
    }

    public final static String getAdminPrefix(boolean debug) {
        if(debug || HunterUHC.getInstance().getGameManager().isDebug()) {
            return "§c[ADMIN] §6[DEBUG] §r";
        }
        return "§c[ADMIN] §r";
    }

    public final static boolean getAutoStart() {
        return config.getBoolean("config.game.autostart");
    }

    public final static int getMinimumPlayers() {
        return config.getInt("config.join.minimumplayers");
    }

    public final static int getStartTime() {
        return config.getInt("config.join.starttime");
    }

    public final static String getMessages(String path) {
        return config.getString("messages.game." + path).replace("&", "§");
    }

    public final static Object getEpisodeParamater(String episodeName, String path) {
        return config.get("episodes." + episodeName + "." + path);
    }

    public final static Object getWorldParameter(String path) {
        return config.get("config.map." + path);
    }

    public final static double getGameTimeParameter(String path) {
        return config.getDouble("config.game." + path);
    }

    public final static String getBypassPermission(String path) {
        return config.getString("staff.bypass." + path);
    }

    public static List<String> getRoleRevealMessage() {

        return config.getStringList("messages.game.rolereveal");

    }

    public final static Object getAbilityName(String ability, String path) {
        return config.get("abilities." + ability + path);
    }

    public final static Set<RoleType> getAllowedRoles(String ability) {

        Set<RoleType> roles = new HashSet<>();

        for (String str : config.getStringList("abilities." + ability + ".roles")) {

            Arrays.stream(RoleType.values()).forEach(r -> {
                Role role = r.get();
                if (role.isActivated()) {
                    if (role.getName().equalsIgnoreCase(str)) {
                        roles.add(r);
                    }
                }

            });
        }

        return roles;

    }

    /**
     * Util functions
     */


    public final static void silentBroadcast(String message) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }

    }

    public static List<Player> getPlayingPlayersAtStart() {
        return Bukkit.getOnlinePlayers().stream().filter(p -> p.getGameMode() == Constant.getJoinGameMode()).collect(Collectors.toList());
    }

}
