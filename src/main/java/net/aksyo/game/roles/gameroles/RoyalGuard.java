package net.aksyo.game.roles.gameroles;

import net.aksyo.Constant;
import net.aksyo.game.roles.Role;
import net.aksyo.game.teams.Ants;
import net.aksyo.game.teams.ITeam;

public class RoyalGuard extends Role {

    public String getName() {
        return Constant.getRoleName("royalguard");
    }

    public String[] getInformation() {
        return Constant.getRoleInformation("royalguard");
    }

    @Override
    public String getDescription() {
        return Constant.getRoleDescription("royalguard");
    }

    public boolean isActivated() {
        return Constant.getRoleActivation("royalguard");
    }

    @Override
    public boolean isSolo() {
        return Constant.getRoleSole("royalguard");
    }

    @Override
    public int getPercentage() {
        return Constant.getRolePercentage("royalguard");
    }

    @Override
    public boolean playDeathMessage() {
        return Constant.getRoleDeathMessage("royalguard");
    }

    @Override
    public ITeam getTeam() {
        return Ants.getInstance();
    }

    @Override
    public boolean debugSolo() {
        return false;
    }

    @Override
    public int debugPercentage() {
        return 0;
    }

    @Override
    public boolean debugActivated() {
        return true;
    }
}
