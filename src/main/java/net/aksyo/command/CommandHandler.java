package net.aksyo.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        } else {
            sender.sendMessage("§cYou need to be a player to execute this command");
            return true;
        }

        return false;
    }
}