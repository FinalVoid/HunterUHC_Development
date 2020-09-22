package net.aksyo.command;

import net.aksyo.Constant;
import net.aksyo.HunterUHC;
import net.aksyo.game.GameState;
import org.bukkit.entity.Player;

public class FreezeGameCommand extends HCommand {


    public FreezeGameCommand() {
        super("freeze", "Freezes the game", true);
    }

    @Override
    public void onCommand(Player player, String[] args) {

        if (args.length == 1) {
            if(HunterUHC.getInstance().getGameManager().isGameState(GameState.FROZEN)) {
                HunterUHC.getInstance().getGameManager().unfreezeGame();
                player.sendMessage(Constant.getAdminPrefix(false) + "§bGame is now §cunfreezed");
                return;
            }
            HunterUHC.getInstance().getGameManager().freezeGame(true);
            player.sendMessage(Constant.getAdminPrefix(false) + "§bGame is now freezed");
            return;
        }

        if (args[1].equalsIgnoreCase("stop")) {
            HunterUHC.getInstance().getServer().getPluginManager().disablePlugin(HunterUHC.getInstance());
            return;
        }


    }
}
