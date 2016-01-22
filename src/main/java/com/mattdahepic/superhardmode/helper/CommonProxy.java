package com.mattdahepic.superhardmode.helper;

import com.mattdahepic.superhardmode.event.BlockEventHandler;
import com.mattdahepic.superhardmode.event.EntityEventHandler;

public class CommonProxy {
    public void registerEventHandlers () {
        BlockEventHandler.init();
        EntityEventHandler.init();
    }
}
