package com.mattdahepic.superhardmode.asm;

import net.minecraftforge.fml.relauncher.IFMLCallHook;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

@IFMLLoadingPlugin.TransformerExclusions({"com.mattdahepic.superhardmode"})
@IFMLLoadingPlugin.Name("SHM-Core")
public class SHMCoreMod implements IFMLLoadingPlugin, IFMLCallHook {
    @Override
    public String[] getASMTransformerClass () {
        return new String[]{"com.mattdahepic.superhardmode.asm.WeatherEventTransformer","com.mattdahepic.superhardmode.asm.IThrowableLandEventTransformer"};
    }
    @Override
    public String getModContainerClass () {
        return "com.mattdahepic.superhardmode.asm.SHMDummyMod";
    }
    @Override
    public String getSetupClass () {
        return "com.mattdahepic.superhardmode.asm.SHMCoreMod";
    }
    @Override
    public void injectData (Map<String, Object> data) {}
    @Override
    public String getAccessTransformerClass () {
        return null;
    }
    @Override
    public Void call () throws Exception {
        return null;
    }
}
