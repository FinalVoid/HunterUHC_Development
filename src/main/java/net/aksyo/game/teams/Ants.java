package net.aksyo.game.teams;

import net.aksyo.Constant;
import net.aksyo.game.roles.Role;
import net.aksyo.game.roles.gameroles.Ant;
import net.aksyo.game.roles.gameroles.Meruem;
import net.aksyo.game.roles.gameroles.RoyalGuard;

public class Ants implements ITeam {

    private final static Ants INSTANCE = new Ants();

    @Override
    public String getName() {
        return Constant.getAntsTeamName();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Role[] getRoles() {
        return new Role[] {new Ant(), new RoyalGuard(), new Meruem() };
    }

    public final static Ants getInstance() {
        return INSTANCE;
    }

}
