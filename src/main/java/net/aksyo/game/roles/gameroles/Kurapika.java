package net.aksyo.game.roles.gameroles;

import net.aksyo.Constant;
import net.aksyo.game.roles.Role;
import net.aksyo.game.teams.Hunters;
import net.aksyo.game.teams.ITeam;

public class Kurapika extends Role {

    public String getName() {
        return Constant.getRoleName("kurapika");
    }

    public String[] getInformation() {
        return Constant.getRoleInformation("kurapika");
    }

    @Override
    public String getDescription() {
        return Constant.getRoleDescription("kurapika");
    }

    public boolean isActivated() {
        return Constant.getRoleActivation("kurapika");
    }

    @Override
    public boolean isSolo() {
        return Constant.getRoleSole("kurapika");
    }

    @Override
    public int getPercentage() {
        return Constant.getRolePercentage("kurapika");
    }

    @Override
    public boolean playDeathMessage() {
        return Constant.getRoleDeathMessage("kurapika");
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
