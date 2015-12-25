package com.mattdahepic.superhardmode.helper;

import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class FallingBlockHelper {
    public static void turnBlockToFallingSand (World world, BlockPos pos) {
        EntityFallingBlock block = new EntityFallingBlock(world,pos.getX(),pos.getY(),pos.getZ(),world.getBlockState(pos));
        world.setBlockToAir(pos);
        world.spawnEntityInWorld(block);
    }
}
