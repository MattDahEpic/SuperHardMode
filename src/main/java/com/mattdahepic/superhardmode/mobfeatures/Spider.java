package com.mattdahepic.superhardmode.mobfeatures;

import com.mattdahepic.mdecore.helpers.RandomHelper;
import com.mattdahepic.superhardmode.config.SHMConfigMob;
import com.mattdahepic.superhardmode.mobfeatures.thread.TaskCleanupWebs;

import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockWeb;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.ArrayList;
import java.util.List;

public class Spider {
    public static void handleSpeedIncrease (LivingSpawnEvent e) {
        if (e.entity instanceof EntitySpider) {
            e.entity.getEntityData().setDouble("generic.movementSpeed",SHMConfigMob.spiderHellaFast);
        }
    }
    public static void handleSpeedIncrease (EntityJoinWorldEvent e) {
        if (e.entity instanceof EntitySpider) {
            e.entity.getEntityData().setDouble("generic.movementSpeed",SHMConfigMob.spiderHellaFast);
        }
    }
    public static void handleMoreSpidersDeeper (LivingSpawnEvent e) {
        if (e.entity instanceof EntityZombie && RandomHelper.randomChance(SHMConfigMob.spiderUndergroundSpawnChance)) { //replace zombies //FIXME: make it not replace respawned zombies
            e.setResult(Event.Result.DENY);
            EntitySpider spdr = new EntitySpider(e.world);
            spdr.setPosition(e.x,e.y,e.z);
            e.world.spawnEntityInWorld(spdr);
        }
    }
    public static void handlePlacingWebsOnDeath (LivingDeathEvent e) {
        if (e.entity instanceof EntitySpider && SHMConfigMob.spiderPlaceWebsOnDeath) {
            if (e.source.getEntity() instanceof EntityPlayer) {
                webPlaceLogic(e);
            } else { //environmental death
                if (RandomHelper.RAND.nextInt(3) == 1) { //not every single mob
                    webPlaceLogic(e);
                }
            }
        }
    }
    private static void webPlaceLogic (LivingDeathEvent e) {
        World world = e.entity.worldObj;

        int offset1 = RandomHelper.RAND.nextInt(8);
        int offset2 = RandomHelper.RAND.nextInt(8);
        List<BlockPos> randomPoses = new ArrayList<BlockPos>();
        randomPoses.add(e.entity.getPosition().add(offset1,0,offset2));
        randomPoses.add(e.entity.getPosition().add(-offset2,0,offset1/2));
        randomPoses.add(e.entity.getPosition().add(-offset1/2,0,-offset2));
        randomPoses.add(e.entity.getPosition().add(offset1,0,-offset2/2));

        nextBlock:
        for (BlockPos pos : randomPoses) {
            if (!(world.getBlockState(pos).getBlock() instanceof BlockAir)) { //don't replace things that aren't air
                continue;
            }
            if (world.getBlockState(pos.down()).getBlock() instanceof BlockAir) { //don't place in midair
                continue;
            }
            for (EnumFacing side : EnumFacing.values()) {
                if (world.getBlockState(pos.offset(side)).getBlock() instanceof BlockCactus) { //don't place next to cactus (cause it'll break the cactus)
                    continue nextBlock;
                }
            }
            world.setBlockState(pos, BlockWeb.getStateById(0));
            if (pos.getY() >= world.getSeaLevel()) { //above sea level
                new TaskCleanupWebs(pos);
            }
        }
    }
}
