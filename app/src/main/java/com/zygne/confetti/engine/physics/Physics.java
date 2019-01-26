package com.zygne.confetti.engine.physics;

import com.zygne.confetti.engine.components.DynamicObject2d;
import com.zygne.confetti.engine.particles.BaseParticle;

public class Physics {

    private float wind;
    private float gravity;
    private float drag;

    public Physics(){
        this.wind = 0;
        this.gravity = 0;
        this.drag = 1;
    }

    public Physics(float wind, float gravity, float drag) {
        this.wind = wind;
        this.gravity = gravity;

        if(drag >= 1){
            this.drag = drag;
        }

        this.drag = 1 / this.drag;
    }

    public void update(DynamicObject2d object2d){

        object2d.getVelocity().add(wind, 0);
        object2d.getVelocity().add(0, gravity);
        object2d.getVelocity().multiply(drag);
    }
}
