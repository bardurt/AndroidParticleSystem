package com.zygne.confetti

import android.support.v7.app.AppCompatActivity
import com.zygne.confetti.engine.view.ExplosionSurface
import android.widget.SeekBar
import android.widget.TextView
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.SeekBar.OnSeekBarChangeListener

class MainActivity : AppCompatActivity(), ExplosionSurface.Callback {
    private var explosionSurface: ExplosionSurface? = null
    private var particles = 0
    private var wind = 0f
    private var gravity = 0f
    private lateinit var sbWind: SeekBar
    private lateinit var sbGravity: SeekBar
    private lateinit var sbParticles: SeekBar
    private lateinit var tvParticleAmount: TextView
    private lateinit var tvFps: TextView
    private lateinit var btnEmitter: Button
    private lateinit var btnExplosion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create the SurfaceViewThread object.
        explosionSurface = ExplosionSurface(applicationContext, 256)
        explosionSurface!!.setCallback(this)

        // Get text drawing LinearLayout canvas.
        val container = findViewById<LinearLayout>(R.id.explosion_container)

        // Add surfaceview object to the LinearLayout object.
        container.addView(explosionSurface)
        sbWind = findViewById(R.id.sb_wind)
        sbWind.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                setWind(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        sbGravity = findViewById(R.id.sb_gravity)
        sbGravity.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                setGravity(progress.toFloat())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        sbParticles = findViewById(R.id.sb_particles)
        sbParticles.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                updateParticles(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        tvParticleAmount = findViewById(R.id.tv_particle_amount)
        tvFps = findViewById(R.id.tv_fps)
        btnEmitter = findViewById(R.id.btn_emitter)
        btnEmitter.setOnClickListener {
            explosionSurface!!.resetExplosion(
                particles,
                0
            )
            explosionSurface!!.updateGravity(this.gravity)
            explosionSurface!!.updateWind(this.wind)
        }
        btnExplosion = findViewById(R.id.btn_explosion)
        btnExplosion.setOnClickListener {
            explosionSurface!!.resetExplosion(
                particles,
                1
            )
            explosionSurface!!.updateGravity(this.gravity)
            explosionSurface!!.updateWind(this.wind)
        }
    }

    private fun setGravity(gravity: Float) {
        this.gravity = ((gravity - 50) * -1) / 200
        explosionSurface!!.updateGravity(this.gravity)
    }

    private fun setWind(wind: Float) {
        this.wind = ((wind - 50) * -1) / 200
        explosionSurface!!.updateWind(this.wind)
    }

    private fun updateParticles(amount: Int) {
        particles = amount
        explosionSurface!!.resetExplosion(amount)
        tvParticleAmount.text = "$amount"
    }

    override fun onFpsUpdate(fps: Int) {
        runOnUiThread { tvFps.text = "$fps" }
    }
}