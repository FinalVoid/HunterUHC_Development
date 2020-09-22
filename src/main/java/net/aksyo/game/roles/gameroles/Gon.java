package net.aksyo.game.roles.gameroles;

import net.aksyo.Constant;
import net.aksyo.game.roles.Role;
import net.aksyo.game.teams.Hunters;
import net.aksyo.game.teams.ITeam;

public class Gon extends Role {


    public String getName() {
        return Constant.getRoleName("gon");
    }

    public String[] getInformation() {
        return Constant.getRoleInformation("gon");
    }

    @Override
    public String getDescription() {
        return Constant.getRoleDescription("gon");
    }

    public boolean isActivated() {
        return Constant.getRoleActivation("gon");
    }

    @Override
    public boolean isSolo() {
        return Constant.getRoleSole("gon");
    }

    @Override
    public int getPercentage() {
        return Constant.getRolePercentage("gon");
    }

    @Override
    public boolean playDeathMessage() {
        return Constant.getRoleDeathMessage("gon");
    }

    public ITeam getTeam() {
        return Hunters.getInstance();
    }

    @Override
    public boolean debugSolo() {
        return true;
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
