package com.zygne.confetti.engine.explosions

import android.graphics.Canvas
import com.zygne.confetti.engine.components.DynamicObject2d
import com.zygne.confetti.engine.particles.BaseParticle
import com.zygne.confetti.engine.particles.CircularParticle

class Explosion(x: Float, y: Float, particleNr: Int, var speed: Int) :
    BaseExplosion(x, y, particleNr) {

    override fun update(deltaTime: Float) {
        if (state != STATE_DEAD) {
            var isDead = true
            for (particle in particles) {
                if (particle!!.isAlive) {
                    physics.update(particle as DynamicObject2d)
                    particle.update(deltaTime)
                    isDead = false
                }
            }
            if (isDead) { //this.state = STATE_DEAD;
                reset()
            }
        }
    }

    private fun reset() {
        for (particle in particles) {
            particle!!.reset(position!!.x, position!!.y)
        }
    }

    override fun render(canvas: Canvas) {
        for (particle in particles) {
            particle!!.render(canvas)
        }
    }

    override fun toString(): String {
        return "Explosion { x: " + position!!.x + ", y: " + position!!.y + ", particles: " + particles.size + " }"
    }

    init {
        state = STATE_ALIVE
        particles = arrayOfNulls(particleNr)
        for (i in particles.indices) {
            val p: BaseParticle = CircularParticle(x, y, speed)
            particles[i] = p
        }
    }

    companion object {
        private val TAG = Explosion::class.java.simpleName
    }
}