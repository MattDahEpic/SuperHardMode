package com.mattdahepic.superhardmode.event;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BreakEventHandler {
    private static final BreakEventHandler instance = new BreakEventHandler();
    public static void init () {
        MinecraftForge.EVENT_BUS.register(instance);
    }
    @SubscribeEvent
    public void placeEvent (BlockEvent.BreakEvent e) {
        handleSoftening(e);
    }
    private void handleSoftening (BlockEvent.BreakEvent e) {

    }
}
