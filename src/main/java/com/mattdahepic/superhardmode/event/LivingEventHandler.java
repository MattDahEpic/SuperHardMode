package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.mobfeatures.Creeper;
import com.mattdahepic.superhardmode.mobfeatures.Player;
import com.mattdahepic.superhardmode.mobfeatures.Zombie;

import net.minecraftforge.common.MinecraftForge;
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
    }
    @SubscribeEvent
    public void livingDropsEvent (LivingDropsEvent e) {
        Player.handleRespawnItemLoss(e);
    }
    @SubscribeEvent
    public void livingSpawnEvent (LivingSpawnEvent e) {
        Creeper.handlePoweredSpawning(e);
    }
    @SubscribeEvent
    public void livingHurtEvent (LivingHurtEvent e) {
        Creeper.handlePoweredHitExplode(e);
    }
}
