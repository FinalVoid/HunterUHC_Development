package net.aksyo.game.roles.gameroles;

import net.aksyo.Constant;
import net.aksyo.game.roles.Role;
import net.aksyo.game.teams.Hunters;
import net.aksyo.game.teams.ITeam;

public class Hunter extends Role {

    public String getName() {
        return Constant.getRoleName("hunter");
    }

    public String[] getInformation() {
        return Constant.getRoleInformation("hunter");
    }

    @Override
    public String getDescription() {
        return Constant.getRoleDescription("hunter");
    }

    public boolean isActivated() {
        return Constant.getRoleActivation("hunter");
    }

    @Override
    public boolean isSolo() {
        return Constant.getRoleSole("hunter");
    }

    @Override
    public int getPercentage() {
        return Constant.getRolePercentage("hunter");
    }
    @Override
    public boolean playDeathMessage() {
        return Constant.getRoleDeathMessage("hunter");
    }

    public ITeam getTeam() {
        return Hunters.getInstance();
    }

    @Override
    public boolean debugSolo() {
        return false;
    }

    @Override
    public int debugPercentage() {
        return 30;
    }

    @Override
    public boolean debugActivated() {
        return true;
    }
}
