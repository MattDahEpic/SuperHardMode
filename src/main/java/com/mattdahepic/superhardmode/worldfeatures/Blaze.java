package com.mattdahepic.superhardmode.worldfeatures;

import com.mattdahepic.mdecore.helpers.RandomHelper;
import com.mattdahepic.superhardmode.config.SHMConfigMob;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.monster.*;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class Blaze {
    public static void handleNetherSpawning (LivingSpawnEvent e) {
        if (SHMConfigMob.blazeNetherSpawnChance > 0 && e.world.provider.getDimensionId() == -1) { //in nether
            if (e.entity instanceof EntityPigZombie && RandomHelper.randomChance(SHMConfigMob.blazeNetherSpawnChance)) {
                e.setResult(Event.Result.DENY);
                for (int i = 0; i < RandomHelper.RAND.nextInt(3); i++) { //spawn pack of blazes
                    EntityBlaze blz = new EntityBlaze(e.world);
                    blz.setPosition(e.x,e.y,e.z);
                    e.world.spawnEntityInWorld(blz);
                }
                if (RandomHelper.randomChance(SHMConfigMob.blazeNetherSpawnChance)) { //bonus magma cube, just for you.
                    EntityMagmaCube mgc = new EntityMagmaCube(e.world);
                    mgc.setPosition(e.x,e.y,e.z);
                    mgc.getEntityData().setInteger("Size",1);
                    e.world.spawnEntityInWorld(mgc);
                }
            }
        }
    }
    public static void handleBlazeSpawningNearBedrock (LivingSpawnEvent e) {
        if (SHMConfigMob.blazeOverworldLowLevelSpawnChance > 0 && e.world.provider.getDimensionId() == 0) {
            if (e.entity instanceof EntitySkeleton && RandomHelper.randomChance(SHMConfigMob.blazeOverworldLowLevelSpawnChance) && e.y <= 20) {
                e.setResult(Event.Result.DENY);
                EntityBlaze blz = new EntityBlaze(e.world);
                blz.setPosition(e.x,e.y,e.z);
                e.world.spawnEntityInWorld(blz);
            }
        }
    }
    public static void handleNetherKills (LivingDeathEvent e) {
        //TODO
    }
    public static void handleOverworldKills (LivingDeathEvent e) {
        if (e.entity.worldObj.provider.getDimensionId() == -1 && e.entity instanceof EntityBlaze) {
            if (RandomHelper.randomChance(SHMConfigMob.blazeOverworldDeathExplode)) {
                EntityTNTPrimed tnt = new EntityTNTPrimed(e.entity.worldObj, e.entity.posX, e.entity.posY, e.entity.posZ, e.entityLiving);
                tnt.fuse = 1;
                e.entity.worldObj.spawnEntityInWorld(tnt);
            }
        }
    }
    public static void handleLootTweaks (LivingDropsEvent e) {
        if (e.entity instanceof EntityBlaze) {
            if (e.entity.worldObj.provider.getDimensionId() == -1) { //blaze in nether
                if (SHMConfigMob.blazeExtraDrops) {
                    EntityItem drop = new EntityItem(e.entity.worldObj, e.entity.posX, e.entity.posY, e.entity.posZ);
                    if (RandomHelper.randomChance(50)) {
                        drop.setEntityItemStack(new ItemStack(Items.glowstone_dust, 2));
                    } else {
                        drop.setEntityItemStack(new ItemStack(Items.gunpowder, 2));
                    }
                    e.drops.add(drop);
                }
            } else { //no drops in overworld
                e.drops.clear();
            }
        }
    }
}
