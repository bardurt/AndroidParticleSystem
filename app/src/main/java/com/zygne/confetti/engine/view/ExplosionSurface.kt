package com.zygne.confetti.engine.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.zygne.confetti.engine.explosions.BaseExplosion
import com.zygne.confetti.engine.explosions.Emitter
import com.zygne.confetti.engine.physics.Physics
import com.zygne.confetti.engine.view.ExplosionSurface

/**
 * Created by Bardur Thomsen on 9/17/18.
 */
class ExplosionSurface : SurfaceView, SurfaceHolder.Callback, Runnable {
    private var surfaceHolder: SurfaceHolder?
    private var thread: Thread? = null
    private var running = false
    private var screenWidth = 0
    private var screenHeight = 0
    private var explosion: BaseExplosion? = null
    private val particles: Int

    constructor(context: Context?) : super(context) {
        isFocusable = true
        // Get SurfaceHolder object.
        surfaceHolder = null
        surfaceHolder = this.holder
        // Add current object as the callback listener.
        surfaceHolder!!.addCallback(this)
        // Set the SurfaceView object at the top of View object.
        setZOrderOnTop(true)
        setBackgroundColor(Color.TRANSPARENT)
        particles = DEFALUT_PARTICLE_NUMBER
    }

    constructor(context: Context?, numberOfParticles: Int) : super(context) {
        isFocusable = true
        // Get SurfaceHolder object.
        surfaceHolder = null
        surfaceHolder = this.holder
        // Add current object as the callback listener.
        surfaceHolder!!.addCallback(this)
        // Set the SurfaceView object at the top of View object.
        setZOrderOnTop(true)
        setBackgroundColor(Color.TRANSPARENT)
        particles = numberOfParticles
    }

    override fun surfaceCreated(surfaceHolder: SurfaceHolder) { // Create the child thread when SurfaceView is created.
        thread = Thread(this)
        // Start to run the child thread.
        thread!!.start()
        // Set thread running flag to true.
        running = true
        // Get screen width and height.
        screenHeight = height
        screenWidth = width
        explosion = Emitter((screenWidth / 2).toFloat(), (screenHeight / 2).toFloat(), particles)
        explosion!!.physics = Physics(0f, 0f, 1f)
        Log.d(TAG, explosion.toString())
    }

    override fun surfaceChanged(surfaceHolder: SurfaceHolder, i: Int, i1: Int, i2: Int) {}
    override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) { // Set thread running flag to false when Surface is destroyed.
// Then the thread will jump out the while loop and complete.
        running = false
    }

    override fun run() {
        while (running) {
            var canvas: Canvas? = null
            try { // Get Canvas from Holder and lock it.
                canvas = surfaceHolder!!.lockCanvas()
                canvas.drawColor(0, PorterDuff.Mode.CLEAR)
                explosion!!.update(0f)
                explosion!!.render(canvas)
            } catch (e: Exception) { // Do nothing.
            } finally {
                if (canvas != null) { // Unlock Canvas.
                    surfaceHolder!!.unlockCanvasAndPost(canvas)
                }
            }
        }
    }

    fun updateWind(wind: Float) {
        explosion!!.physics.wind = wind
    }

    fun updateGravity(gravity: Float) {
        explosion!!.physics.gravity = gravity
    }

    companion object {
        val TAG = ExplosionSurface::class.java.simpleName
        const val DEFALUT_PARTICLE_NUMBER = 64
    }
}