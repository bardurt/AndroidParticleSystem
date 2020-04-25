package com.zygne.confetti.engine.particles

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.zygne.confetti.engine.components.DynamicObject2d
import com.zygne.confetti.engine.math.Rectangle
import com.zygne.confetti.engine.math.Vector2
import com.zygne.confetti.engine.util.Randomizer

/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 04/09/2018.
 */
abstract class BaseParticle : DynamicObject2d {
    @JvmField
    protected var paint : Paint? = null
    var state = STATE_ALIVE// particle is alive or dead = 0
    var age = 0// current age of the particle = 0
    var lifetime = DEFAULT_LIFETIME// particle dies when it reaches this value = 0
    private var expansion = DEFAULT_EXPANSION // rate of expansion = 0f
    private var alpha = 255 // alpha value of color
    private var alphaRate = 1 // rate at which alpha value decreases = 0
    private val fadeAlpha = true // should alpha value be reduced
    private val shouldExpand = true

    constructor() {}
    constructor(x: Float, y: Float) {
        position = Vector2(x, y)
        bounds = Rectangle(x, y, 0f, 0f)
        velocity = Vector2()
        paint = Paint()
        paint!!.color = Color.RED
        init(x, y)
    }

    constructor(x: Float, y: Float, width: Float, height: Float) : super(x, y, width, height) {
        init(x, y)
    }

    private fun init(x: Float, y: Float) {
        position!![x] = y
        state = STATE_ALIVE
        val radius = Randomizer.rndInt(1, MAX_DIMENSION)
        bounds!![x, y, radius.toFloat()] = radius.toFloat()
        lifetime = DEFAULT_LIFETIME
        age = 0
        velocity = Vector2(Randomizer.rndDbl(0f, MAX_SPEED * 2.toFloat()) - MAX_SPEED,
                Randomizer.rndDbl(0f, MAX_SPEED * 2.toFloat()) - MAX_SPEED)
        alpha = 255
        alphaRate = ((alpha + 5) / lifetime).toInt()
        expansion = DEFAULT_EXPANSION
        // smoothing out the diagonal speed
        if (velocity!!.distSquared(0f, 0f) > MAX_SPEED * MAX_SPEED) {
            velocity!!.multiply(0.7f)
        }
        randomizeColor()
    }

    fun setExpansion(expansion: Float) {
        this.expansion = expansion
    }

    val isAlive: Boolean
        get() = state == STATE_ALIVE

    val isDead: Boolean
        get() = state == STATE_DEAD

    fun reset(x: Float, y: Float) {
        init(x, y)
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
            paint!!.alpha = alpha
        }
    }

    private fun randomizeColor() {
        paint!!.setARGB(255, Randomizer.rndInt(0, 255), Randomizer.rndInt(0, 255), Randomizer.rndInt(0, 255))
    }

    override fun render(canvas: Canvas) {
        throw RuntimeException("BaseParticle.render(Canvas canvas) should only be called by the child class!")
    }

    companion object {
        const val STATE_ALIVE = 0 // particle is alive
        const val STATE_DEAD = 1 // particle is dead
        const val DEFAULT_LIFETIME = 80f // default lifetime for a particle
        const val MAX_DIMENSION = 8 // the maximum width or height
        const val MAX_SPEED = 8 // maximum speed (per update)
        const val DEFAULT_EXPANSION = 1.01f
    }
}