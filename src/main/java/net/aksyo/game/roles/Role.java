package net.aksyo.game.roles;

import net.aksyo.HunterUHC;
import net.aksyo.game.teams.ITeam;
import net.aksyo.player.UHCPlayer;

public abstract class Role {

    /**
     *
     * @return The roles name
     */
    public abstract String getName();

    /**
     *
     * @return The roles total information
     */
    public abstract String[] getInformation();

    /**
     *
     * @return Resume of the roles information
     */
    public abstract String getDescription();

    /**
     *
     * @return if the role is activated during the game
     */
    public abstract boolean isActivated();

    /**
     *
     * @return If the role can only be attributed to one person per game
     */
    public abstract boolean isSolo();

    /**
     *
     * @return the percentage of player that will get role
     */
    public abstract int getPercentage();

    /**
     *
     * @return if a death message shows up when the player dies
     */
    public abstract boolean playDeathMessage();

    /**
     *
     * @return the role's team
     */
    public abstract ITeam getTeam();

    /**
     * Reveals everyones role
     */
    public static void reveal() {

        for(UHCPlayer player : HunterUHC.getInstance().getTeamManager().getUHCPlayers()) {


        }
    }


    /*
    DEBUG METHODS
     */

    public abstract boolean debugSolo();

    public abstract int debugPercentage();

    public abstract boolean debugActivated();


}
