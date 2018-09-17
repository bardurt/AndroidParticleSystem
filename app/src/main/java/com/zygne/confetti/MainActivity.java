package com.zygne.confetti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.zygne.confetti.engine.view.ExplosionSurface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the SurfaceViewThread object.
        final ExplosionSurface surfaceViewThread = new ExplosionSurface(getApplicationContext());

        // Get text drawing LinearLayout canvas.
        LinearLayout container = findViewById(R.id.explosion_container);

        // Add surfaceview object to the LinearLayout object.
        container.addView(surfaceViewThread);
    }
}
