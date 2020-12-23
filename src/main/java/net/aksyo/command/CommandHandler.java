package net.aksyo.command;

import net.aksyo.HunterUHC;
import net.aksyo.abilities.Ability;
import net.aksyo.player.UHCPlayer;
import net.aksyo.utils.Pair;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class CommandHandler implements CommandExecutor {

    public CommandHandler() {
        new DebugCommand();
        new FreezeGameCommand();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length < 1) {
                //player.sendMessage(Constant.getCommandHelpMessage());
                return true;
            }

            for(HCommand hCommand : HCommand.getHCommandList()) {
                if(args[0].equalsIgnoreCase(hCommand.getCommand())) {
                    if(hCommand.isAdmin() && player.isOp()) {
                        hCommand.onCommand(player, args);
                        return true;
                    } else if(!hCommand.isAdmin()) {
                        hCommand.onCommand(player, args);
                        return true;
                    }
                }
            }

            for (Ability ability : Ability.getGetAbilityList()) {
                if (args[0].equalsIgnoreCase(ability.getCommand())) {
                    UHCPlayer uhcPlayer = HunterUHC.getInstance().getTeamManager().getUHCPlayer(player);
                    if (uhcPlayer != null) {
                        if (ability.getAllowedRoleTypes().stream().anyMatch(r -> r == uhcPlayer.getRole())) {
                            if (Ability.getAbilityManager().get(ability).get(uhcPlayer).getRight()) {
                                ability.onCommand(player, args);
                                handleAbilityCancel(ability, uhcPlayer, Ability.getAbilityManager().get(ability).get(uhcPlayer).getLeft());
                                return true;
                            }
                        }
                    }
                }
            }

        } else {
            sender.sendMessage("§cYou need to be a player to execute this command");
            return true;
        }

        return false;
    }

    protected void handleAbilityCancel(Ability ability, UHCPlayer uhcPlayer, int times) {

        if (Ability.getAbilityManager().get(ability).get(uhcPlayer).getLeft() == ability.getTimes()) {
            Ability.getAbilityManager().get(ability).replace(uhcPlayer, Pair.of(false, times));
            return;
        }

        Ability.getAbilityManager().get(ability).replace(uhcPlayer, Pair.of(true, times++));

    }
}
