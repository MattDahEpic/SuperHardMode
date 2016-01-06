package com.mattdahepic.superhardmode.mobfeatures.thread;

import com.mattdahepic.superhardmode.helper.TagHelper;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TaskRespawnZombies {
    private final EntityZombie zombie;
    private final BlockPos pos;
    private final long respawnTicks;

    private long startWorldTime = 0;

    public TaskRespawnZombies (EntityZombie zombie, BlockPos pos, int respawnTicks) {
        this.zombie = zombie;
        this.pos = pos;
        this.respawnTicks = respawnTicks;
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void worldTickEvent (TickEvent.WorldTickEvent e) {
        if (startWorldTime == 0) { //not initialized
            startWorldTime = e.world.getTotalWorldTime();
        } else if (e.world.getTotalWorldTime() < startWorldTime + respawnTicks) { //not to respawn time
            e.world.spawnParticle(EnumParticleTypes.FLAME, pos.getX(), pos.getY(), pos.getZ(),1,1,1); //FIXME: this doesnt work
        } else { //respawn time
            EntityZombie zombieNew = new EntityZombie(e.world);
            TagHelper.copySHMTags(zombie, zombieNew);
            TagHelper.flagLootless(zombieNew, true);
            zombieNew.setPosition(pos.getX(),pos.getY(),pos.getZ());
            zombieNew.setHealth(zombieNew.getMaxHealth()/2); //FIXME: zombies spawn with full health
            e.world.spawnEntityInWorld(zombieNew);
            MinecraftForge.EVENT_BUS.unregister(this);
        }
    }
}
