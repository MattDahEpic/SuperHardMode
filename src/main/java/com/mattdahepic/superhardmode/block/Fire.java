package com.mattdahepic.superhardmode.block;

import com.mattdahepic.superhardmode.SuperHardMode;
import com.mattdahepic.superhardmode.config.SHMConfig;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent;

public class Fire {
    public static void handleNetherrackFire(BlockEvent.BreakEvent e) {
        if (SHMConfig.netherrackFireChance > 0 && e.state.getBlock() == Blocks.netherrack && SHMConfig.shouldPlayerBypass(e.getPlayer())) {
            Block underBlock = e.world.getBlockState(e.pos.down()).getBlock();
            if (underBlock == Blocks.netherrack && SuperHardMode.RNGesus.nextInt(100) < SHMConfig.netherrackFireChance) {
                e.setCanceled(true);
                e.world.setBlockState(e.pos,Blocks.fire.getDefaultState());
            }
        }
    }
}
