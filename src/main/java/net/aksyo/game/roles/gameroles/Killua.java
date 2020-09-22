package net.aksyo.game.roles.gameroles;

import net.aksyo.Constant;
import net.aksyo.game.roles.Role;
import net.aksyo.game.teams.Hunters;
import net.aksyo.game.teams.ITeam;

public class Killua extends Role {

    public String getName() {
        return Constant.getRoleName("killua");
    }

    public String[] getInformation() {
        return Constant.getRoleInformation("killua");
    }

    @Override
    public String getDescription() {
        return Constant.getRoleDescription("killua");
    }

    public boolean isActivated() {
        return Constant.getRoleActivation("killua");
    }

    @Override
    public boolean isSolo() {
        return Constant.getRoleSole("killua");
    }

    @Override
    public int getPercentage() {
        return Constant.getRolePercentage("killua");
    }

    @Override
    public boolean playDeathMessage() {
        return Constant.getRoleDeathMessage("killua");
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
