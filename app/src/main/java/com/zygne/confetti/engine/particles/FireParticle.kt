package com.zygne.confetti.engine.particles

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.zygne.confetti.engine.math.Rectangle
import com.zygne.confetti.engine.math.Vector2
import com.zygne.confetti.engine.util.Randomizer

class FireParticle(x: Float, y: Float) : BaseParticle(x, y) {

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
        val radius = Randomizer.randomInteger(3, 18)
        bounds!![x, y, radius.toFloat()] = radius.toFloat()
        lifetime = 40f
        age = 0
        velocity = Vector2(
            Randomizer.randomDouble(0f, 1 * 1.toFloat()) - 1,
            Randomizer.randomDouble(0f, 1 * 1.toFloat()) - 1
        )
        alpha = 255
        alphaRate = ((alpha + 5) / lifetime).toInt()
        expansion = DEFAULT_EXPANSION

        if (velocity!!.distSquared(0f, 0f) > 2 * 2) {
            velocity!!.multiply(0.7f)
        }
        randomizeColor()
    }

    override fun reset(x: Float, y: Float) {
        setUp(x, y)
    }

    override fun randomizeColor() {
        paint.setARGB(255, 226, Randomizer.randomInteger(149, 226), 4)
    }

    override fun render(canvas: Canvas) {
        if (isAlive) {
            canvas.drawCircle(position!!.x, position!!.y, bounds!!.width, paint)
        }
    }

    override fun update(dt: Float) {
        if (state != STATE_DEAD) {
            age++
            position!!.sub(velocity!!)
            alpha -= alphaRate
            bounds!!.multiply(1 / expansion)
            if (age >= lifetime) { // reached the end if its life
                state = STATE_DEAD
            }
            if (alpha < 10) {
                state = STATE_DEAD
            }
            paint.alpha = alpha
        }
    }
}