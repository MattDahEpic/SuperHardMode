package com.mattdahepic.superhardmode.mobfeatures;

import com.mattdahepic.superhardmode.config.SHMConfigMob;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class Ghast {
    public static void nullifyArrowDamage (LivingHurtEvent e) {
        if (e.entity instanceof EntityGhast && e.source.isProjectile()) {
            if (SHMConfigMob.ghastImmuneToArrows) {
                e.setCanceled(true);
            }
        }
    }
    public static void hellaLoot (LivingDropsEvent e) {
        if (e.entity instanceof EntityGhast && SHMConfigMob.ghastImmuneToArrows) {
            for (EntityItem i : e.drops) {
                i.getEntityItem().stackSize = MathHelper.floor_float(i.getEntityItem().stackSize * SHMConfigMob.ghastDropMultiplier);
            }
        }
    }
    public static void hellaExperience (LivingExperienceDropEvent e) {
        if (e.entity instanceof EntityGhast && SHMConfigMob.ghastImmuneToArrows) {
            e.setDroppedExperience(MathHelper.floor_float(e.getOriginalExperience() * SHMConfigMob.ghastXpMultiplier));
        }
    }
}
