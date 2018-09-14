package com.zygne.confetti.engine.explosions;

import android.graphics.Canvas;

import com.zygne.confetti.engine.components.RendableObject;
import com.zygne.confetti.engine.components.UpdatableObject;
import com.zygne.confetti.engine.particles.BaseParticle;
import com.zygne.confetti.engine.particles.CircularParticle;
import com.zygne.confetti.engine.particles.RectangularParticle;
import com.zygne.confetti.engine.particles.StringParticle;
import com.zygne.confetti.engine.util.Randomizer;

/**
 * Created by Bardur Thomsen on 9/13/18.
 */
public class Explosion implements RendableObject, UpdatableObject {

    private static final String TAG = Explosion.class.getSimpleName();

    public static final int STATE_ALIVE = 0;    // at least 1 particle is alive
    public static final int STATE_DEAD = 1;    // all particles are dead

    private BaseParticle[] particles;            // particles in the explosion
    private float x, y;                        // the explosion's origin
    private float gravity;                    // the gravity of the explosion (+ upward, - down)
    private float wind;                        // speed of wind on horizontal
    private int size;                        // number of particles
    private int state;                        // whether it's still active or not

    public Explosion(int particleNr, float x, float y) {
        this.state = STATE_ALIVE;
        this.particles = new BaseParticle[particleNr];
        this.x = x;
        this.y = y;

        for (int i = 0; i < this.particles.length; i++) {

            BaseParticle p = new CircularParticle(x, y);

            this.particles[i] = p;
        }

        this.size = particleNr;
    }

    public BaseParticle[] getParticles() {
        return particles;
    }

    public void setParticles(BaseParticle[] particles) {
        this.particles = particles;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getWind() {
        return wind;
    }

    public void setWind(float wind) {
        this.wind = wind;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    // helper methods -------------------------
    public boolean isAlive() {
        return this.state == STATE_ALIVE;
    }

    public boolean isDead() {
        return this.state == STATE_DEAD;
    }

    @Override
    public void update(float deltaTime) {
        if (this.state != STATE_DEAD) {
            boolean isDead = true;
            for (int i = 0; i < this.particles.length; i++) {
                if (this.particles[i].isAlive()) {
                    this.particles[i].update(deltaTime);
                    isDead = false;
                }
            }
            if (isDead) {
                //this.state = STATE_DEAD;
                reset();
            }

        }
    }

    private void reset() {

        for (int i = 0; i < this.particles.length; i++) {
            particles[i].reset(x, y);
        }

    }

    @Override
    public void render(Canvas canvas) {

        for (int i = 0; i < this.particles.length; i++) {
            this.particles[i].render(canvas);
        }
    }
}