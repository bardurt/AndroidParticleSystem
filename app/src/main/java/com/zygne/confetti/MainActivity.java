package com.zygne.confetti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.zygne.confetti.engine.view.ExplosionSurface;

public class MainActivity extends AppCompatActivity {

    private ExplosionSurface explosionSurface;

    private SeekBar sbWind;
    private SeekBar sbGravity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the SurfaceViewThread object.
        explosionSurface = new ExplosionSurface(getApplicationContext(), 256);

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
}
