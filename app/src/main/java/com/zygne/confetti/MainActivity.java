package com.zygne.confetti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zygne.confetti.engine.view.ExplosionSurface;

public class MainActivity extends AppCompatActivity implements
        ExplosionSurface.Callback {

    private ExplosionSurface explosionSurface;

    private int particles;
    private float wind;
    private float gravity;

    private SeekBar sbWind;
    private SeekBar sbGravity;
    private SeekBar sbParticles;
    private TextView tvParticleAmount;
    private TextView tvFps;
    private Button btnEmitter;
    private Button btnExplosion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the SurfaceViewThread object.
        explosionSurface = new ExplosionSurface(getApplicationContext(), 256);
        explosionSurface.setCallback(this);

        // Get text drawing LinearLayout canvas.
        LinearLayout container = findViewById(R.id.explosion_container);

        // Add surfaceview object to the LinearLayout object.
        container.addView(explosionSurface);

        sbWind = findViewById(R.id.sb_wind);
        sbWind.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                setWind(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        sbGravity = findViewById(R.id.sb_gravity);
        sbGravity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setGravity(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        sbParticles = findViewById(R.id.sb_particles);
        sbParticles.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateParticles(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        tvParticleAmount = findViewById(R.id.tv_particle_amount);
        tvFps = findViewById(R.id.tv_fps);

        btnEmitter = findViewById(R.id.btn_emitter);
        btnEmitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explosionSurface.resetExplosion(particles, 0);
            }
        });

        btnExplosion = findViewById(R.id.btn_explosion);
        btnExplosion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                explosionSurface.resetExplosion(particles, 1);
            }
        });

    }

    private void setGravity(float gravity) {
        gravity = gravity - 50;
        gravity = gravity * -1;
        gravity = gravity / 200;
        this.gravity = gravity;
        explosionSurface.updateGravity(gravity);
    }

    private void setWind(float wind) {
        wind = wind - 50;
        wind = wind * -1;
        wind = wind / 200;
        this.
                explosionSurface.updateWind(wind);
    }

    private void updateParticles(int amount) {
        this.particles = amount;
        explosionSurface.resetExplosion(amount);
        tvParticleAmount.setText("" + amount);
    }

    @Override
    public void onFpsUpdate(final int fps) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvFps.setText("" + fps);
            }
        });
    }
}
