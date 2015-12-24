package com.mattdahepic.superhardmode.worldfeatures.thread;

import com.mattdahepic.superhardmode.config.SHMConfigWorld;

import net.minecraft.block.*;
import net.minecraft.world.chunk.Chunk;

public class TaskUnlightTorches implements Runnable {
    //The chunk to process weather changes in.
    private final Chunk chunk;

    public TaskUnlightTorches(Chunk chunk) {
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
                            if (SHMConfigWorld.torchRainBreak) {
                                //TODO: set torch to unlit torch block
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
