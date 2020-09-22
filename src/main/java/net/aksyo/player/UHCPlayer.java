package net.aksyo.player;


import net.aksyo.game.roles.RoleType;
import net.aksyo.game.teams.ITeam;
import org.bukkit.entity.Player;

public class UHCPlayer {

    private Player player;
    private RoleType role;

    public UHCPlayer(Player player, RoleType role) {
        this.player = player;
        this.role = role;
    }

    public Player getPlayer() {
        return player;
    }

    public RoleType getRole() {
        return role;
    }

    public ITeam getTeam() {
        return role.get().getTeam();
    }


}
