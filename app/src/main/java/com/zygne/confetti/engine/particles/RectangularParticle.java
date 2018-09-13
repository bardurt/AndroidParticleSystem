package com.zygne.confetti.engine.particles;

import android.graphics.Canvas;

/**
 * Created by Bardur Thomsen on 9/13/18.
 */
public class RectangularParticle extends BaseParticle {

    public RectangularParticle(float x, float y) {
        super(x, y);
    }

    @Override
    public void render(Canvas canvas) {

        if(isAlive()) {
            canvas.drawRect(x, y,  x + width, y + height, paint);
        }
    }
}
