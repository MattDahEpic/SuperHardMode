package com.mattdahepic.superhardmode.block;

import com.mattdahepic.superhardmode.config.SHMConfig;

import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.world.BlockEvent;

public class Softening {
    public static void handleSoftening (BlockEvent.BreakEvent e) {
        if (SHMConfig.softenBlocksOnMine) {
            ItemStack blockBroken = new ItemStack(e.world.getBlockState(e.pos).getBlock(),1,e.world.getBlockState(e.pos).getBlock().getMetaFromState(e.state));
            if (SHMConfig.blocksThatSoften.contains(blockBroken)) {
                for (EnumFacing side : EnumFacing.values()) {
                    BlockPos pos = e.pos.offset(side);
                    ItemStack blockToSoften = new ItemStack(e.world.getBlockState(pos).getBlock(),1,e.world.getBlockState(pos).getBlock().getMetaFromState(e.world.getBlockState(pos)));
                    if (SHMConfig.blocksThatBeSoftened.contains(blockToSoften)) {
                        PhysicsHelper.turnBlockToFallingSand(e.world,e.pos);
                    }
                }
            }
        }
    }
}
