package com.zygne.confetti.engine.explosions;

import android.graphics.Canvas;

import com.zygne.confetti.engine.particles.BaseParticle;
import com.zygne.confetti.engine.particles.CircularParticle;

/**
 * Created by Bardur Thomsen on 9/13/18.
 */
public class Explosion extends BaseExplosion {

    private static final String TAG = Explosion.class.getSimpleName();

    public Explosion(float x, float y, int particleNr) {
        super(x, y, particleNr);

        this.state = STATE_ALIVE;
        this.particles = new BaseParticle[particleNr];

        for (int i = 0; i < this.particles.length; i++) {

            BaseParticle p = new CircularParticle(x, y, 0, 0);

            this.particles[i] = p;
        }
    }

    @Override
    public void update(float deltaTime) {
        if (this.state != STATE_DEAD) {
            boolean isDead = true;
            for (BaseParticle particle : this.particles) {
                if (particle.isAlive()) {
                    particle.update(deltaTime);
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

        for (BaseParticle particle : this.particles) {
            particle.reset(position.getX(), position.getY());
        }

    }

    @Override
    public void render(Canvas canvas) {

        for (BaseParticle particle : this.particles) {
            particle.render(canvas);
        }
    }

    @Override
    public String toString() {
        return  "Explosion { x: " + this.position.getX() + ", y: " + this.position.getY() + ", particles: " + particles.length +" }";
    }
}