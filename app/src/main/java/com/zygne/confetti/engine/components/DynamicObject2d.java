package com.zygne.confetti.engine.components;

import android.graphics.Canvas;

import com.zygne.confetti.engine.math.Rectangle;;
import com.zygne.confetti.engine.math.Vector2;

/**
 * Created by Bardur Thomsen on 9/27/18.
 */
public abstract class DynamicObject2d implements RendableObject, UpdatableObject {

    protected Vector2 position;
    protected Rectangle bounds;
    protected Vector2 velocity;

    public DynamicObject2d(){}

    public DynamicObject2d(float x, float y){
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x, y, 0, 0);
        this.velocity = new Vector2();
    }

    public DynamicObject2d(float x, float y, float width, float height){
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x, y, width, height);
        this.velocity = new Vector2();
    }

    @Override
    public void render(Canvas canvas) {

    }

    @Override
    public void update(float dt) {

    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
}
