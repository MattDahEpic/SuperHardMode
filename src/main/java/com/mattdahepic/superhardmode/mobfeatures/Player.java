package com.mattdahepic.superhardmode.mobfeatures;

import com.mattdahepic.mdecore.config.MDEConfig;
import com.mattdahepic.mdecore.helpers.ItemHelper;
import com.mattdahepic.mdecore.helpers.RandomHelper;
import com.mattdahepic.superhardmode.SuperHardMode;
import com.mattdahepic.superhardmode.config.SHMConfigMain;
import com.mattdahepic.superhardmode.config.SHMConfigMob;
import com.mattdahepic.superhardmode.config.SHMConfigWorld;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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

                int dropsToRemove = MathHelper.floor_float(drops.size()*SHMConfigMob.playerRespawnStackLossMultiplier); //FIXME: flooring this wont remove drops when the inventory has 3 slots or less filled
                for (int i = 0; i < dropsToRemove; i++) {
                    ItemStack isBlacklist = drops.get(RandomHelper.RAND.nextInt(drops.size()));
                    if (Arrays.asList(SHMConfigMob.playerRespawnItemLossBlacklist).contains(ItemHelper.getNameFromItemStack(isBlacklist))) {
                        continue; //don't remove blacklisted items
                    }
                    toRemove.add(isBlacklist);
                }

                outer:
                for (ItemStack remove : toRemove) {
                    for (EntityItem i : e.drops) {
                        if (i.getEntityItem() == remove) {
                            if (!remove.getItem().getToolClasses(remove).isEmpty()) { //is tool
                                int dur = remove.getItemDamage();
                                int maxDur = remove.getMaxDamage();
                                dur += MathHelper.floor_float((maxDur / 100)*SHMConfigMob.playerRespawnToolDamage);
                                if (dur >= maxDur && !SHMConfigMob.playerRespawnDamageTools) {
                                    dur = maxDur - 1; //1 use left
                                }
                                remove.setItemDamage(dur); //FIXME: is this broken?
                            } else {
                                e.drops.remove(i);
                                if (MDEConfig.debugLogging) SuperHardMode.logger.info("Removed "+i);
                            }
                            continue outer; //no need to continue testing when it's going to be false
                        }
                    }
                }
            }
        }
    }
    public static void handleDamageEffects (LivingHurtEvent e) {
        //FIXME: cooldown so messages aren't spammed -OR- extra utilites-like replacing chat message
        if (e.entity instanceof EntityPlayer && SHMConfigMob.playerDamageEffects && !SHMConfigMain.shouldPlayerBypass((EntityPlayer)e.entity)) {
            switch (e.source.damageType) {
                case "fall": //slowness 3 for 40 ticks (your legs are broken)
                    if (SHMConfigMain.playerMessages) e.entity.addChatMessage(new ChatComponentText(EnumChatFormatting.RED+"Your legs broke!"));
                    ((EntityPlayer) e.entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,40,2,false,false));
                    break;
                case "inWall": //blindness 2 for 20 ticks & haste 4 for 20 ticks (you panic while trying to get out of the wall)
                    if (SHMConfigMain.playerMessages) e.entity.addChatMessage(new ChatComponentText(EnumChatFormatting.RED+"You panic while trying to get out of the wall."));
                    ((EntityPlayer) e.entity).addPotionEffect(new PotionEffect(Potion.blindness.id,20,1,false,false));
                    ((EntityPlayer) e.entity).addPotionEffect(new PotionEffect(Potion.digSpeed.id,20,3,false,false));
                    break;
                case "explosion": //nauesa 3 for 120 ticks & blindness 1 for 30 ticks (your brain got knocked around in the blast)
                    if (SHMConfigMain.playerMessages) e.entity.addChatMessage(new ChatComponentText(EnumChatFormatting.RED+"Your brain got knocked around in the blast!"));
                    ((EntityPlayer) e.entity).addPotionEffect(new PotionEffect(Potion.confusion.id,120,2,false,false));
                    ((EntityPlayer) e.entity).addPotionEffect(new PotionEffect(Potion.blindness.id,30,1,false,false));
                    break;
                case "lava": //blindness 1 for 40 ticks & slowness 1 for 40 ticks (you burn up)
                    if (SHMConfigMain.playerMessages) e.entity.addChatMessage(new ChatComponentText(EnumChatFormatting.RED+"You burn in the lava."));
                    ((EntityPlayer) e.entity).addPotionEffect(new PotionEffect(Potion.blindness.id,40,0,false,false));
                    ((EntityPlayer) e.entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,40,0,false,false));
                    break;
                case "onFire": //blindness 1 for 40 ticks (your eyes burned out)
                    if (SHMConfigMain.playerMessages) e.entity.addChatMessage(new ChatComponentText(EnumChatFormatting.RED+"Your eyes fall to the ground with a soft, firey plop."));
                    ((EntityPlayer) e.entity).addPotionEffect(new PotionEffect(Potion.blindness.id,40,0,false,false));
                    break;
                case "drown": //blindness 5 for 20 ticks (as you panic beneath the waves you begin to lose sense of the world around you)
                    if (SHMConfigMain.playerMessages) e.entity.addChatMessage(new ChatComponentText(EnumChatFormatting.RED+"As you panic beneath the waves, you begin to lose sense of the world around you."));
                    ((EntityPlayer) e.entity).addPotionEffect(new PotionEffect(Potion.blindness.id,20,4,false,false));
                    break;
                case "starve": //nauesa 20 for 360 ticks & slowness 2 for 20 ticks (your brain begins to lack the energy needed for coherent thought)
                    if (SHMConfigMain.playerMessages) e.entity.addChatMessage(new ChatComponentText(EnumChatFormatting.RED+"Your brain begins to lack the energy needed for coherent thought and your legs fall out from beneath you."));
                    ((EntityPlayer) e.entity).addPotionEffect(new PotionEffect(Potion.confusion.id,360,19,false,false));
                    ((EntityPlayer) e.entity).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,20,1,false,false));
                    break;
                default:
                    break;
            }
        }
    }
    public static void handlePlayerSetOnFire (PlayerInteractEvent e) {
        if (SHMConfigWorld.setPlayersOnFireWhenBreakingFire && !SHMConfigMain.shouldPlayerBypass(e.entityPlayer) && e.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            if (e.world.getBlockState(e.pos.offset(e.face)).getBlock() == Blocks.fire) {
                e.entityPlayer.setFire(100);
            }
        }
    }
}
