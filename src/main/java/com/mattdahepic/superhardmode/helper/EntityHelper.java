package com.mattdahepic.superhardmode.helper;

import net.minecraft.entity.Entity;

public class EntityHelper {
    private static final String BASE_TAG = "superhardmode.";
    public static final String IGNORED_TAG = BASE_TAG+"ignored";
    public static final String ZOMBIE_RESPAWN_TAG = BASE_TAG+"zombie.respawns";
    public static final String LOOTLESS_TAG = BASE_TAG+"lootless";
    public static void flagIgnored (Entity e, boolean value) {
        e.getEntityData().setBoolean(IGNORED_TAG,value);
    }
    public static boolean hasFlagIgnored (Entity e) {
        return e.getEntityData().getBoolean(IGNORED_TAG);
    }
    public static void flagLootless (Entity e, boolean value) {
        e.getEntityData().setBoolean(LOOTLESS_TAG,value);
    }
    public static boolean hasFlagLootless (Entity e) {
        return e.getEntityData().getBoolean(LOOTLESS_TAG);
    }
}
