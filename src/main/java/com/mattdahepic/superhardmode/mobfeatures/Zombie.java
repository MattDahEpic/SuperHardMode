package com.mattdahepic.superhardmode.mobfeatures;

import com.mattdahepic.superhardmode.SuperHardMode;
import com.mattdahepic.superhardmode.config.SHMConfigMain;
import com.mattdahepic.superhardmode.config.SHMConfigMob;
import com.mattdahepic.superhardmode.helper.EntityHelper;
import com.mattdahepic.superhardmode.helper.RandomHelper;
import com.mattdahepic.superhardmode.mobfeatures.thread.TaskRespawnZombies;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class Zombie {
    public static void handlePlayerSlowness (LivingAttackEvent e) {
        if (e.entity instanceof EntityPlayer && SHMConfigMob.zombieHitSlowness) {
            EntityPlayer player = (EntityPlayer) e.entity;
            if (!SHMConfigMain.shouldPlayerBypass(player) && e.source.getEntity() instanceof EntityZombie) {
                int amplifier = 0;
                if (SHMConfigMob.stackZombieHitSlowness && player.isPotionActive(Potion.moveSlowdown)) {
                    int currentModifier = player.getActivePotionEffect(Potion.moveSlowdown).getAmplifier();
                    if (currentModifier + 1 < SHMConfigMob.maxZombieHitSlownessStack) {
                        amplifier = currentModifier + 1;
                    } else {
                        amplifier = currentModifier;
                    }
                }
                player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,120,amplifier));
            }
        }
    }
    public static void handleZombieRespawn (LivingDeathEvent e) {
        if (SHMConfigMob.zombieRespawnChance > 0 && !EntityHelper.hasFlagIgnored(e.entity)) {
            if (e.entity instanceof EntityZombie) {
                EntityZombie zombie = (EntityZombie)e.entity;
                int respawns = zombie.getEntityData().getInteger(EntityHelper.ZOMBIE_RESPAWN_TAG);
                respawns++;
                int respawnChance = MathHelper.floor_double((1.0D / respawns)*SHMConfigMob.zombieRespawnChance);
                if (!zombie.isVillager() && !zombie.isBurning() && RandomHelper.randomChance(respawnChance)) {
                    zombie.getEntityData().setInteger(EntityHelper.ZOMBIE_RESPAWN_TAG,respawns);
                    new TaskRespawnZombies(zombie.worldObj,zombie,zombie.getPosition(),SuperHardMode.RNGesus.nextInt(8)*20).run();
                }
            }
        }
    }
}
