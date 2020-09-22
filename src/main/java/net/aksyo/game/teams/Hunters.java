package net.aksyo.game.teams;

import net.aksyo.Constant;
import net.aksyo.game.roles.Role;
import net.aksyo.game.roles.gameroles.*;

public class Hunters implements ITeam {

    private final static Hunters INSTANCE = new Hunters();

    @Override
    public String getName() {
        return Constant.getHuntersTeamName();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Role[] getRoles() {
        return new Role[] {new Gon(), new Killua(), new Kurapika(), new Netero(), new Hunter()};
    }

    public final static Hunters getInstance() {
        return INSTANCE;
    }
}
