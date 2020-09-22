package net.aksyo.game.episodes;

import net.aksyo.Constant;
import net.aksyo.HunterUHC;
import net.aksyo.game.managers.EpisodeManager;
import net.aksyo.game.teams.Ants;
import net.aksyo.player.UHCPlayer;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.function.Consumer;

public class AntsInvasionEpisode implements IEpisode {


    @Override
    public String getName() {
        return (String) Constant.getEpisodeParamater("antsinvasion", "name");
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getBroadcastedMessage() {
        return (String) Constant.getEpisodeParamater("antsinvasion", "broadcastedmessage").toString().replace("&", "§");
    }

    @Override
    public int getStartTime() {
        return (int) Constant.getEpisodeParamater("antsinvasion", "time");
    }

    @Override
    public boolean isActivated() {
        return (boolean) Constant.getEpisodeParamater("antsinvasion", "activated");
    }

    @Override
    public Consumer<UHCPlayer> getActions() {
        return (p) -> {
            if(p.getTeam() instanceof Ants || HunterUHC.getInstance().getGameManager().isDebug()) {

                int pTime = HunterUHC.getInstance().getEpisodeManager().getTimeAfterNextEpisode(this)  * 1200;

                Player player = p.getPlayer();
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, pTime, 0, false, false));
                player.sendMessage("§3You received force! "  + pTime);
            }
        };
    }
}
