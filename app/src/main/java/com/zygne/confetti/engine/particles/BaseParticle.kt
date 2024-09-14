package com.zygne.confetti.engine.particles

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.zygne.confetti.engine.components.DynamicObject2d
import com.zygne.confetti.engine.math.Rectangle
import com.zygne.confetti.engine.math.Vector2
import com.zygne.confetti.engine.util.Randomizer

abstract class BaseParticle(
    x: Float,
    y: Float,
    val maxSpeed: Int = DEFAULT_MAX_SPEED
) :
    DynamicObject2d(x, y) {
    var paint: Paint
    var state = STATE_ALIVE// particle is alive or dead = 0
    var age = 0// current age of the particle = 0
    var lifetime = DEFAULT_LIFETIME// particle dies when it reaches this value = 0
    var expansion = DEFAULT_EXPANSION // rate of expansion = 0f
    var alpha = 255 // alpha value of color
    var alphaRate = 1 // rate at which alpha value decreases = 0
    val fadeAlpha = true // should alpha value be reduced
    val shouldExpand = true

    init {
        position = Vector2(x, y)
        bounds = Rectangle(x, y, 0f, 0f)
        velocity = Vector2()
        paint = Paint()
        paint.color = Color.RED
        setUp(x, y)
    }

    private fun setUp(x: Float, y: Float) {
        position!![x] = y
        state = STATE_ALIVE
        val radius = Randomizer.randomInteger(1, MAX_DIMENSION)
        bounds!![x, y, radius.toFloat()] = radius.toFloat()
        lifetime = 120f
        age = 0
        velocity = Vector2(
            Randomizer.randomDouble(0f, maxSpeed * 2.toFloat()) - maxSpeed,
            Randomizer.randomDouble(0f, maxSpeed * 2.toFloat()) - maxSpeed
        )
        alpha = 255
        alphaRate = ((alpha + 5) / lifetime).toInt()
        expansion = DEFAULT_EXPANSION
        // smoothing out the diagonal speed
        if (velocity!!.distSquared(0f, 0f) > maxSpeed * maxSpeed) {
            velocity!!.multiply(0.7f)
        }

        randomizeColor()
    }

    val isAlive: Boolean
        get() = state == STATE_ALIVE

    val isDead: Boolean
        get() = state == STATE_DEAD

    open fun reset(x: Float, y: Float) {
        setUp(x, y)
    }

    override fun update(dt: Float) {
        if (state != STATE_DEAD) {
            age++
            position!!.sub(velocity!!)
            alpha -= alphaRate
            bounds!!.multiply(expansion)
            if (age >= lifetime) { // reached the end if its life
                state = STATE_DEAD
            }
            if (alpha < 10) {
                state = STATE_DEAD
            }
            paint.alpha = alpha
        }
    }

    open fun randomizeColor() {
        paint.setARGB(
            255,
            Randomizer.randomInteger(0, 255),
            Randomizer.randomInteger(0, 255),
            Randomizer.randomInteger(0, 255)
        )
    }

    override fun render(canvas: Canvas) {
        throw RuntimeException("BaseParticle.render(Canvas canvas) should only be called by the child class!")
    }

    companion object {
        const val STATE_ALIVE = 0 // particle is alive
        const val STATE_DEAD = 1 // particle is dead
        const val DEFAULT_LIFETIME = 80f // default lifetime for a particle
        const val MAX_DIMENSION = 8 // the maximum width or height
        const val DEFAULT_MAX_SPEED = 8 // maximum speed (per update)
        const val DEFAULT_EXPANSION = 1.01f
    }


}