package com.mattdahepic.superhardmode.mobfeatures;

import com.mattdahepic.superhardmode.config.SHMConfigMain;
import com.mattdahepic.superhardmode.config.SHMConfigMob;
import com.mattdahepic.superhardmode.helper.RandomHelper;

import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class Player {
    public static void handleReducedHealthHungerRespawning (PlayerEvent.PlayerRespawnEvent e) {
        if (!SHMConfigMain.shouldPlayerBypass(e.player)) {
            if (SHMConfigMob.playerRespawnHungerLow > 0 && SHMConfigMob.playerRespawnHungerLow <= SHMConfigMob.playerRespawnHungerHigh) {
                e.player.getFoodStats().setFoodLevel(MathHelper.floor_float(20 * RandomHelper.randomFloatInRange(SHMConfigMob.playerRespawnHungerLow, SHMConfigMob.playerRespawnHungerHigh)));
            }
            if (SHMConfigMob.playerRespawnHealthLow > 0 && SHMConfigMob.playerRespawnHealthLow <= SHMConfigMob.playerRespawnHealthHigh) {
                e.player.setHealth(e.player.getMaxHealth()*RandomHelper.randomFloatInRange(SHMConfigMob.playerRespawnHealthLow,SHMConfigMob.playerRespawnHealthHigh));
            }
        }
    }
}
