package com.mattdahepic.superhardmode.mobfeatures.thread;

import com.mattdahepic.superhardmode.helper.EntityHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class TaskRespawnZombies implements Runnable {
    private final World world;
    private final EntityZombie zombie;
    private final BlockPos pos;
    private final int respawnTicks;

    public TaskRespawnZombies (World world, EntityZombie zombie, BlockPos pos, int respawnTicks) {
        this.world = world;
        this.zombie = zombie;
        this.pos = pos;
        this.respawnTicks = respawnTicks;
    }
    @Override
    public void run () {
        synchronized (world) { //TODO: threadsafe?
            if () { //less then full time
                //particles if not skull
            } else {
                EntityZombie zombieNew = new EntityZombie(world);
                world.spawnEntityInWorld(zombieNew);
                zombieNew.copyDataFromOld(zombie);
                EntityHelper.flagLootless((Entity)zombieNew,true);
                //remove skull?
            }
        }
    }
}
