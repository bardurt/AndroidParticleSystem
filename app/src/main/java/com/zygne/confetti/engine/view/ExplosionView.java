package com.zygne.confetti.engine.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zygne.confetti.engine.explosions.Explosion;

/**
 * Created by Bardur Thomsen on 9/13/18.
 */
public class ExplosionView extends View {

    private boolean set = false;
    private Explosion explosion;

    public ExplosionView(Context context) {
        super(context);
    }

    public ExplosionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExplosionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!set){
            createExplosion(getWidth()/2, getHeight() /2);
        } else {
            explosion.update(1);
            explosion.render(canvas);
            invalidate();
        }
    }

    private void createExplosion(int x, int y){
        explosion = new Explosion(1000, x, y);
        this.set = true;
        invalidate();
    }

}
