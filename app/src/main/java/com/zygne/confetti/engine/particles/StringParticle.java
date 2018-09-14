package com.zygne.confetti.engine.particles;

import android.graphics.Canvas;

/**
 * Created by Bardur Thomsen on 9/13/18.
 */
public class StringParticle extends BaseParticle{

    public StringParticle(float x, float y) {
        super(x, y);

        paint.setStrokeWidth(width / 2);
    }

    @Override
    public void render(Canvas canvas) {

        if(isAlive()) {
            canvas.drawLine(x, y, x + width, y + height, paint);
            canvas.drawLine(x , y + height, x + width, y, paint);
        }
    }
}
