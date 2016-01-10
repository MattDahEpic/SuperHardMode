package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.mobfeatures.Player;
import com.mattdahepic.superhardmode.worldfeatures.Fire;
import com.mattdahepic.superhardmode.worldfeatures.Misc;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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
    @SubscribeEvent
    public void playerInteractEvent (PlayerInteractEvent e) {
        Fire.handlePlayerSetOnFire(e);
        Misc.cactusBreakDamagePlayer(e);
    }
}
