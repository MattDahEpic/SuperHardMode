package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.config.SHMConfig;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlaceEventHandler {
    private static final PlaceEventHandler instance = new PlaceEventHandler();
    public static void init () {
        MinecraftForge.EVENT_BUS.register(instance);
    }
    @SubscribeEvent
    public void placeEvent (BlockEvent.PlaceEvent e) {
        handleTorch(e);
    }
    private void handleTorch (BlockEvent.PlaceEvent e) {
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
