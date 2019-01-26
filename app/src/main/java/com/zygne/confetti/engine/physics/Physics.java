package com.zygne.confetti.engine.physics;

import com.zygne.confetti.engine.components.DynamicObject2d;
import com.zygne.confetti.engine.particles.BaseParticle;

public class Physics {

    private float windSpeed;
    private int windDirection;
    private float hForce;
    private float gravity;

    public Physics(){
        this.windSpeed = 1;
        this.windDirection = 1;
        this.gravity = 1;
        hForce = (windSpeed * windDirection);
    }

    public Physics(float windSpeed, int windDirection, float gravity) {
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.gravity = gravity;
        hForce = (windSpeed * windDirection);
    }

    public void update(DynamicObject2d object2d){

        object2d.getVelocity().add(hForce, 0);
    }
}
