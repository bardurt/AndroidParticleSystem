package com.zygne.confetti

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import com.zygne.confetti.engine.view.ExplosionSurface

class MainActivity : AppCompatActivity(), ExplosionSurface.Callback {
    private var explosionSurface: ExplosionSurface? = null
    private var particles = 32
    private var wind = 0f
    private var gravity = 0f
    private var type = 1;
    private lateinit var sbWind: SeekBar
    private lateinit var sbGravity: SeekBar
    private lateinit var sbParticles: SeekBar
    private lateinit var tvParticleAmount: TextView
    private lateinit var tvFps: TextView
    private lateinit var btnEmitter: ImageView
    private lateinit var btnExplosion: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        explosionSurface = findViewById(R.id.explosion_surface);
        explosionSurface!!.setCallback(this)
        tvParticleAmount = findViewById(R.id.tv_particle_amount)
        tvFps = findViewById(R.id.tv_fps)

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
        sbParticles.progress = particles

        btnEmitter = findViewById(R.id.btn_emitter)
        btnEmitter.setOnClickListener {
            type = 0
            explosionSurface!!.resetExplosion(
                particles,
                type
            )
            explosionSurface!!.updateGravity(this.gravity)
            explosionSurface!!.updateWind(this.wind)
        }
        btnExplosion = findViewById(R.id.btn_explosion)
        btnExplosion.setOnClickListener {
            type = 1
            explosionSurface!!.resetExplosion(
                particles,
                type
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
        explosionSurface!!.resetExplosion(amount, type)
        tvParticleAmount.text = "$amount"
    }

    override fun onFpsUpdate(fps: Int) {
        runOnUiThread { tvFps.text = "$fps" }
    }
}