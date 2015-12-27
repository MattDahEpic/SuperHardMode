package com.mattdahepic.superhardmode.helper;

import java.util.Random;

//TODO: MOVE THIS TO MDECORE
public class RandomHelper {
    public static final Random rand = new Random();
    /**
     * Returns true or false based upon a random chance.
     *
     * @param chance between 0 and 100
     * @return true or false depending on random
     */
    public static boolean randomChance (int chance) {
        return rand.nextInt(100) < chance;
    }
}
