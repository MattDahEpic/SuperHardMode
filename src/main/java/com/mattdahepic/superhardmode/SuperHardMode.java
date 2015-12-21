package com.mattdahepic.superhardmode;

import com.mattdahepic.mdecore.update.UpdateChecker;
import com.mattdahepic.superhardmode.config.SHMConfig;
import com.mattdahepic.superhardmode.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = SuperHardMode.MODID,name = SuperHardMode.NAME,version = SuperHardMode.VERSION,dependencies = SuperHardMode.DEPENDENCIES)
public class SuperHardMode {
    @Mod.Instance(SuperHardMode.MODID)
    public SuperHardMode instance;

    public static final String MODID = "superhardmode";
    public static final String NAME = "Super Hard Mode";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:mdecore@[1.8.8-1.6.1,);";
    public static final String UPDATE_URL = "https://raw.githubusercontent.com/MattDahEpic/Version/master/"+ MinecraftForge.MC_VERSION+"/"+MODID+".txt";
    public static final String CLIENT_PROXY = "com.mattdahepic.superhardmode.proxy.ClientProxy";
    public static final String COMMON_PROXY = "com.mattdahepic.superhardmode.proxy.CommonProxy";

    public static final Logger logger = LogManager.getLogger(MODID);

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(instance);
        SHMConfig.instance(MODID).initialize(e);
        SHMConfig.finializeConfigValues();
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
