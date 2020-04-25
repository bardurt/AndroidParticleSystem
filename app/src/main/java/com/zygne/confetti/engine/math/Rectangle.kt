package com.zygne.confetti.engine.math

/**
 * Created by Bardur Thomsen on 9/27/18.
 */
class Rectangle(x: Float, y: Float, width: Float, height: Float) {

    private val lowerLeft: Vector2

    var width: Float
        private set

    var height: Float
        private set

    operator fun set(x: Float, y: Float, width: Float, height: Float) {
        lowerLeft[x] = y
        this.width = width
        this.height = height
    }

    fun overlapRectangles(r1: Rectangle, r2: Rectangle): Boolean {
        return if (r1.lowerLeft.x < r2.lowerLeft.x + r2.width && r1.lowerLeft.x + r1.width > r2.lowerLeft.x && r1.lowerLeft.y < r2.lowerLeft.y + r2.height && r1.lowerLeft.y + r1.height > r2.lowerLeft.y) {
            true
        } else {
            false
        }
    }

    fun multiply(scaler: Float) {
        width *= scaler
        height *= scaler
    }

    init {
        lowerLeft = Vector2(x, y)
        this.width = width
        this.height = height
    }
}