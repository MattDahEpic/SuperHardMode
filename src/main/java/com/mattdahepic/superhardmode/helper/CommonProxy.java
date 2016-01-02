package com.mattdahepic.superhardmode.helper;

import com.mattdahepic.superhardmode.event.BlockEventHandler;
import com.mattdahepic.superhardmode.event.LivingEventHandler;
import com.mattdahepic.superhardmode.event.PlayerEventHandler;

public class CommonProxy {
    public void registerEventHandlers () {
        BlockEventHandler.init();
        LivingEventHandler.init();
        PlayerEventHandler.init();
    }
}
