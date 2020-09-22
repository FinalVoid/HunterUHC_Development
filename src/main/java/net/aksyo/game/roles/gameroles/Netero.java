package net.aksyo.game.roles.gameroles;

import net.aksyo.Constant;
import net.aksyo.game.roles.Role;
import net.aksyo.game.teams.Hunters;
import net.aksyo.game.teams.ITeam;

public class Netero extends Role {

    public String getName() {
        return Constant.getRoleName("netero");
    }

    public String[] getInformation() {
        return Constant.getRoleInformation("netero");
    }

    @Override
    public String getDescription() {
        return Constant.getRoleDescription("netero");
    }

    public boolean isActivated() {
        return Constant.getRoleActivation("netero");
    }

    @Override
    public boolean isSolo() {
        return Constant.getRoleSole("netero");
    }

    @Override
    public int getPercentage() {
        return Constant.getRolePercentage("netero");
    }

    @Override
    public boolean playDeathMessage() {
        return Constant.getRoleDeathMessage("netero");
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
