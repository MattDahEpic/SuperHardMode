package com.mattdahepic.superhardmode.mobfeatures.thread;

import com.mattdahepic.superhardmode.helper.EntityHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class TaskRespawnZombies implements Runnable {
    private final World world;
    private final EntityZombie zombie;
    private final BlockPos pos;
    private final long finalRespawnTime;

    public TaskRespawnZombies (World world, EntityZombie zombie, BlockPos pos, int respawnTicks) {
        this.world = world;
        this.zombie = zombie;
        this.pos = pos;
        this.finalRespawnTime = world.getTotalWorldTime()+respawnTicks;
    }
    @Override
    public void run () {
        synchronized (world) { //TODO: threadsafe?
            if (world.getTotalWorldTime() <= finalRespawnTime) {
                world.spawnParticle(EnumParticleTypes.FLAME,pos.getX(),pos.getY(),pos.getZ(),1,1,1);
            } else {
                EntityZombie zombieNew = new EntityZombie(world);
                world.spawnEntityInWorld(zombieNew);
                zombieNew.copyDataFromOld(zombie);
                EntityHelper.flagLootless((Entity)zombieNew,true);
            }
        }
    }
}
