package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.mobfeatures.*;
import com.mattdahepic.superhardmode.worldfeatures.Misc;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class EntityEventHandler {
    private static final EntityEventHandler instance = new EntityEventHandler();
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
        Player.handleDamageEffects(e);
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
    @SubscribeEvent
    public void enderTeleportEvent (EnderTeleportEvent e) {
        Enderman.handlePlayerTeleport(e);
    }
    @SubscribeEvent
    public void playerRespawnEvent (PlayerEvent.PlayerRespawnEvent e) {
        Player.handleReducedHealthHungerRespawning(e);
    }
    @SubscribeEvent
    public void playerInteractEvent (PlayerInteractEvent e) {
        Player.handlePlayerSetOnFire(e);
        Misc.cactusBreakDamagePlayer(e);
    }
}
