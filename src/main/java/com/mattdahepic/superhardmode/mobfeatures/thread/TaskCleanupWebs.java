package com.mattdahepic.superhardmode.mobfeatures.thread;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TaskCleanupWebs {
    private final BlockPos webLoc;

    private long startTime = 0;

    public TaskCleanupWebs (BlockPos pos) {
        this.webLoc = pos;
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void tick (TickEvent.WorldTickEvent e) {
        if (startTime == 0) startTime = e.world.getTotalWorldTime();
        if (e.world.getTotalWorldTime() >= startTime+600) { //30 seconds
            if (e.world.getBlockState(webLoc).getBlock() == Blocks.web) {
                e.world.setBlockToAir(webLoc);
            }
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }
}
