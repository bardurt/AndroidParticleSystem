package com.zygne.confetti

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.zygne.confetti.engine.view.ExplosionSurface

class MainActivity : AppCompatActivity() {
    private var explosionSurface: ExplosionSurface? = null
    private var sbWind: SeekBar? = null
    private var sbGravity: SeekBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Create the SurfaceViewThread object.
        explosionSurface = ExplosionSurface(applicationContext, 256)
        // Get text drawing LinearLayout canvas.
        val container = findViewById<LinearLayout>(R.id.explosion_container)
        // Add surfaceview object to the LinearLayout object.
        container.addView(explosionSurface)
        sbWind = findViewById(R.id.sb_wind)
        sbWind!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                setWind(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        sbGravity = findViewById(R.id.sb_gravity)
        sbGravity!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                setGravity(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    private fun setGravity(pGravity: Float) {
        var gravity = pGravity
        gravity = gravity - 50
        gravity = gravity * -1
        gravity = gravity / 200
        explosionSurface!!.updateGravity(gravity)
    }

    private fun setWind(pWind: Float) {
        var wind = pWind
        wind = wind - 50
        wind = wind * -1
        wind = wind / 200
        explosionSurface!!.updateWind(wind)
    }
}