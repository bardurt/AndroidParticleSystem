package com.zygne.confetti.engine.explosions

import android.graphics.Canvas
import com.zygne.confetti.engine.particles.BaseParticle
import com.zygne.confetti.engine.particles.CircularParticle

/**
 * Created by Bardur Thomsen on 9/13/18.
 */
class Explosion(x: Float, y: Float, particleNr: Int) : BaseExplosion(x, y, particleNr) {

    override fun update(deltaTime: Float) {
        if (state != STATE_DEAD) {
            var isDead = true
            for (particle in particles) {
                if (particle!!.isAlive) {
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

    companion object {
        private val TAG = Explosion::class.java.simpleName
    }

    init {
        state = STATE_ALIVE
        particles = arrayOfNulls(particleNr)
        for (i in particles.indices) {
            val p: BaseParticle = CircularParticle(x, y, 0f, 0f)
            particles[i] = p
        }
    }
}