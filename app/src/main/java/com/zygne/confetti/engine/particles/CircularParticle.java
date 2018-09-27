package com.zygne.confetti.engine.particles;

import android.graphics.Canvas;

/**
 * Created by Bardur Thomsen on 9/13/18.
 */
public class CircularParticle extends BaseParticle {


    public CircularParticle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void render(Canvas canvas) {

        if(isAlive()) {
            canvas.drawCircle(position.getX(), position.getY(), bounds.getWidth(), paint);
        }
    }
}
