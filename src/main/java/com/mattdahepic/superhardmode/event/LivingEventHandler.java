package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.mobfeatures.*;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LivingEventHandler {
    private static final LivingEventHandler instance = new LivingEventHandler();
    public static void init () {
        MinecraftForge.EVENT_BUS.register(instance);
    }
    @SubscribeEvent
    public void livingAttackEvent (LivingAttackEvent e) {
        Zombie.handlePlayerSlowness(e);
    }
    @SubscribeEvent
    public void livingDeathEvent (LivingDeathEvent e) {
        Zombie.handleZombieRespawn(e);
        Creeper.handleTntDropOnDeath(e);
        Creeper.handleDeathFireworks(e);
        Blaze.handleNetherKills(e);
        Blaze.handleOverworldKills(e);
    }
    @SubscribeEvent
    public void livingDropsEvent (LivingDropsEvent e) {
        Player.handleRespawnItemLoss(e);
        Blaze.handleLootTweaks(e);
        PigZombie.handleDropNetherWart(e);
        Ghast.hellaLoot(e);
    }
    @SubscribeEvent
    public void livingSpawnEvent (LivingSpawnEvent e) {
        Creeper.handlePoweredSpawning(e);
        Blaze.handleNetherSpawning(e);
        Blaze.handleBlazeSpawningNearBedrock(e);
        PigZombie.handleAlwaysAggro(e);
        Witch.naturalWitchSpawning(e);
        Spider.handleSpeedIncrease(e);
        Spider.handleMoreSpidersDeeper(e);
    }
    @SubscribeEvent
    public void livingHurtEvent (LivingHurtEvent e) {
        Creeper.handlePoweredHitExplode(e);
        Ghast.nullifyArrowDamage(e);
    }
    @SubscribeEvent
    public void entityJoinWorldEvent (EntityJoinWorldEvent e) {
        PigZombie.handleAlwaysAggro(e);
        Spider.handleSpeedIncrease(e);
    }
    @SubscribeEvent
    public void experienceDropEvent (LivingExperienceDropEvent e) {
        Ghast.hellaExperience(e);
    }
}
