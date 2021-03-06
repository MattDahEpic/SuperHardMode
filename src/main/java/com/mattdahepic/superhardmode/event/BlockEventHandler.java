package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.farmingfeatures.AntiFarming;
import com.mattdahepic.superhardmode.worldfeatures.*;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockEventHandler {
    private static final BlockEventHandler instance = new BlockEventHandler();
    public static void init () {
        MinecraftForge.EVENT_BUS.register(instance);
    }
    @SubscribeEvent
    public void breakEvent (BlockEvent.BreakEvent e) {
        Misc.netherrackBreakFire(e);
        Collapse.handleCollapse(e);
    }
    @SubscribeEvent
    public void placeEvent (BlockEvent.PlaceEvent e) {
        Torch.handleTorchPlacement(e);
        LimitedBuilding.handleAntiNerdPole(e);
        Collapse.handleCollapse(e);
        AntiFarming.netherwartFarming(e);
    }
    @SubscribeEvent
    public void blockDropsEvent (BlockEvent.HarvestDropsEvent e) {
        AntiFarming.netherwartFarming(e);
    }
}
