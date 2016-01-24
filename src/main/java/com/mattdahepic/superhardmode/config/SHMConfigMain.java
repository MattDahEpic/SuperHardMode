package com.mattdahepic.superhardmode.config;

import com.mattdahepic.mdecore.config.annot.Comment;
import com.mattdahepic.mdecore.config.annot.Config;
import com.mattdahepic.mdecore.config.sync.ConfigProcessor;
import com.mattdahepic.mdecore.config.sync.ConfigSyncable;
import com.mattdahepic.mdecore.helpers.EnvironmentHelper;
import com.mattdahepic.superhardmode.SuperHardMode;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import org.apache.commons.lang3.ArrayUtils;

public class SHMConfigMain extends ConfigSyncable {
    public static final String CAT_BYPASS = "bypass";
    public static final String CAT_SOUNDS = "sounds";

    @Config @Comment({"Should some messages be displayed to players for effects that wouldn't fit well with vanilla, or when the player is likely panicking?"}) public static boolean playerMessages = true;

    @Config(CAT_BYPASS) @Comment({"Should Super Hard Mode player features be disabled for players in creative?"}) public static boolean bypassCreative = true;
    @Config(CAT_BYPASS) @Comment({"Should Super Hard Mode player features be disabled for operators?"}) public static boolean bypassOps = false;

    @Config(CAT_SOUNDS) @Comment({"Should a fizz sound be played when a torch's placement has been blocked or when a torch goes out in the rain?"}) public static boolean torchSounds = true;

    private static ConfigSyncable INSTANCE;
    public static ConfigSyncable instance() {
        if (INSTANCE == null) {
            INSTANCE = new SHMConfigMain(SuperHardMode.CONFIGNAME_MAIN);
        }
        return INSTANCE;
    }

    public static ConfigProcessor processor;

    protected SHMConfigMain(String configName) {
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

    /** HELPERS */

    public static boolean shouldPlayerBypass (EntityPlayer p) {
        return p == null || (!EnvironmentHelper.isDeobf && ((bypassCreative && p.capabilities.isCreativeMode) || (bypassOps && ArrayUtils.contains(MinecraftServer.getServer().getConfigurationManager().getOppedPlayerNames(),p.getName()))));
    }
}
