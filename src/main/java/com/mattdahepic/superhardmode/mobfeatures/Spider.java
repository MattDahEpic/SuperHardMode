package com.mattdahepic.superhardmode.mobfeatures;

import com.mattdahepic.mdecore.helpers.RandomHelper;
import com.mattdahepic.superhardmode.config.SHMConfigMob;

import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

public class Spider {
    public static void handleSpeedIncrease (LivingSpawnEvent e) {
        if (e.entity instanceof EntitySpider) {
            e.entity.getEntityData().setDouble("generic.movementSpeed",SHMConfigMob.spiderHellaFast);
        }
    }
    public static void handleSpeedIncrease (EntityJoinWorldEvent e) {
        if (e.entity instanceof EntitySpider) {
            e.entity.getEntityData().setDouble("generic.movementSpeed",SHMConfigMob.spiderHellaFast);
        }
    }
    public static void handleMoreSpidersDeeper (LivingSpawnEvent e) {
        if (e.entity instanceof EntityZombie && RandomHelper.randomChance(SHMConfigMob.spiderUndergroundSpawnChance)) { //replace zombies //FIXME: make it not replace respawned zombies
            e.setResult(Event.Result.DENY);
            EntitySpider spdr = new EntitySpider(e.world);
            spdr.setPosition(e.x,e.y,e.z);
            e.world.spawnEntityInWorld(spdr);
        }
    }
    public static void handlePlacingWebsOnDeath (LivingDeathEvent e) {
        if (e.entity instanceof EntitySpider && SHMConfigMob.spiderPlaceWebsOnDeath) {
            if (e.source.getEntity() instanceof EntityPlayer) {
                webPlaceLogic(e);
            } else { //environmental death
                if (RandomHelper.RAND.nextInt(3) == 1) { //not every single mob
                    webPlaceLogic(e);
                }
            }
        }
    }
    private static void webPlaceLogic (LivingDeathEvent e) {
        //TODO: this
    }
}
