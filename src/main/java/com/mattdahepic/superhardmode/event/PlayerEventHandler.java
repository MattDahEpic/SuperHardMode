package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.mobfeatures.Player;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerEventHandler {
    public static final PlayerEventHandler instance = new PlayerEventHandler();
    public static void init () {
        MinecraftForge.EVENT_BUS.register(instance);
    }
    @SubscribeEvent
    public void playerRespawnEvent (PlayerEvent.PlayerRespawnEvent e) {
        Player.handleReducedHealthHungerRespawning(e);
    }
}
