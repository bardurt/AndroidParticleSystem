package com.zygne.confetti.engine.util;

import android.util.Log;

public class FpsCounter {

    private static final String TAG = FpsCounter.class.getSimpleName();

    private long mLastTime = 0;
    private int fps = 0;
    private int ifps = 0;

    public void countFrames(){
        long now = System.currentTimeMillis();
        System.out.println("FPS:" + fps);
        ifps++;
        if(now > (mLastTime + 1000)) {
            mLastTime = now;
            fps = ifps;
            ifps = 0;

            Log.d(TAG, "Frames per second : " + fps);
        }
    }

    public int getFps(){
        return fps;
    }
}
