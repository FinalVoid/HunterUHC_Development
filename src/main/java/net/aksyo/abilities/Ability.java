package net.aksyo.abilities;

import net.aksyo.game.roles.RoleType;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Ability {

    private static List<Ability> abilityList = new ArrayList<>();

    private String command;
    private RoleType[] allowedRoleTypes;

    public abstract void onCommand(Player player, String[] args);

    public Ability(String command, RoleType[] roleTypes) {
        this.command = command;
        this.allowedRoleTypes = roleTypes;
        abilityList.add(this);
    }

    public String getCommand() {
        return command;
    }

    public RoleType[] getAllowedRoleTypes() {
        return allowedRoleTypes;
    }

    public static List<Ability> getGetAbilityList() {
        return abilityList;
    }



}
