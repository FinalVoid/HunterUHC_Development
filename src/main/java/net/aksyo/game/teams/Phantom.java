package net.aksyo.game.teams;

import net.aksyo.Constant;
import net.aksyo.game.roles.Role;
import net.aksyo.game.roles.gameroles.PhantomMember;

public class Phantom implements ITeam {

    private final static Phantom INSTANCE = new Phantom();

    @Override
    public String getName() {
        return Constant.getPhantomTeamName();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public Role[] getRoles() {
        return new Role[] {new PhantomMember() };
    }

    public final static Phantom getInstance() {
        return INSTANCE;
    }
}
