package com.mattdahepic.superhardmode.farmingfeatures;

import com.mattdahepic.superhardmode.config.SHMConfigFarming;
import com.mattdahepic.superhardmode.config.SHMConfigMain;

import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

public class AntiFarming {
    public static void bonemealMushroom (PlayerInteractEvent e) {
        if (e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && e.entityPlayer.getHeldItem().getItem() == Items.dye) {
            if (e.entityPlayer.getHeldItem().getItemDamage() == 15 && !SHMConfigFarming.mushroomBoneMeal && !SHMConfigMain.shouldPlayerBypass(e.entityPlayer)) {
                e.setCanceled(true);
            }
        }
    }
    public static void netherwartFarming (BlockEvent.HarvestDropsEvent e) {
        if (e.state.getBlock() == Blocks.nether_wart && !SHMConfigFarming.netherwartFarming) {
            if (!SHMConfigMain.shouldPlayerBypass(e.harvester)) return;
            e.dropChance = 1.0f;
            e.drops.clear();
            e.drops.add(new ItemStack(Items.nether_wart,1,0));
        }
    }
    public static void netherwartFarming (BlockEvent.PlaceEvent e) {
        if (e.placedBlock.getBlock() == Blocks.nether_wart && !SHMConfigFarming.netherwartFarming) {
            if (!SHMConfigMain.shouldPlayerBypass(e.player)) {
                e.setCanceled(true);
            }
        }
    }
    public static void noIronFromGolems (LivingDropsEvent e) {
        if (e.entity instanceof EntityIronGolem && !SHMConfigFarming.ironFromGolems) {
            e.drops.clear();
        }
    }
}
