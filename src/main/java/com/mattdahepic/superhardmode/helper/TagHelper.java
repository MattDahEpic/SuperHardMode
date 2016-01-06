package com.mattdahepic.superhardmode.helper;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class TagHelper {
    private static final String BASE_TAG = "superhardmode.";
    public static final String IGNORED_TAG = BASE_TAG+"ignored";
    public static final String RESPAWNS_TAG = BASE_TAG+"respawns";
    public static final String LOOTLESS_TAG = BASE_TAG+"lootless";

    public static void flagIgnored (Entity e, boolean value) {
        e.getEntityData().setBoolean(IGNORED_TAG,value);
    }
    public static void flagLootless (Entity e, boolean value) {
        e.getEntityData().setBoolean(LOOTLESS_TAG,value);
    }
    public static void flagRespawns (Entity e, int value) {
        e.getEntityData().setInteger(RESPAWNS_TAG, value);
    }

    public static boolean getFlagIgnored(Entity e) {
        return e.getEntityData().getBoolean(IGNORED_TAG);
    }
    public static boolean getFlagLootless(Entity e) {
        return e.getEntityData().getBoolean(LOOTLESS_TAG);
    }
    public static int getFlagRespawns (Entity e) {
        return e.getEntityData().getInteger(RESPAWNS_TAG);
    }

    public static void copySHMTags (Entity eOld, Entity eNew) {
        NBTTagCompound dataOld = eOld.getEntityData();
        NBTTagCompound dataNew = eNew.getEntityData();
        dataNew.setBoolean(IGNORED_TAG,dataOld.getBoolean(IGNORED_TAG));
        dataNew.setInteger(RESPAWNS_TAG,dataOld.getInteger(RESPAWNS_TAG));
        dataNew.setBoolean(LOOTLESS_TAG,dataOld.getBoolean(LOOTLESS_TAG));
    }
}
