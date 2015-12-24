package com.mattdahepic.superhardmode.worldfeatures;

import com.mattdahepic.superhardmode.SuperHardMode;
import com.mattdahepic.superhardmode.config.SHMConfigMain;
import com.mattdahepic.superhardmode.config.SHMConfigWorld;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

public class Fire {
    public static void handleNetherrackBreakFire(BlockEvent.BreakEvent e) {
        if (SHMConfigWorld.netherrackFireChance > 0 && e.state.getBlock() == Blocks.netherrack && !SHMConfigMain.shouldPlayerBypass(e.getPlayer())) {
            Block underBlock = e.world.getBlockState(e.pos.down()).getBlock();
            if (underBlock == Blocks.netherrack && SuperHardMode.RNGesus.nextInt(100) < SHMConfigWorld.netherrackFireChance) {
                e.setCanceled(true);
                e.world.setBlockState(e.pos,Blocks.fire.getDefaultState());
            }
        }
    }
    public static void handlePlayerSetOnFire (PlayerInteractEvent e) {
        if (SHMConfigWorld.setPlayersOnFireWhenBreakingFire && !SHMConfigMain.shouldPlayerBypass(e.entityPlayer) && e.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            if (e.world.getBlockState(e.pos.offset(e.face)).getBlock() == Blocks.fire) {
                e.entityPlayer.setFire(100);
            }
        }
    }
}
