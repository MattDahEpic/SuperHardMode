package com.mattdahepic.superhardmode.config;

import com.mattdahepic.mdecore.config.annot.Comment;
import com.mattdahepic.mdecore.config.annot.Config;
import com.mattdahepic.mdecore.config.annot.RestartReq;
import com.mattdahepic.mdecore.config.sync.ConfigProcessor;
import com.mattdahepic.mdecore.config.sync.ConfigSyncable;
import com.mattdahepic.superhardmode.SuperHardMode;

public class SHMConfigFarming extends ConfigSyncable {
    @Config @Comment({"Should players be allowed to use bonemeal on mushrooms?"}) public static boolean mushroomBoneMeal = false;
    @Config @Comment({"Should players be allowed to farm nether warts?"}) public static boolean netherwartFarming = false;
    @Config @Comment({"Show iron golems drop iron?"}) public static boolean ironFromGolems = false;
    @Config @Comment({"Should melon and pumpkin seed crafting be allowed"}) @RestartReq(RestartReqs.REQUIRES_MC_RESTART) public static boolean disableMelonPumpkinSeedCrafting = true;

    private static ConfigSyncable INSTANCE;
    public static ConfigSyncable instance() {
        if (INSTANCE == null) {
            INSTANCE = new SHMConfigFarming(SuperHardMode.CONFIGNAME_FARMING);
        }
        return INSTANCE;
    }

    public static ConfigProcessor processor;

    protected SHMConfigFarming(String configName) {
        super(configName);
    }
    @Override
    public void init() {
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
