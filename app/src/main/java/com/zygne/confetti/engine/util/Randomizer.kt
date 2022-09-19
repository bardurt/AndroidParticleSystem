package com.zygne.confetti.engine.util

object Randomizer {
    fun roundInteger(min: Int, max: Int): Int {
        return (min + Math.random() * (max - min + 1)).toInt()
    }

    fun roundDouble(min: Float, max: Float): Float {
        return (min + (max - min) * Math.random()).toFloat()
    }
}