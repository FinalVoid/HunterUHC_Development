package net.aksyo.game.roles.gameroles;

import net.aksyo.Constant;
import net.aksyo.game.roles.Role;
import net.aksyo.game.teams.Ants;
import net.aksyo.game.teams.ITeam;


public class Ant extends Role {

    public String getName() {
        return Constant.getRoleName("ant");
    }

    public String[] getInformation() {
        return Constant.getRoleInformation("ant");
    }

    @Override
    public String getDescription() {
        return Constant.getRoleDescription("ant");
    }

    public boolean isActivated() {
        return Constant.getRoleActivation("ant");
    }

    @Override
    public boolean isSolo() {
        return Constant.getRoleSole("ant");
    }

    @Override
    public boolean playDeathMessage() {
        return Constant.getRoleDeathMessage("ant");
    }

    @Override
    public int getPercentage() {
        return Constant.getRolePercentage("ant");
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
        return 50;
    }

    @Override
    public boolean debugActivated() {
        return true;
    }
}
