package net.aksyo.abilities;

import net.aksyo.game.roles.RoleType;
import net.aksyo.player.UHCPlayer;
import net.aksyo.utils.Pair;
import org.bukkit.entity.Player;

import java.util.*;

public abstract class Ability {

    private static List<Ability> abilityList = new ArrayList<>();
    private static Map<Ability, Map<UHCPlayer, Pair<Boolean, Integer>>> abilityManager = new HashMap<>();

    private String command;
    private Set<RoleType> allowedRoleTypes;
    private int times;

    public abstract void onCommand(Player player, String[] args);

    public Ability(String command, Set<RoleType> roleTypes) {
        this.command = command;
        this.allowedRoleTypes = roleTypes;
        abilityList.add(this);
    }

    public String getCommand() {
        return command;
    }

    public Set<RoleType> getAllowedRoleTypes() {
        return allowedRoleTypes;
    }

    public int getTimes() {
        return times;
    }

    public static List<Ability> getGetAbilityList() {
        return abilityList;
    }

    public static Map<Ability, Map<UHCPlayer, Pair<Boolean, Integer>>> getAbilityManager() {
        return abilityManager;
    }
}
