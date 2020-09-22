package net.aksyo.game.episodes;

import net.aksyo.player.UHCPlayer;


import java.util.function.Consumer;
import java.util.function.Supplier;

public interface IEpisode {

    String getName();

    String getDescription();

    String getBroadcastedMessage();

    int getStartTime();

    boolean isActivated();

    Consumer<UHCPlayer> getActions();



}
