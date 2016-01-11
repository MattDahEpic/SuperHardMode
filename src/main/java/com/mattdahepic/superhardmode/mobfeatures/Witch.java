package com.mattdahepic.superhardmode.mobfeatures;

import com.mattdahepic.mdecore.helpers.RandomHelper;
import com.mattdahepic.superhardmode.config.SHMConfigMob;

import net.minecraft.block.BlockGrass;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class Witch {
    public static void naturalWitchSpawning (LivingSpawnEvent e) {
        if (e.entity instanceof EntityZombie && e.world.getBlockState(new BlockPos(e.x,e.y,e.z).down()).getBlock() instanceof BlockGrass) { //is a zombie on the surface
            if (RandomHelper.randomChance(SHMConfigMob.witchNatSpawnChance)) {
                e.setResult(Event.Result.DENY);
                EntityWitch wtch = new EntityWitch(e.world);
                wtch.setPosition(e.x,e.y,e.z);
                e.world.spawnEntityInWorld(wtch);
            }
        }
    }
    //TODO: everything else, needs thrown potion splash event
}
