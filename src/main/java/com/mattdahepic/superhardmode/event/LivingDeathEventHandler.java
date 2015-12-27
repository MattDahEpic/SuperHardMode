package com.mattdahepic.superhardmode.event;

import com.mattdahepic.superhardmode.mobfeatures.Zombie;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LivingDeathEventHandler {
    private static LivingDeathEventHandler instance = new LivingDeathEventHandler();
    public static void init () {
        MinecraftForge.EVENT_BUS.register(instance);
    }
    @SubscribeEvent
    public void livingDeathEvent (LivingDeathEvent e) {
        Zombie.handleZombieRespawn(e);
    }
}
