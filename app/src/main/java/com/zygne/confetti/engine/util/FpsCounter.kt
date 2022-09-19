package com.zygne.confetti.engine.util

import android.util.Log

class FpsCounter {
    private var mLastTime: Long = 0
    var fps = 0
        private set
    private var ifps = 0
    fun countFrames() {
        val now = System.currentTimeMillis()
        println("FPS:$fps")
        ifps++
        if (now > mLastTime + 1000) {
            mLastTime = now
            fps = ifps
            ifps = 0
            Log.d(TAG, "Frames per second : $fps")
        }
    }

    companion object {
        private val TAG = FpsCounter::class.java.simpleName
    }
}