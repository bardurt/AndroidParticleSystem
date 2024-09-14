package com.zygne.confetti.engine.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.zygne.confetti.engine.explosions.BaseExplosion
import com.zygne.confetti.engine.explosions.Emitter
import com.zygne.confetti.engine.explosions.Explosion
import com.zygne.confetti.engine.explosions.Fire
import com.zygne.confetti.engine.particles.BaseParticle
import com.zygne.confetti.engine.physics.Physics
import com.zygne.confetti.engine.util.FpsCounter

class ExplosionSurface(context: Context?, attributeSet: AttributeSet?) :
    SurfaceView(context, attributeSet), SurfaceHolder.Callback, Runnable {
    private var callback: Callback? = null
    private var surfaceHolder: SurfaceHolder?
    private var thread: Thread? = null
    private var running = false
    private var screenWidth = 0
    private var screenHeight = 0
    private var explosion: BaseExplosion? = null
    private var particles: Int
    private val fpsCounter = FpsCounter()
    private var timeToNotify = 0

    init {
        isFocusable = true
        surfaceHolder = null
        surfaceHolder = this.holder
        surfaceHolder!!.addCallback(this)
        setZOrderOnTop(false)
        setBackgroundColor(Color.TRANSPARENT)
        particles = DEFAULT_PARTICLE_NUMBER
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) { // Create the child thread when SurfaceView is created.
        thread = Thread(this)
        thread!!.start()
        running = true
        screenHeight = height
        screenWidth = width
        explosion = Explosion(
            (screenWidth / 2).toFloat(),
            (screenHeight / 2).toFloat(),
            particles,
            BaseParticle.DEFAULT_MAX_SPEED
        )
        explosion!!.physics = Physics(0f, 0f, 1f)
        Log.d(TAG, explosion.toString())
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {}
    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
        running = false
    }

    override fun run() {
        while (running) {
            var canvas: Canvas? = null
            try {
                canvas = surfaceHolder!!.lockCanvas()
                canvas.drawColor(0, PorterDuff.Mode.CLEAR)
                explosion!!.update(0f)
                explosion!!.render(canvas)
            } catch (e: Exception) {
            } finally {
                if (canvas != null) {
                    surfaceHolder!!.unlockCanvasAndPost(canvas)
                }
            }
            fpsCounter.countFrames()
            timeToNotify++
            if (timeToNotify > 200) {
                if (callback != null) {
                    callback!!.onFpsUpdate(fpsCounter.fps)
                }
            }
        }
    }

    fun setCallback(callback: Callback?) {
        this.callback = callback
    }

    fun resetExplosion(particles: Int, type: Int, speed: Int) {
        this.particles = particles
        explosion = if (type == 1) {
            Explosion((screenWidth / 2).toFloat(), (screenHeight / 2).toFloat(), particles, speed)
        } else {
            Emitter((screenWidth / 2).toFloat(), (screenHeight / 2).toFloat(), particles, speed)
        }
        explosion!!.physics = Physics(0f, 0f, 1f)
    }

    fun updateWind(wind: Float) {
        explosion!!.physics.wind = wind
    }

    fun updateGravity(gravity: Float) {
        explosion!!.physics.gravity = gravity
    }

    interface Callback {
        fun onFpsUpdate(fps: Int)
    }

    companion object {
        const val TAG = "ExplosionSurface"
        const val DEFAULT_PARTICLE_NUMBER = 64
    }
}