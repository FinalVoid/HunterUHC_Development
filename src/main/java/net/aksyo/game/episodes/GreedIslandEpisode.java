package net.aksyo.game.episodes;

import net.aksyo.Constant;
import net.aksyo.HunterUHC;
import net.aksyo.game.managers.EpisodeManager;
import net.aksyo.game.teams.Hunters;
import net.aksyo.player.UHCPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.function.Consumer;

public class GreedIslandEpisode implements IEpisode {


    @Override
    public String getName() {
        return (String) Constant.getEpisodeParamater("greedisland", "name");
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getBroadcastedMessage() {
        return (String) Constant.getEpisodeParamater("greedisland", "broadcastedmessage").toString().replace("&", "§");
    }

    @Override
    public int getStartTime() {
        return (int) Constant.getEpisodeParamater("greedisland", "time");
    }

    @Override
    public boolean isActivated() {
        return (boolean) Constant.getEpisodeParamater("greedisland", "activated");
    }

    @Override
    public Consumer<UHCPlayer> getActions() {
        return (p) -> {
            if(p.getTeam() instanceof Hunters || HunterUHC.getInstance().getGameManager().isDebug()) {

                int pTime = HunterUHC.getInstance().getEpisodeManager().getTimeAfterNextEpisode(this) * 1200;

                Player player = p.getPlayer();
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, pTime, 1, false, false));
                player.sendMessage("§3You received speed! "  + pTime );
            }
        };
    }
}
