package net.aksyo.abilities;

import net.aksyo.Constant;
import net.aksyo.game.roles.RoleType;
import org.bukkit.entity.Player;

public class GonAttackAbility extends Ability {


    public GonAttackAbility() {
        super("attack", Constant.getAllowedRoles("gonattack"));
    }

    @Override
    public void onCommand(Player player, String[] args) {



    }
}
