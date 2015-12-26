package com.mattdahepic.superhardmode.helper;

import com.mattdahepic.superhardmode.event.*;

public class CommonProxy {
    public void registerEventHandlers () {
        PlaceEventHandler.init();
        BreakEventHandler.init();
        PlayerInteractEventHandler.init();
        LivingAttackEventHandler.init();
    }
}
