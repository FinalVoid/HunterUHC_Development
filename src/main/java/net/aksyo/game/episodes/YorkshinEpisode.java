package net.aksyo.game.episodes;

import net.aksyo.Constant;
import net.aksyo.HunterUHC;
import net.aksyo.game.managers.EpisodeManager;
import net.aksyo.game.teams.Hunters;
import net.aksyo.game.teams.Phantom;
import net.aksyo.player.UHCPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.function.Consumer;

public class YorkshinEpisode implements IEpisode {

    @Override
    public String getName() {
        return (String) Constant.getEpisodeParamater("yorkshin", "name");
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getBroadcastedMessage() {
        return (String) Constant.getEpisodeParamater("yorkshin", "broadcastedmessage").toString().replace("&", "§");
    }

    @Override
    public int getStartTime() {
        return (int) Constant.getEpisodeParamater("yorkshin", "time");
    }

    @Override
    public boolean isActivated() {
        return (boolean) Constant.getEpisodeParamater("yorkshin", "activated");
    }

    @Override
    public Consumer<UHCPlayer> getActions() {
        return (p) -> {
            if(p.getTeam() instanceof Phantom || HunterUHC.getInstance().getGameManager().isDebug()) {

                int pTime = HunterUHC.getInstance().getEpisodeManager().getTimeAfterNextEpisode(this) * 1200;

                Player player = p.getPlayer();
                player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, pTime, 1, false, false));
                player.sendMessage("§3You received fast digging! " + pTime);
            }
        };
    }
}
