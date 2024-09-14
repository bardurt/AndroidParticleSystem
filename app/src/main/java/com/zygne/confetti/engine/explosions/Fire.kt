package com.zygne.confetti.engine.explosions

import android.graphics.Canvas
import com.zygne.confetti.engine.components.DynamicObject2d
import com.zygne.confetti.engine.particles.BaseParticle
import com.zygne.confetti.engine.particles.FireParticle

class Fire(x: Float, y: Float, var particleNr: Int) : BaseExplosion(x, y, particleNr) {
    private val minSize = 100
    private var maxSize = 500
    private var size: Int
    private var timeToUpdate = 250f
    private var ellapsedTime = 0f;

    init {
        state = STATE_ALIVE
        if (particleNr < minSize) {
            particleNr = minSize
        }
        particles = arrayOfNulls(particleNr)
        for (i in 0 until minSize) {
            val p: BaseParticle = FireParticle(x, y)
            particles[i] = p
        }
        size = minSize
        maxSize = particleNr
    }

    override fun update(dt: Float) {
        ellapsedTime+= dt
        if (ellapsedTime > timeToUpdate) {
            createNewParticle()
            ellapsedTime = 0f
        }

        for (i in 0 until size) {
            if (particles[i]!!.isAlive) {
                physics.gravity = 0.8f
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
            for (i in 0..2) {
                if (size < maxSize) {
                    val p: BaseParticle = FireParticle(position!!.x, position!!.y)
                    particles[size] = p
                    size++
                }
            }
        }
    }

    companion object {
        private val TAG = Fire::class.java.simpleName
    }

}