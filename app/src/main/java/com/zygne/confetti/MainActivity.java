package com.zygne.confetti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zygne.confetti.engine.view.ExplosionSurface;

public class MainActivity extends AppCompatActivity implements
    ExplosionSurface.Callback{

    private ExplosionSurface explosionSurface;

    private SeekBar sbWind;
    private SeekBar sbGravity;
    private SeekBar sbParticles;
    private TextView tvParticleAmount;
    private TextView tvFps;
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
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbGravity = findViewById(R.id.sb_gravity);
        sbGravity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setGravity(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sbParticles = findViewById(R.id.sb_particles);
        sbParticles.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateParticles(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tvParticleAmount = findViewById(R.id.tv_particle_amount);
        tvFps = findViewById(R.id.tv_fps);

    }

    private void setGravity(float gravity){
        gravity = gravity - 50;
        gravity = gravity * -1;
        gravity = gravity / 200;
        explosionSurface.updateGravity(gravity);
    }

    private void setWind(float wind){
        wind = wind - 50;
        wind = wind * -1;
        wind = wind / 200;
        explosionSurface.updateWind(wind);
    }

    private void updateParticles(int amount){
        explosionSurface.resetExplosion(amount);
        tvParticleAmount.setText(""+amount);
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
