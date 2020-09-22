package net.aksyo.game.teams;

import net.aksyo.game.roles.Role;

public interface ITeam {

    String getName();
    String getDescription();
    Role[] getRoles();

}
