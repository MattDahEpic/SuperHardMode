package com.mattdahepic.superhardmode.mobfeatures;

import com.mattdahepic.mdecore.helpers.RandomHelper;
import com.mattdahepic.superhardmode.config.SHMConfigMob;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class PigZombie {
    public static void handleDropNetherWart (LivingDropsEvent e) {
        if (e.entity instanceof EntityPigZombie && e.entity.worldObj.provider.getDimensionId() == -1) {
            //if in fortress
            EntityItem ntw = new EntityItem(e.entity.worldObj,e.entity.posX,e.entity.posY,e.entity.posZ,new ItemStack(Items.nether_wart));
            if (SHMConfigMob.pigmanDropWartInFortress && e.entity.worldObj.getBlockState(e.entity.getPosition().down()).getBlock() == Blocks.nether_brick) { //in nether fortress //TODO: check if inside nether fortress bounding box
                e.drops.add(ntw);
                return;
            }
            if (RandomHelper.randomChance(SHMConfigMob.pigmenDropWartInNetherChance)) { //anywhere in nether
                e.drops.add(ntw);
            }
        }
    }
    public static void handleAlwaysAggro(LivingSpawnEvent e) {
        if (e.entity instanceof EntityPigZombie && e.world.provider.getDimensionId() == -1 && SHMConfigMob.pigmenAlwaysAggroNether) {
            ReflectionHelper.setPrivateValue(EntityPigZombie.class,(EntityPigZombie)e.entity,Integer.MAX_VALUE,"angerLevel","field_70837_d");
        }
    }
    public static void handleAlwaysAggro(EntityJoinWorldEvent e) {
        if (e.entity instanceof EntityPigZombie && e.world.provider.getDimensionId() == -1 && SHMConfigMob.pigmenAlwaysAggroNether) {
            ReflectionHelper.setPrivateValue(EntityPigZombie.class,(EntityPigZombie)e.entity,Integer.MAX_VALUE,"angerLevel","field_70837_d");
        }
    }
    public static void handleSpawnOnLightning (WeatherEvent.LightningStrike e) {
        if (SHMConfigMob.pigmenOverworldLightningSpawn) {
            int amount;
            switch (RandomHelper.RAND.nextInt(10)) {
                case 0:
                case 1: //20%
                {
                    amount = 2;
                    break;
                }
                case 2:
                case 3: //20%
                {
                    amount = 3;
                    break;
                }
                default:
                    amount = 1;
            }
            for (int i = 0; i < amount; i++) {
                EntityPigZombie pgz = new EntityPigZombie(e.world);
                pgz.setPosition(e.pos.getX(),e.pos.getY(),e.pos.getZ());
                e.world.spawnEntityInWorld(pgz);
            }
        }
    }
}
