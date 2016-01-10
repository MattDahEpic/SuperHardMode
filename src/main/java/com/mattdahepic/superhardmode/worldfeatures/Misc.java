package com.mattdahepic.superhardmode.worldfeatures;

import com.mattdahepic.superhardmode.config.SHMConfigWorld;

import net.minecraft.block.BlockCactus;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class Misc {
    public static void cactusBreakDamagePlayer (PlayerInteractEvent e) {
        if (e.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK && e.entityPlayer.getHeldItem() == null) {
            if (e.world.getBlockState(e.pos).getBlock() instanceof BlockCactus && SHMConfigWorld.breakingCactusWithBareHandsHurts) {
                e.entityPlayer.fall(6,1f); //damage player
            }
        }
    }
}
