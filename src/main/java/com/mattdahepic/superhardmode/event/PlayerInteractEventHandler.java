package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.worldfeatures.Fire;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerInteractEventHandler {
    private static final PlayerInteractEventHandler instance = new PlayerInteractEventHandler();
    public static void init () {
        MinecraftForge.EVENT_BUS.register(instance);
    }
    @SubscribeEvent
    public void playerInteractEvent (PlayerInteractEvent e) {
        Fire.handlePlayerSetOnFire(e);
    }
}
