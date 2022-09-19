package com.zygne.confetti.engine.explosions

import android.graphics.Canvas
import com.zygne.confetti.engine.components.DynamicObject2d
import com.zygne.confetti.engine.particles.BaseParticle
import com.zygne.confetti.engine.particles.CircularParticle

class Emitter(x: Float, y: Float, var particleNr: Int) : BaseExplosion(x, y, particleNr) {
    private val minSize = 10
    private var maxSize = 100
    private var size: Int

    init {

        state = STATE_ALIVE
        if (particleNr < minSize) {
            particleNr = minSize
        }
        particles = arrayOfNulls(particleNr)
        for (i in 0 until minSize) {
            val p: BaseParticle = CircularParticle(x, y)
            particles[i] = p
        }
        size = minSize
        maxSize = particleNr
    }

    override fun update(dt: Float) {
        createNewParticle()
        for (i in 0 until size) {
            if (particles[i]!!.isAlive) {
                physics.update(particles[i] as DynamicObject2d)
                particles[i]!!.update(dt)
            } else {
                particles[i]!!.reset(position!!.x, position!!.y)
            }
        }
    }

    override fun render(canvas: Canvas) {
        for (particle in particles) {
            particle!!.render(canvas)
        }
    }

    private fun createNewParticle() {
        if (size < maxSize) {
            for (i in 0..4) {
                if (size < maxSize) {
                    val p: BaseParticle = CircularParticle(position!!.x, position!!.y)
                    particles[size] = p
                    size++
                }
            }
        }
    }

    companion object {
        private val TAG = Emitter::class.java.simpleName
    }

}