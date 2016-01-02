package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.mobfeatures.Player;
import com.mattdahepic.superhardmode.mobfeatures.Zombie;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
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
    }
    @SubscribeEvent
    public void livingDropsEvent (LivingDropsEvent e) {
        Player.handleRespawnItemLoss(e);
    }
}
