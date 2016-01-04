package com.mattdahepic.superhardmode.mobfeatures;

import com.mattdahepic.mdecore.helpers.RandomHelper;
import com.mattdahepic.superhardmode.config.SHMConfigMob;
import com.mattdahepic.superhardmode.mobfeatures.thread.TaskFireworkCreeper;

import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class Creeper {
    public static void handlePoweredSpawning (LivingSpawnEvent e) {
        if (SHMConfigMob.creeperPoweredSpawnChance > 0 && e.entity instanceof EntityCreeper) {
            if (RandomHelper.randomChance(SHMConfigMob.creeperPoweredSpawnChance)) {
                e.entity.getEntityData().setBoolean("powered",true);
            }
        }
    }
    public static void handlePoweredHitExplode (LivingHurtEvent e) {
        if (SHMConfigMob.creeperPoweredHitExplode && e.entity instanceof EntityCreeper) {
            if (((EntityCreeper)e.entity).getPowered() && e.source.getEntity() instanceof EntityPlayer) {
                ((EntityCreeper)e.entity).ignite();
            }
        }
    }
    public static void handleTntDropOnDeath (LivingDeathEvent e) {
        if (SHMConfigMob.creeperDropTNTOnDeathChance > 0 && e.entity instanceof EntityCreeper) {
            if (RandomHelper.randomChance(SHMConfigMob.creeperDropTNTOnDeathChance)) {
                EntityTNTPrimed tnt = new EntityTNTPrimed(e.entity.worldObj,e.entity.posX,e.entity.posY,e.entity.posZ,e.entityLiving);
                e.entity.worldObj.spawnEntityInWorld(tnt);
            }
        }
    }
    public static void handleDeathFireworks (LivingDeathEvent e) {
        if (SHMConfigMob.creeperFireDeathFireworks && e.entity instanceof EntityCreeper) {
            if (e.entity.isBurning()) {
                new TaskFireworkCreeper((EntityCreeper)e.entity,RandomHelper.randomIntInRange(1,4)*20);
            }
        }
    }
}
