package com.zygne.confetti.engine.explosions

import com.zygne.confetti.engine.components.DynamicObject2d
import com.zygne.confetti.engine.particles.BaseParticle
import com.zygne.confetti.engine.physics.Physics

abstract class BaseExplosion(x: Float, y: Float, protected val numberOfParticles: Int) : DynamicObject2d(x, y) {

    @JvmField
    var state = 0

    var gravity : Float = 0f // the gravity of the explosion (+ upward, - down) = 0f

    var wind  : Float = 0f // speed of wind on horizontal = 0f

    @JvmField
    var particles: Array<BaseParticle?> = emptyArray()

    @JvmField
    var physics: Physics = Physics()

    override fun toString(): String {
        return this.javaClass.simpleName + ": { x: " + position!!.x + ", y: " + position!!.y + ", particles: " + numberOfParticles + " }"
    }

    // helper methods -------------------------
    val isAlive: Boolean
        get() = state == STATE_ALIVE

    val isDead: Boolean
        get() = state == STATE_DEAD

    companion object {
        const val STATE_ALIVE = 0 // at least 1 particle is alive
        const val STATE_DEAD = 1 // all particles are dead
    }

}