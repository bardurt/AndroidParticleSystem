package com.zygne.confetti.engine.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.zygne.confetti.engine.explosions.BaseExplosion;
import com.zygne.confetti.engine.explosions.Emitter;
import com.zygne.confetti.engine.explosions.Explosion;
import com.zygne.confetti.engine.physics.Physics;
import com.zygne.confetti.engine.util.FpsCounter;

/**
 * Created by Bardur Thomsen on 9/17/18.
 */
public class ExplosionSurface extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    public static final String TAG = ExplosionSurface.class.getSimpleName();

    public static final int DEFALUT_PARTICLE_NUMBER = 64;

    public Callback callback;
    private SurfaceHolder surfaceHolder;
    private Thread thread = null;
    private boolean running = false;
    private int screenWidth = 0;
    private int screenHeight = 0;
    private BaseExplosion explosion;
    private int particles;
    private FpsCounter fpsCounter = new FpsCounter();
    private int timeToNotify;


    public ExplosionSurface(Context context) {
        super(context);

        setFocusable(true);

        // Get SurfaceHolder object.
        surfaceHolder = null;
        surfaceHolder = this.getHolder();
        // Add current object as the callback listener.
        surfaceHolder.addCallback(this);

        // Set the SurfaceView object at the top of View object.
        setZOrderOnTop(true);

        setBackgroundColor(Color.TRANSPARENT);

        particles = DEFALUT_PARTICLE_NUMBER;

    }

    public ExplosionSurface(Context context, int numberOfParticles) {
        super(context);

        setFocusable(true);

        // Get SurfaceHolder object.
        surfaceHolder = null;
        surfaceHolder = this.getHolder();
        // Add current object as the callback listener.
        surfaceHolder.addCallback(this);

        // Set the SurfaceView object at the top of View object.
        setZOrderOnTop(true);

        setBackgroundColor(Color.TRANSPARENT);

        particles = numberOfParticles;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        // Create the child thread when SurfaceView is created.
        thread = new Thread(this);
        // Start to run the child thread.
        thread.start();
        // Set thread running flag to true.
        running = true;

        // Get screen width and height.
        screenHeight = getHeight();
        screenWidth = getWidth();

        explosion = new Explosion(screenWidth / 2, screenHeight / 2, particles);
        explosion.setPhysics(new Physics(0f, 0f, 1f));

        Log.d(TAG, explosion.toString());
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // Set thread running flag to false when Surface is destroyed.
        // Then the thread will jump out the while loop and complete.
        running = false;
    }


    @Override
    public void run() {
        while (running) {

            Canvas canvas= null;
            try {
                // Get Canvas from Holder and lock it.
                canvas = this.surfaceHolder.lockCanvas();

                canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
                explosion.update(0);
                explosion.render(canvas);

            }catch(Exception e)  {
                // Do nothing.
            } finally {
                if(canvas!= null)  {
                    // Unlock Canvas.
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }

            fpsCounter.countFrames();
            timeToNotify++;

            if(timeToNotify > 200){
                if(callback != null){
                    callback.onFpsUpdate(fpsCounter.getFps());
                }
            }
        }
    }

    public void setCallback(Callback callback){
        this.callback = callback;
    }

    public void resetExplosion(int particles){
        this.particles = particles;
        explosion = new Explosion(screenWidth / 2, screenHeight / 2, particles);
        explosion.setPhysics(new Physics(0f, 0f, 1f));
    }

    public void resetExplosion(int particles, int type){
        this.particles = particles;

        if(type == 1) {
            explosion = new Explosion(screenWidth / 2, screenHeight / 2, particles);
        } else {
            explosion = new Emitter(screenWidth / 2, screenHeight / 2, particles);
        }

        explosion.setPhysics(new Physics(0f, 0f, 1f));
    }

    public void updateWind(float wind){
        explosion.getPhysics().setWind(wind);
    }

    public void updateGravity(float gravity){
        explosion.getPhysics().setGravity(gravity);
    }

    public interface Callback{
        void onFpsUpdate(int fps);
    }

}
