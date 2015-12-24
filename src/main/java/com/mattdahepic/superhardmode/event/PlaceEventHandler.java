package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.worldfeatures.LimitedBuilding;
import com.mattdahepic.superhardmode.worldfeatures.Torch;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlaceEventHandler {
    private static final PlaceEventHandler instance = new PlaceEventHandler();
    public static void init () {
        MinecraftForge.EVENT_BUS.register(instance);
    }
    @SubscribeEvent
    public void placeEvent (BlockEvent.PlaceEvent e) {
        Torch.handleTorchPlacement(e);
        LimitedBuilding.handleAntiNerdPole(e);
    }
}
