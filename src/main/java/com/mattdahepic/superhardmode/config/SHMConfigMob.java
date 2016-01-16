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
    public static final String CAT_CREEPER = "creeper";
    public static final String CAT_BLAZE = "blaze";
    public static final String CAT_PIGMAN = "pigman";
    public static final String CAT_WITCH = "witch";
    public static final String CAT_GHAST = "ghast";
    public static final String CAT_SPIDER = "spider";
    public static final String CAT_ENDERMAN = "enderman";

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
    @Config(CAT_PLAYER) @Comment({"Should potion effect be applied when the player takes certain environmental damage?","Example: slowness to emulate broken legs after taking fall damage?"}) public static boolean playerDamageEffects = true;

    @Config(CAT_CREEPER) @Comment({"What is the chance for charged creepers to spawn naturally?","Set to 0 to disable."}) @Range(min = 0,max = 100) public static int creeperPoweredSpawnChance = 15;
    @Config(CAT_CREEPER) @Comment({"Should charged creepers explode when hit?"}) public static boolean creeperPoweredHitExplode = true;
    @Config(CAT_CREEPER) @Comment({"What chance should all creepers have to summon primed TNT on death?","Set to 0 to disable"}) @Range(min = 0,max = 100) public static int creeperDropTNTOnDeathChance = 3;
    @Config(CAT_CREEPER) @Comment({"Should creepers that were on fire when they died fly up into the air and explode into fireworks?"}) public static boolean creeperFireDeathFireworks = true;

    @Config(CAT_BLAZE) @Comment({"What chance should blazes have to spawn in the nether naturally?","Set to 0 to disable."}) @Range(min = 0,max = 100) public static int blazeNetherSpawnChance = 30;
    @Config(CAT_BLAZE) @Comment({"What chance should blazes have to spawn near bedrock in the overworld?","Set to 0 to disable."}) @Range(min = 0,max = 100) public static int blazeOverworldLowLevelSpawnChance = 30;
    @Config(CAT_BLAZE) @Comment({"Should blazes drop extra items such as glowstone and gunpowder?"}) public static boolean blazeExtraDrops = true;
    @Config(CAT_BLAZE) @Comment({"What chance should blazes have of exploding on death in the overworld?","Set to 0 to disable"}) @Range(min = 0,max = 100) public static int blazeOverworldDeathExplode = 50;
    @Config(CAT_BLAZE) @Comment({"What chance should blazes have to multiply when killed in the nether?","Final chance is calculated by dividing this value by how many times the blaze has already respawned."}) @Range(min = 0,max = 100) public static int blazeNetherKillRespawnChance = 50;

    @Config(CAT_PIGMAN) @Comment({"Should pigmen drop nether warts when killed in a nether fortress?"}) public static boolean pigmanDropWartInFortress = true;
    @Config(CAT_PIGMAN) @Comment({"What chance should pigmen have to drop nether warts anywhere in the nether when killed?","Set to 0 to disable."}) @Range(min = 0,max = 100) public static int pigmenDropWartInNetherChance = 15;
    @Config(CAT_PIGMAN) @Comment({"Should pigmen always be aggro in the nether?"}) public static boolean pigmenAlwaysAggroNether = true;
    @Config(CAT_PIGMAN) @Comment({"Should pigmen spawn in the overworld where lightning strikes?"}) public static boolean pigmenOverworldLightningSpawn = true;

    @Config(CAT_WITCH) @Comment({"What chance should witches have to spawn on the surface instead of a zombie?","Set to 0 to disable"}) @Range(min = 0,max = 100) public static int witchNatSpawnChance = 13;

    @Config(CAT_GHAST) @Comment({"Should ghasts be immune to arrows?","When set to true the loot dropped by ghasts is MUCH better."}) public static boolean ghastImmuneToArrows = true;
    @Config(CAT_GHAST) @Comment({"What should the XP a ghast drops be multiplied by if it's immune to arrows?","Final Ghast XP drop = floor(base xp drop * this value)"}) @Range(min = 0f) public static float ghastXpMultiplier = 7f;
    @Config(CAT_GHAST) @Comment({"What should the items a ghast drops be multiplied by if it's immune to arrows","for each drop { stack size = floor(base stack size * this value) }"}) @Range(min = 0f) public static float ghastDropMultiplier = 3f;

    @Config(CAT_SPIDER) @Comment({"How fast should spiders move?","Vanilla value is 0.699999988079071"}) @Range(min = 0f,max = 100f) public static float spiderHellaFast = 3f;
    @Config(CAT_SPIDER) @Comment({"What chance should spiders have to spawn undergound instead of a zombie?"}) @Range(min = 0,max = 100) public static int spiderUndergroundSpawnChance = 30;
    @Config(CAT_SPIDER) @Comment({"Should spiders place webs on death (with some exceptions)?"}) public static boolean spiderPlaceWebsOnDeath = true;

    @Config(CAT_ENDERMAN) @Comment({"What chance should endermen have to teleport the player if they are aggro at the player when they teleport?"}) @Range(min = 0,max = 100) public static int endermanTeleportPlayerChance = 75;

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
