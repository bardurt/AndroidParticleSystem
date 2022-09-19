package com.zygne.confetti.engine.math

import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class Vector2 {
    var x = 0f
        private set
    var y = 0f
        private set

    constructor() {}
    constructor(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    constructor(other: Vector2) {
        x = other.x
        y = other.y
    }

    fun cpy(): Vector2 {
        return Vector2(x, y)
    }

    operator fun set(x: Float, y: Float): Vector2 {
        this.x = x
        this.y = y
        return this
    }

    fun set(other: Vector2): Vector2 {
        x = other.x
        y = other.y
        return this
    }

    fun add(x: Float, y: Float): Vector2 {
        this.x += x
        this.y += y
        return this
    }

    fun add(other: Vector2): Vector2 {
        x += other.x
        y += other.y
        return this
    }

    fun sub(x: Float, y: Float): Vector2 {
        this.x -= x
        this.y -= y
        return this
    }

    fun sub(other: Vector2): Vector2 {
        x -= other.x
        y -= other.y
        return this
    }

    fun multiply(scalar: Float): Vector2 {
        x *= scalar
        y *= scalar
        return this
    }

    fun len(): Float {
        return sqrt(x * x + y * y.toDouble()).toFloat()
    }

    fun nor(): Vector2 {
        val len = len()
        if (len != 0f) {
            x /= len
            y /= len
        }
        return this
    }

    fun angle(): Float {
        var angle = atan2(y.toDouble(), x.toDouble()).toFloat() * TO_DEGREES
        if (angle < 0) angle += 360f
        return angle
    }

    fun rotate(angle: Float): Vector2 {
        val rad = angle * TO_RADIANS
        val cos = cos(rad.toDouble()).toFloat()
        val sin = sin(rad.toDouble()).toFloat()
        val newX = x * cos - y * sin
        val newY = x * sin + y * cos
        x = newX
        y = newY
        return this
    }

    fun dist(other: Vector2): Float {
        val distX = x - other.x
        val distY = y - other.y
        return sqrt(distX * distX + distY * distY.toDouble()).toFloat()
    }

    fun dist(x: Float, y: Float): Float {
        val distX = this.x - x
        val distY = this.y - y
        return sqrt(distX * distX + distY * distY.toDouble()).toFloat()
    }

    fun distSquared(other: Vector2): Float {
        val distX = x - other.x
        val distY = y - other.y
        return distX * distX + distY * distY
    }

    fun distSquared(x: Float, y: Float): Float {
        val distX = this.x - x
        val distY = this.y - y
        return distX * distX + distY * distY
    }

    companion object {
        private const val TO_RADIANS = 1 / 180.0f * Math.PI.toFloat()
        private const val TO_DEGREES = 1 / Math.PI.toFloat() * 180
    }
}