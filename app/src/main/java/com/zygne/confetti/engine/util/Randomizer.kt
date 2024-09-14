package com.zygne.confetti.engine.util

object Randomizer {
    fun randomInteger(min: Int, max: Int): Int {
        return (min + Math.random() * (max - min + 1)).toInt()
    }

    fun randomDouble(min: Float, max: Float): Float {
        return (min + (max - min) * Math.random()).toFloat()
    }
}