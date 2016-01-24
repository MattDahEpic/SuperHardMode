package com.mattdahepic.superhardmode.helper;

import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class FallingBlockHelper {
    public static void turnBlockToFallingSand (World world, BlockPos pos) {
        if (!world.isRemote) {
            EntityFallingBlock block = new EntityFallingBlock(world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, world.getBlockState(pos));
            world.spawnEntityInWorld(block);
        }
    }
}
