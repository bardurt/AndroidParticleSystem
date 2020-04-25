package com.zygne.confetti.engine.physics

import com.zygne.confetti.engine.components.DynamicObject2d

class Physics {
    var wind: Float
    var gravity: Float
    private var drag: Float = 1f

    constructor() {
        wind = 0f
        gravity = 0f
        drag = 1f
    }

    constructor(wind: Float, gravity: Float, drag: Float) {
        this.wind = wind
        this.gravity = gravity
        if (drag >= 1) {
            this.drag = drag
        }
        this.drag = 1 / this.drag
    }

    fun getDrag(): Float {
        return drag
    }

    fun setDrag(drag: Float) {
        if (drag >= 1) {
            this.drag = drag
        }
        this.drag = 1 / this.drag
    }

    fun update(object2d: DynamicObject2d) {
        object2d.velocity!!.add(wind, 0f)
        object2d.velocity!!.add(0f, gravity)
        object2d.velocity!!.multiply(drag)
    }
}