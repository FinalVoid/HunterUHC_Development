package net.aksyo.game.roles.gameroles;

import net.aksyo.Constant;
import net.aksyo.game.roles.Role;
import net.aksyo.game.teams.Ants;
import net.aksyo.game.teams.ITeam;

public class Meruem extends Role {

    public String getName() {
        return Constant.getRoleName("meruem");
    }

    public String[] getInformation() {
        return Constant.getRoleInformation("meruem");
    }

    @Override
    public String getDescription() {
        return Constant.getRoleDescription("meruem");
    }

    public boolean isActivated() {
        return Constant.getRoleActivation("meruem");
    }

    @Override
    public boolean isSolo() {
        return Constant.getRoleSole("meruem");
    }

    @Override
    public int getPercentage() {
        return Constant.getRolePercentage("meruem");
    }

    @Override
    public boolean playDeathMessage() {
        return Constant.getRoleDeathMessage("meruem");
    }

    @Override
    public ITeam getTeam() {
        return Ants.getInstance();
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
