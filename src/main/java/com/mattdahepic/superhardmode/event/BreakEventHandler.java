package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.config.SHMConfig;

import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
        if (SHMConfig.softenBlocksOnMine) {
            ItemStack blockBroken = new ItemStack(e.world.getBlockState(e.pos).getBlock(),1,e.world.getBlockState(e.pos).getBlock().getMetaFromState(e.state));
            if (SHMConfig.blocksThatSoften.contains(blockBroken)) {
                for (EnumFacing side : EnumFacing.values()) {
                    BlockPos pos = e.pos.offset(side);
                    //TODO: check for blocks to break
                    //TODO: break blocks
                }
            }
        }
    }
}
