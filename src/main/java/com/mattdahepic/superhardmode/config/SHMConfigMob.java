package com.mattdahepic.superhardmode.config;

import com.mattdahepic.mdecore.config.annot.Comment;
import com.mattdahepic.mdecore.config.annot.Config;
import com.mattdahepic.mdecore.config.annot.Range;
import com.mattdahepic.mdecore.config.sync.ConfigProcessor;
import com.mattdahepic.mdecore.config.sync.ConfigSyncable;
import com.mattdahepic.superhardmode.SuperHardMode;

public class SHMConfigMob extends ConfigSyncable {
    public static final String CAT_ZOMBIE = "zombie";

    @Config(CAT_ZOMBIE) @Comment({"Should zombies apply slowness to players when they attack and hit?"}) public static boolean zombieHitSlowness = true;
    @Config(CAT_ZOMBIE) @Comment({"Should multiple hits from zombie(s) stack the slowness effect?"}) public static boolean stackZombieHitSlowness = true;
    @Config(CAT_ZOMBIE) @Comment({"What should the maximum level of slowness be for players who are attacked by zombies?"}) @Range(min = 2,max = 127) public static int maxZombieHitSlownessStack = 12;
    @Config(CAT_ZOMBIE) @Comment({"Zombies place a block on death and with this chance, if the block is not broken the zombie respawns","Set to 0 to disable"}) @Range(min = 0,max = 100) public static int zombieRespawnChance = 75;

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
