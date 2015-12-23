package com.mattdahepic.superhardmode.helper;

import com.mattdahepic.superhardmode.event.BreakEventHandler;
import com.mattdahepic.superhardmode.event.PlaceEventHandler;

public class CommonProxy {
    public void registerEventHandlers () {
        PlaceEventHandler.init();
        BreakEventHandler.init();
    }
}
