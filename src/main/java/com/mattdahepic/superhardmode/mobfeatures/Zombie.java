package com.mattdahepic.superhardmode.mobfeatures;

import com.mattdahepic.mdecore.helpers.RandomHelper;
import com.mattdahepic.superhardmode.config.SHMConfigMain;
import com.mattdahepic.superhardmode.config.SHMConfigMob;
import com.mattdahepic.superhardmode.helper.TagHelper;
import com.mattdahepic.superhardmode.mobfeatures.thread.TaskRespawnZombies;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
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
        if (SHMConfigMob.zombieRespawnChance > 0 && !TagHelper.getFlagIgnored(e.entity)) {
            if (e.entity instanceof EntityZombie) {
                int respawns = TagHelper.getFlagRespawns(e.entity)+1;
                if (!((EntityZombie) e.entity).isVillager() && !e.entity.isBurning() && RandomHelper.randomChance(SHMConfigMob.zombieRespawnChance/respawns)) {
                    TagHelper.flagRespawns(e.entity,respawns);
                    new TaskRespawnZombies((EntityZombie)e.entity,e.entity.getPosition(),RandomHelper.RAND.nextInt(8)*20);
                }
            }
        }
    }
}
