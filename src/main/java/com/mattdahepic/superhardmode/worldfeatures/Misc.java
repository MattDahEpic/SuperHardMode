package com.mattdahepic.superhardmode.worldfeatures;

import com.mattdahepic.mdecore.helpers.RandomHelper;
import com.mattdahepic.superhardmode.config.SHMConfigMain;
import com.mattdahepic.superhardmode.config.SHMConfigWorld;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

public class Misc {
    public static void cactusBreakDamagePlayer (PlayerInteractEvent e) {
        if (e.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK && e.entityPlayer.getHeldItem() == null) {
            if (e.world.getBlockState(e.pos).getBlock() instanceof BlockCactus && SHMConfigWorld.breakingCactusWithBareHandsHurts) {
                e.entityPlayer.fall(6,1f); //damage player
            }
        }
    }
    public static void netherrackBreakFire(BlockEvent.BreakEvent e) {
        if (SHMConfigWorld.netherrackFireChance > 0 && e.state.getBlock() == Blocks.netherrack && !SHMConfigMain.shouldPlayerBypass(e.getPlayer())) {
            Block underBlock = e.world.getBlockState(e.pos.down()).getBlock();
            if (underBlock == Blocks.netherrack && RandomHelper.randomChance(SHMConfigWorld.netherrackFireChance)) {
                e.setCanceled(true);
                e.world.setBlockState(e.pos, Blocks.fire.getDefaultState());
            }
        }
    }
}
