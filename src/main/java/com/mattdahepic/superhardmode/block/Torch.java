package com.mattdahepic.superhardmode.block;

import com.mattdahepic.superhardmode.config.SHMConfig;

import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent;

public class Torch {
    public static void handleTorch (BlockEvent.PlaceEvent e) {
        if (e.placedBlock.getBlock() == Blocks.torch) {
            if (!SHMConfig.shouldPlayerBypass(e.player)) {
                if (SHMConfig.preventTorchPlacingOnSoftBlocks) {
                    if (e.placedAgainst.getBlock().getBlockHardness(e.world, e.pos) < 1.0F) {
                        e.setCanceled(true);
                        if (SHMConfig.torchSounds) {
                            e.player.playSound("random.fizz", 1.0F, 1.0F);
                        }
                        return;
                    }
                }
                if (e.pos.getY() <= SHMConfig.torchMinY) {
                    e.setCanceled(true);
                    if (SHMConfig.torchSounds) {
                        e.player.playSound("random.fizz", 1.0F, 1.0F);
                    }
                    return;
                }
            }
        }
    }
}
