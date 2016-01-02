package com.mattdahepic.superhardmode.mobfeatures;

import com.mattdahepic.mdecore.helpers.ItemHelper;
import com.mattdahepic.superhardmode.config.SHMConfigMain;
import com.mattdahepic.superhardmode.config.SHMConfigMob;
import com.mattdahepic.superhardmode.helper.RandomHelper;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    public static void handleReducedHealthHungerRespawning(PlayerEvent.PlayerRespawnEvent e) {
        if (!SHMConfigMain.shouldPlayerBypass(e.player)) {
            if (SHMConfigMob.playerRespawnHungerLow > 0 && SHMConfigMob.playerRespawnHungerLow <= SHMConfigMob.playerRespawnHungerHigh) {
                e.player.getFoodStats().setFoodLevel(MathHelper.floor_float(20 * RandomHelper.randomFloatInRange(SHMConfigMob.playerRespawnHungerLow, SHMConfigMob.playerRespawnHungerHigh)));
            }
            if (SHMConfigMob.playerRespawnHealthLow > 0 && SHMConfigMob.playerRespawnHealthLow <= SHMConfigMob.playerRespawnHealthHigh) {
                e.player.setHealth(e.player.getMaxHealth() * RandomHelper.randomFloatInRange(SHMConfigMob.playerRespawnHealthLow, SHMConfigMob.playerRespawnHealthHigh));
            }
        }
    }
    public static void handleRespawnItemLoss(LivingDropsEvent e) {
        if (SHMConfigMob.playerRespawnItemLoss && e.entity instanceof EntityPlayer) {
            if (!SHMConfigMain.shouldPlayerBypass((EntityPlayer)e.entity)) {
                List<ItemStack> drops = new ArrayList<ItemStack>();
                for (EntityItem i : e.drops) drops.add(i.getEntityItem());
                List<ItemStack> removedDrops = new ArrayList<ItemStack>();

                List<ItemStack> toRemove = new ArrayList<ItemStack>();

                int dropsToRemove = MathHelper.floor_float(drops.size()*SHMConfigMob.playerRespawnStackLossMultiplier);
                for (int i = 0; i < dropsToRemove; i++) {
                    ItemStack isBlacklist = drops.get(RandomHelper.rand.nextInt(drops.size()));
                    if (Arrays.asList(SHMConfigMob.playerRespawnItemLossBlacklist).contains(ItemHelper.getNameFromItemStack(isBlacklist))) {
                        continue; //don't remove blacklisted items
                    }
                    toRemove.add(isBlacklist);
                }

                for (ItemStack remove : toRemove) {
                    for (EntityItem i : e.drops) {
                        if (i.getEntityItem() == remove) {
                            if (isTool && SHMConfigMob.playerRespawnDamageTools) { //is tool
                                //damage tool
                            } else {
                                e.drops.remove(i);
                            }
                        }
                    }
                }
            }
        }
    }
}
