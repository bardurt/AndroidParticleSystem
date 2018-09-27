package com.zygne.confetti.engine.explosions;

import com.zygne.confetti.engine.components.DynamicObject2d;
import com.zygne.confetti.engine.particles.BaseParticle;

/**
 * Created by Bardur Thomsen on 9/27/18.
 */
public abstract class BaseExplosion extends DynamicObject2d {

    public static final int STATE_ALIVE = 0;  // at least 1 particle is alive
    public static final int STATE_DEAD = 1;   // all particles are dead

    protected int state;
    protected final int numberOfParticles;
    protected float gravity;                    // the gravity of the explosion (+ upward, - down)
    protected float wind;                       // speed of wind on horizontal
    protected BaseParticle[] particles;         // particles in the explosion

    public BaseExplosion(float x, float y, int particleNr) {
        super(x, y);
        this.numberOfParticles = particleNr;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": { x: " + this.position.getX() + ", y: " + this.position.getY() + ", particles: " + numberOfParticles +" }";
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

    // helper methods -------------------------
    public boolean isAlive() {
        return this.state == STATE_ALIVE;
    }

    public boolean isDead() {
        return this.state == STATE_DEAD;
    }
}
