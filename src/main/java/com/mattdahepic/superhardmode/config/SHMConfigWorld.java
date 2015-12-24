package com.mattdahepic.superhardmode.config;

import com.mattdahepic.mdecore.config.annot.Comment;
import com.mattdahepic.mdecore.config.annot.Config;
import com.mattdahepic.mdecore.config.annot.Range;
import com.mattdahepic.mdecore.config.sync.ConfigProcessor;
import com.mattdahepic.mdecore.config.sync.ConfigSyncable;
import com.mattdahepic.mdecore.helpers.ItemHelper;
import com.mattdahepic.superhardmode.SuperHardMode;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SHMConfigWorld extends ConfigSyncable {
    public static final String CAT_TORCHES = "torches";
    public static final String CAT_FIRE = "fire";
    public static final String CAT_LIMBUILD = "limited_building";

    @Config(CAT_TORCHES) @Comment({"As you venture deeper into the core of the world, the air becomes thinner.","Below what Y-level should torches pop off the wall due to lack of air?"}) @Range(min=0,max=255) public static int torchMinY = 30;
    @Config(CAT_TORCHES) @Comment({"Since torches are a stick with fire on them, should they go out in the rain?"}) public static boolean torchRainBreak = true;
    @Config(CAT_TORCHES) @Comment({"Should players be unable to place torches on soft blocks like dirt and sand?","Idea is that players don\'t litter the landscape with torches."}) public static boolean preventTorchPlacingOnSoftBlocks = true;

    @Config @Comment({"Should blocks soften when ores ner them are removed?"}) public static boolean softenBlocksOnMine = true;
    @Config @Comment({"A list of blocks that soften blocks around them when mined.","Formatted as modid:block@meta"}) public static String[] oresThatSoften = new String[]{"minecraft:coal_ore@0","minecraft:iron_ore@0","minecraft:lapis_ore@0","minecraft:gold_ore@0","minecraft:redstone_ore@0","minecraft:diamond_ore@0"};
    public static List<ItemStack> blocksThatSoften = new ArrayList<ItemStack>();
    @Config @Comment({"A list of blocks that can be softened, as to not destroy player built structures.","Formatted as modid:block@meta"}) public static String[] oresThatBeSoftened = new String[]{"minecraft:stone@0","minecraft:stone@1","minecraft:stone@3","minecraft:stone@5","minecraft:dirt@0"};
    public static List<ItemStack> blocksThatBeSoftened = new ArrayList<ItemStack>();

    @Config(CAT_FIRE) @Comment({"What chance should breaking netherrack have of starting a fire?","Set to 0 to disable."}) @Range(min = 0,max = 100) public static int netherrackFireChance = 75;
    @Config(CAT_FIRE) @Comment({"Should the player be set on fire if they try to put out a fire by hand?","Encourages breaking the block below or using water to set out fires."}) public static boolean setPlayersOnFireWhenBreakingFire = true;

    @Config(CAT_LIMBUILD) @Comment({"Should the player be disallowed to place blocks directly below them?","Prevents \"nerd poles\" aka 1 block towers"}) public static boolean antiNerdPole = true;
    @Config(CAT_LIMBUILD) @Comment({"Should the player be disallowed to place blocks in unsafe locations?","Unsafe locations include in lava and in midair."}) public static boolean disallowUnsafePlacement = true;

    private static ConfigSyncable INSTANCE;
    public static ConfigSyncable instance() {
        if (INSTANCE == null) {
            INSTANCE = new SHMConfigWorld(SuperHardMode.CONFIGNAME_WORLD);
        }
        return INSTANCE;
    }

    public static ConfigProcessor processor;

    protected SHMConfigWorld(String configName) {
        super(configName);
    }
    @Override
    public void init() {
        addSection(CAT_TORCHES);
        addSection(CAT_FIRE);
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

    public static void finializeConfigValues () {
        for (String name : oresThatSoften) {
            String blockName = name.substring(0,name.indexOf('@'));
            int meta = Integer.parseInt(name.substring(name.indexOf('@')+1));
            blocksThatSoften.add(ItemHelper.getItemFromName(blockName, meta));
        }
        for (String name : oresThatBeSoftened) {
            String blockName = name.substring(0,name.indexOf('@'));
            int meta = Integer.parseInt(name.substring(name.indexOf('@')+1));
            blocksThatBeSoftened.add(ItemHelper.getItemFromName(blockName,meta));
        }
    }
}
