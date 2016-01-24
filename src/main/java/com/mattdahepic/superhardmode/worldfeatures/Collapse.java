package com.mattdahepic.superhardmode.worldfeatures;

import com.mattdahepic.superhardmode.helper.FallingBlockHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public class Collapse {
    public static void handleCollapse (BlockEvent.BreakEvent e) {
        handleCollapse(e.world,e.pos,true);
    }
    public static void handleCollapse (BlockEvent.PlaceEvent e) {
        handleCollapse(e.world,e.pos,true);
    }
    private static void handleCollapse (World world, BlockPos pos, boolean cascade) {
        for (EnumFacing side : EnumFacing.values()) {
            if (!isBlockSupported(world,pos)) {
                if (cascade) {
                    FallingBlockHelper.turnBlockToFallingSand(world, pos);
                    handleCollapse(world, pos, false);
                } else {
                    FallingBlockHelper.turnBlockToFallingSand(world, pos);
                }
            }
        }
    }
    private static boolean isBlockSupported (World world, BlockPos pos) {
        if (world.isAirBlock(pos)) return true;
        int supportingBlocks = 0;

        for (EnumFacing face : EnumFacing.HORIZONTALS) {
            for (int i = 0; i < 6; i++) {
                BlockPos test = pos.offset(face,i);
                Block testBlock = world.getBlockState(pos.down()).getBlock();
                if (testBlock != Blocks.air && !(testBlock instanceof BlockStone)) { //TODO: test if player placed
                    supportingBlocks++;
                }
            }
        }

        return supportingBlocks != 0;
    }
}
