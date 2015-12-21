package com.mattdahepic.superhardmode.thread;

import com.mattdahepic.superhardmode.config.SHMConfig;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;

import java.util.Random;

public class TaskProcessWeatherChanges implements Runnable {
    //The chunk to process weather changes in.
    private final Chunk chunk;

    public TaskProcessWeatherChanges (Chunk chunk) {
        this.chunk = chunk;
    }

    @Override
    public void run () {
        if (this.chunk.getWorld().isRaining()) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = chunk.getWorld().provider.getHeight(); y > 0; y--) {
                        Block block = chunk.getBlock(x, y, z);

                        if (block instanceof BlockAir) {
                            continue;
                        } else if (block instanceof BlockTorch) {
                            if (SHMConfig.torchRainBreak) {
                                if (new Random().nextInt(5) == 1) {
                                    block.breakBlock(chunk.getWorld(),new BlockPos(x+16*chunk.xPosition,y,z+16*chunk.zPosition),chunk.getWorld().getBlockState(new BlockPos(x+16*chunk.xPosition,y,z+16*chunk.zPosition)));
                                } else {
                                    chunk.getWorld().setBlockToAir(new BlockPos(x+16*chunk.xPosition,y,z+16*chunk.zPosition));
                                }
                                chunk.setChunkModified();
                            }
                        } else if (block instanceof BlockCrops) {
                            //TODO: break crops
                            chunk.setChunkModified();
                        } else {
                            break; //anything that isnt air will protect crops and torches
                        }
                    }
                }
            }
        }
    }
}
