package com.zygne.confetti.engine.explosions;

import android.graphics.Canvas;

import com.zygne.confetti.engine.particles.BaseParticle;
import com.zygne.confetti.engine.particles.CircularParticle;

/**
 * Created by Bardur Thomsen on 9/27/18.
 */

public class Emitter extends BaseExplosion {

    private static final String TAG = Emitter.class.getSimpleName();

    private final int minSize = 10;
    private int maxSize = 100;

    private int size;

    public Emitter(float x, float y, int particleNr) {
        super(x, y, particleNr);

        this.state = STATE_ALIVE;

        if(particleNr < minSize){
            particleNr = minSize;
        }

        this.particles = new BaseParticle[particleNr];

        for(int i = 0; i < minSize; i++) {
            BaseParticle p = new CircularParticle(x, y);
            this.particles[i] = p;
        }

        size = minSize;

        maxSize = particleNr;
    }

    @Override
    public void update(float deltaTime) {

        createNewParticle();

        for (int i = 0; i < size; i++) {
            if (this.particles[i].isAlive()) {
                this.physics.update(this.particles[i]);
                this.particles[i].update(deltaTime);
            } else {
                this.particles[i].reset(position.getX(), position.getY());
            }
        }
    }

    @Override
    public void render(Canvas canvas) {

        for (BaseParticle particle : this.particles) {
            particle.render(canvas);
        }
    }

    private void createNewParticle(){
        if(size < maxSize) {
            for(int i = 0; i < 5; i++) {
                if(size < maxSize) {
                    BaseParticle p = new CircularParticle(position.getX(), position.getY());
                    this.particles[size] = p;
                    size++;
                }
            }
        }
    }
}
