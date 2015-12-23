package com.mattdahepic.superhardmode.config;

import com.mattdahepic.mdecore.config.annot.Comment;
import com.mattdahepic.mdecore.config.annot.Config;
import com.mattdahepic.mdecore.config.annot.Range;
import com.mattdahepic.mdecore.config.sync.ConfigProcessor;
import com.mattdahepic.mdecore.config.sync.ConfigSyncable;
import com.mattdahepic.mdecore.helpers.ItemHelper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class SHMConfig extends ConfigSyncable {
    public static final String CAT_BYPASS = "bypass";
    public static final String CAT_TORCHES = "torches";
    public static final String CAT_SOUNDS = "sounds";
    public static final String CAT_WORLD = "world";
    public static final String CAT_FIRE = "fire";

    @Config(CAT_BYPASS) @Comment({"Should Super Hard Mode player features be disabled for players in creative?"}) public static boolean bypassCreative = true;
    @Config(CAT_BYPASS) @Comment({"Should Super Hard Mode player features be disabled for operators?"}) public static boolean bypassOps = false;

    @Config(CAT_TORCHES) @Comment({"As you venture deeper into the core of the world, the air becomes thinner.","Below what Y-level should torches pop off the wall due to lack of air?"}) @Range(min=0,max=255) public static int torchMinY = 30;
    @Config(CAT_TORCHES) @Comment({"Since torches are a stick with fire on them, should they go out in the rain?"}) public static boolean torchRainBreak = true;
    @Config(CAT_TORCHES) @Comment({"Should players be unable to place torches on soft blocks like dirt and sand?","Idea is that players don\'t litter the landscape with torches."}) public static boolean preventTorchPlacingOnSoftBlocks = true;

    @Config(CAT_SOUNDS) @Comment({"Should a fizz sound be played when a torch's placement has been blocked or when a torch goes out in the rain?"}) public static boolean torchSounds = true;

    @Config(CAT_WORLD) @Comment({"Should blocks soften when ores ner them are removed?"}) public static boolean softenBlocksOnMine = true;
    @Config(CAT_WORLD) @Comment({"A list of blocks that soften blocks around them when mined.","Formatted as modid:block@meta"}) private static String[] oresThatSoften = new String[]{"minecraft:coal_ore@0","minecraft:iron_ore@0","minecraft:lapis_ore@0","minecraft:gold_ore@0","minecraft:redstone_ore@0","minecraft:diamond_ore@0"};
    public static List<ItemStack> blocksThatSoften = new ArrayList<ItemStack>();
    @Config(CAT_WORLD) @Comment({"A list of blocks that can be softened, as to not destroy player built structures.","Formatted as modid:block@meta"}) private static String[] oresThatBeSoftened = new String[]{"minecraft:stone@0","minecraft:stone@1","minecraft:stone@3","minecraft:stone@5","minecraft:dirt@0"};
    public static List<ItemStack> blocksThatBeSoftened = new ArrayList<ItemStack>();

    @Config(CAT_FIRE) @Comment({"What chance should breaking netherrack have of starting a fire?","Set to 0 to disable."}) @Range(min = 0,max = 100) public static int netherrackFireChance = 75;

    private static ConfigSyncable INSTANCE;
    public static ConfigSyncable instance(String configName) {
        if (INSTANCE == null) {
            INSTANCE = new SHMConfig(configName);
        }
        return INSTANCE;
    }

    public static ConfigProcessor processor;

    protected SHMConfig(String configName) {
        super(configName);
    }
    @Override
    public void init() {
        addSection(CAT_BYPASS);
        addSection(CAT_TORCHES);
        addSection(CAT_SOUNDS);
        addSection(CAT_WORLD);
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

    public static boolean shouldPlayerBypass (EntityPlayer p) {
        return (bypassCreative && p.capabilities.isCreativeMode) || (bypassOps && ArrayUtils.contains(MinecraftServer.getServer().getConfigurationManager().getOppedPlayerNames(),p.getName()));
    }
    public static void finializeConfigValues () {
        for (String name : oresThatSoften) {
            String blockName = name.substring(0,name.indexOf('@'));
            int meta = Integer.parseInt(name.substring(name.indexOf('@')));
            blocksThatSoften.add(ItemHelper.getItemFromName(blockName,meta));
        }
    }
}
