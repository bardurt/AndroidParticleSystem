package com.zygne.confetti.engine.math;

/**
 * Created by Bardur Thomsen on 9/27/18.
 */
public class Circle {

    private final Vector2 center;

    private float radius;

    public Circle(float x, float y, float radius) {
        this.center = new Vector2(x, y);
        this.center.set(x,y);
        this.radius = radius;
    }

    public boolean overlapCircles(Circle c1, Circle c2) {
        float distance = c1.center.distSquared(c2.center);
        float radiusSum = c1.radius + c2.radius;
        return distance <= radiusSum * radiusSum;
    }
}