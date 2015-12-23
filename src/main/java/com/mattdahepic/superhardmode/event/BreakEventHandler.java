package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.block.Fire;
import com.mattdahepic.superhardmode.block.Softening;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BreakEventHandler {
    private static final BreakEventHandler instance = new BreakEventHandler();
    public static void init () {
        MinecraftForge.EVENT_BUS.register(instance);
    }
    @SubscribeEvent
    public void breakEvent (BlockEvent.BreakEvent e) {
        Softening.handleSoftening(e);
        Fire.handleNetherrackFire(e);
    }
}
