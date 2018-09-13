package com.zygne.confetti.engine.util;

/**
 * Created by Bardur Thomsen on 9/13/18.
 */
public class Randomizer {

    // Return an integer that ranges from min inclusive to max inclusive.
    public static int rndInt(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }

    public static float rndDbl(float min, float max) {
        return (float) (min + (max - min) * Math.random());
    }
}
