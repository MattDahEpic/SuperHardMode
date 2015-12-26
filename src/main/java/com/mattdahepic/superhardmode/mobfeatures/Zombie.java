package com.mattdahepic.superhardmode.mobfeatures;

import com.mattdahepic.superhardmode.config.SHMConfigMain;
import com.mattdahepic.superhardmode.config.SHMConfigMob;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

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
}
