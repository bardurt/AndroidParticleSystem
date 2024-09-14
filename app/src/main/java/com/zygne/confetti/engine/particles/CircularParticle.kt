package com.zygne.confetti.engine.particles

import android.graphics.Canvas

class CircularParticle(x: Float, y: Float) : BaseParticle(x, y) {

    override fun render(canvas: Canvas) {
        if (isAlive) {
            canvas.drawCircle(position!!.x, position!!.y, bounds!!.width, paint)
        }
    }
}