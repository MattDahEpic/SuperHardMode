package com.mattdahepic.superhardmode.mobfeatures;

import com.mattdahepic.mdecore.helpers.RandomHelper;
import com.mattdahepic.mdecore.helpers.TeleportHelper;
import com.mattdahepic.superhardmode.config.SHMConfigMob;

import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class Enderman {
    public static void handlePlayerTeleport (EnderTeleportEvent e) {
        if (e.entity instanceof EntityEnderman && ((EntityEnderman) e.entity).getAttackTarget() instanceof EntityPlayer) { //enderman aggro at player
            if (RandomHelper.randomChance(SHMConfigMob.endermanTeleportPlayerChance)) {
                EntityPlayer player = (EntityPlayer) ((EntityEnderman) e.entity).getAttackTarget();

                boolean hasTPed = false;
                while (!hasTPed) {
                    BlockPos tpPos = new BlockPos(e.targetX + RandomHelper.RAND.nextInt(8), e.targetY + RandomHelper.RAND.nextInt(8), e.targetZ + RandomHelper.RAND.nextInt(8));
                    if (TeleportHelper.isSafeLandingPosition(e.entity.worldObj,tpPos)) {
                        player.setPositionAndUpdate(tpPos.getX(),tpPos.getY(),tpPos.getZ());
                        player.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE+"Vwoop!"));
                        hasTPed = true;
                    }
                }
            }
        }
    }
}
