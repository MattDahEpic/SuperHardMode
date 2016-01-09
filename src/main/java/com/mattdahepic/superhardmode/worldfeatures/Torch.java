package com.mattdahepic.superhardmode.worldfeatures;

import com.mattdahepic.superhardmode.config.SHMConfigMain;
import com.mattdahepic.superhardmode.config.SHMConfigWorld;
import com.mattdahepic.superhardmode.worldfeatures.thread.TaskUnlightTorches;

import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.BlockEvent;

public class Torch {
    public static void handleTorchPlacement(BlockEvent.PlaceEvent e) {
        if (e.placedBlock.getBlock() == Blocks.torch && !SHMConfigMain.shouldPlayerBypass(e.player)) {
            if (SHMConfigWorld.preventTorchPlacingOnSoftBlocks) {
                if (e.placedAgainst.getBlock().getBlockHardness(e.world, e.pos) < 1.0F) {
                    e.setCanceled(true);
                    if (SHMConfigMain.torchSounds) {
                        e.player.playSound("random.fizz", 1.0F, 1.0F);
                    }
                    return;
                }
            }
            if (e.pos.getY() <= SHMConfigWorld.torchMinY) {
                e.setCanceled(true);
                if (SHMConfigMain.torchSounds) {
                    e.player.playSound("random.fizz", 1.0F, 1.0F);
                }
                return;
            }
        }
    }
    public static void handleTorchUnlighting (BlockEvent.PlaceEvent e) {
        if (e.changeTo == WeatherEvent.Change.WeatherType.RAIN || e.changeTo == WeatherEvent.Change.WeatherType.STORM) {
            for (Chunk c : e.world.getLoadedChunks()) { //FIXME
                new TaskUnlightTorches(c);
            }
        }
    }
}
