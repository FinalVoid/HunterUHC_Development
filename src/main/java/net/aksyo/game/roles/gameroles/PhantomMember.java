package net.aksyo.game.roles.gameroles;

import net.aksyo.Constant;
import net.aksyo.game.roles.Role;
import net.aksyo.game.teams.ITeam;
import net.aksyo.game.teams.Phantom;

public class PhantomMember extends Role {

    public String getName() {
        return Constant.getRoleName("phantom");
    }

    public String[] getInformation() {
        return Constant.getRoleInformation("phantom");
    }

    @Override
    public String getDescription() {
        return Constant.getRoleDescription("phantom");
    }

    public boolean isActivated() {
        return Constant.getRoleActivation("phantom");
    }

    @Override
    public boolean isSolo() {
        return Constant.getRoleSole("phantom");
    }

    @Override
    public int getPercentage() {
        return Constant.getRolePercentage("phantom");
    }

    @Override
    public boolean playDeathMessage() {
        return Constant.getRoleDeathMessage("phantom");
    }

    @Override
    public ITeam getTeam() {
        return Phantom.getInstance();
    }

    @Override
    public boolean debugSolo() {
        return false;
    }

    @Override
    public int debugPercentage() {
        return 20;
    }

    @Override
    public boolean debugActivated() {
        return true;
    }
}
