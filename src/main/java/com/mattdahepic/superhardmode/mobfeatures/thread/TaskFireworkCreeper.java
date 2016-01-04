package com.mattdahepic.superhardmode.mobfeatures.thread;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TaskFireworkCreeper {
    private final EntityCreeper creeper;
    private final long length;

    private long startTime = 0;

    public TaskFireworkCreeper (EntityCreeper creeper, int length) {
        this.creeper = creeper;
        this.length = length;
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void worldTick (TickEvent.WorldTickEvent e) {
        if (startTime == 0) startTime = e.world.getTotalWorldTime();
        if (e.world.getTotalWorldTime() < startTime+length) { //still rising
            creeper.addVelocity(0,0.015,0);
        } else { //explode
            e.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE,creeper.posX,creeper.posY,creeper.posZ,1,1,1);
            e.world.playSoundAtEntity(creeper,"random.explode",1f,1f);
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }
}
