package com.mattdahepic.superhardmode;

import com.mattdahepic.mdecore.update.UpdateChecker;
import com.mattdahepic.superhardmode.config.SHMConfigMain;
import com.mattdahepic.superhardmode.config.SHMConfigWorld;
import com.mattdahepic.superhardmode.helper.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(modid = SuperHardMode.MODID,name = SuperHardMode.NAME,version = SuperHardMode.VERSION,dependencies = SuperHardMode.DEPENDENCIES)
public class SuperHardMode {
    @Mod.Instance(SuperHardMode.MODID)
    public SuperHardMode instance;

    public static final String MODID = "superhardmode";
    public static final String NAME = "Super Hard Mode";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:mdecore@[1.8.8-1.6.1,);";
    public static final String UPDATE_URL = "https://raw.githubusercontent.com/MattDahEpic/Version/master/"+ MinecraftForge.MC_VERSION+"/"+MODID+".txt";
    public static final String CLIENT_PROXY = "com.mattdahepic.superhardmode.helper.ClientProxy";
    public static final String COMMON_PROXY = "com.mattdahepic.superhardmode.helper.CommonProxy";

    public static final Logger logger = LogManager.getLogger(MODID);
    public static final Random RNGesus = new Random();

    public static final String CONFIGNAME_MAIN = MODID;
    public static final String CONFIGNAME_WORLD = MODID+"-world";
    public static final String CONFIGNAME_MOBS = MODID+"-mobs";
    public static final String CONFIGNAME_FARMING = MODID+"-farming";

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(instance);
        SHMConfigMain.instance().initialize(e);
        SHMConfigWorld.instance().initialize(e);
        SHMConfigWorld.finializeConfigValues();
        proxy.registerEventHandlers();
    }
    @Mod.EventHandler
    public void init (FMLInitializationEvent e) {
        UpdateChecker.checkRemote(MODID,UPDATE_URL);
    }
    @SubscribeEvent
    public void onJoin (PlayerEvent.PlayerLoggedInEvent e) {
        UpdateChecker.printMessageToPlayer(MODID,e.player);
    }
}
