package com.mattdahepic.superhardmode.config;

import com.mattdahepic.mdecore.config.annot.Comment;
import com.mattdahepic.mdecore.config.annot.Config;
import com.mattdahepic.mdecore.config.annot.Range;
import com.mattdahepic.mdecore.config.sync.ConfigProcessor;
import com.mattdahepic.mdecore.config.sync.ConfigSyncable;
import com.mattdahepic.superhardmode.SuperHardMode;

public class SHMConfigMob extends ConfigSyncable {
    public static final String CAT_ZOMBIE = "zombie";
    public static final String CAT_PLAYER = "player";

    @Config(CAT_ZOMBIE) @Comment({"Should zombies apply slowness to players when they attack and hit?"}) public static boolean zombieHitSlowness = true;
    @Config(CAT_ZOMBIE) @Comment({"Should multiple hits from zombie(s) stack the slowness effect?"}) public static boolean stackZombieHitSlowness = true;
    @Config(CAT_ZOMBIE) @Comment({"What should the maximum level of slowness be for players who are attacked by zombies?"}) @Range(min = 2,max = 127) public static int maxZombieHitSlownessStack = 12;
    @Config(CAT_ZOMBIE) @Comment({"Zombies place a block on death and with this chance, if the block is not broken the zombie respawns","Set to 0 to disable"}) @Range(min = 0,max = 100) public static int zombieRespawnChance = 75;

    @Config(CAT_PLAYER) @Comment({"Lowest percent of hunger a player can respawn with, multiplicative.","Set to 0 to disable hunger reduction upon respawn."}) @Range(min = 0f,max = 1f) public static float playerRespawnHungerLow = 0.5f;
    @Config(CAT_PLAYER) @Comment({"Highest percent of hunger a player can respawn with, multiplicative."}) @Range(min = 0f,max = 1f) public static float playerRespawnHungerHigh = 0.75f;
    @Config(CAT_PLAYER) @Comment({"Lowest percent of health a player can respawn with, multiplicative.","Set to 0 to disable health reduction upon respawn."}) @Range(min = 0f,max = 1f) public static float playerRespawnHealthLow = 0.5f;
    @Config(CAT_PLAYER) @Comment({"Highest percent of health a player can respawn with, multiplicative."}) @Range(min = 0f,max = 1f) public static float playerRespawnHealthHigh = 0.75f;
    @Config(CAT_PLAYER) @Comment({"Should players lose some items when they die.","Meant to discourage players from killing themselves to restore health and hunger."}) public static boolean playerRespawnItemLoss = true;
    @Config(CAT_PLAYER) @Comment({"What percent of stacks should be removed on player death, if enabled?"}) @Range(min = 0f,max = 1f) public static float playerRespawnStackLossMultiplier = 0.3f;
    @Config(CAT_PLAYER) @Comment({"Items and blocks to blacklist from player drop removal.","Formatted as modid:item@meta"}) public static String[] playerRespawnItemLossBlacklist = new String[]{"minecraft:barrier@0"};
    @Config(CAT_PLAYER) @Comment({"Should tools end up being destroyed or only brought down to 1 use if playerRespawnToolDamage were to being them below 0 durability?","true = 1 use, false = destroy"}) public static boolean playerRespawnDamageTools = true;
    @Config(CAT_PLAYER) @Comment({"How much of a tool should be taken away when tools are damaged on death?"}) @Range(min = 0f,max = 1f) public static float playerRespawnToolDamage = 0.1f;

    private static ConfigSyncable INSTANCE;
    public static ConfigSyncable instance() {
        if (INSTANCE == null) {
            INSTANCE = new SHMConfigMob(SuperHardMode.CONFIGNAME_MOBS);
        }
        return INSTANCE;
    }

    public static ConfigProcessor processor;

    protected SHMConfigMob(String configName) {
        super(configName);
    }
    @Override
    public void init() {
        addSection(CAT_ZOMBIE);
        processor = new ConfigProcessor(getClass(), this.config, this.configFileName);
        processor.process(true);
    }
    @Override
    protected void reloadIngameConfigs() {}
    @Override
    protected void reloadNonIngameConfigs() {}
    @Override
    public String getConfigName() {
        return this.configFileName;
    }
}
