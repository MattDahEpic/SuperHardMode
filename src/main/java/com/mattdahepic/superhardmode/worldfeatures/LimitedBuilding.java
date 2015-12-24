package com.mattdahepic.superhardmode.worldfeatures;

import com.mattdahepic.superhardmode.config.SHMConfigMain;
import com.mattdahepic.superhardmode.config.SHMConfigWorld;

import net.minecraft.util.BlockPos;
import net.minecraftforge.event.world.BlockEvent;

public class LimitedBuilding {
    public static void handleAntiNerdPole (BlockEvent.PlaceEvent e) {
        if (SHMConfigWorld.antiNerdPole && !SHMConfigMain.shouldPlayerBypass(e.player)) {
            BlockPos playerPos = e.player.getPosition();
            if (e.pos.getX() == playerPos.getX() && e.pos.getZ() == playerPos.getZ() && e.pos.getY() < playerPos.getY()) {
                e.setCanceled(true);
            }
        }
    }
}
