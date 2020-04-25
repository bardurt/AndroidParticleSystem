package com.zygne.confetti.engine.particles

import android.graphics.Canvas

/**
 * Created by Bardur Thomsen on 9/13/18.
 */
class CircularParticle : BaseParticle {
    constructor(x: Float, y: Float) : super(x, y) {}
    constructor(x: Float, y: Float, width: Float, height: Float) : super(x, y, width, height) {}

    override fun render(canvas: Canvas) {
        if (isAlive) {
            canvas.drawCircle(position!!.x, position!!.y, bounds!!.width, paint)
        }
    }
}