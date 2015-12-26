package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.mobfeatures.Zombie;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LivingAttackEventHandler {
    private static final LivingAttackEventHandler instance = new LivingAttackEventHandler();
    public static void init () {
        MinecraftForge.EVENT_BUS.register(instance);
    }
    @SubscribeEvent
    public void livingAttackEvent (LivingAttackEvent e) {
        Zombie.handlePlayerSlowness(e);
    }
}
