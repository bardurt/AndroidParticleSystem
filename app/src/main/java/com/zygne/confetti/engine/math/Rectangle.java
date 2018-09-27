package com.zygne.confetti.engine.math;

/**
 * Created by Bardur Thomsen on 9/27/18.
 */
public class Rectangle {

    private final Vector2 lowerLeft;
    private float width;
    private float height;

    public Rectangle(float x, float y, float width, float height) {
        this.lowerLeft = new Vector2(x,y);
        this.width = width;
        this.height = height;
    }

    public void set(float x, float y, float width, float height) {
        this.lowerLeft.set(x, y);
        this.width = width;
        this.height = height;
    }

    public boolean overlapRectangles(Rectangle r1, Rectangle r2) {
        if(r1.lowerLeft.getX() < r2.lowerLeft.getX() + r2.width &&
                r1.lowerLeft.getX() + r1.width > r2.lowerLeft.getX() &&
                r1.lowerLeft.getY() < r2.lowerLeft.getY() + r2.height &&
                r1.lowerLeft.getY() + r1.height > r2.lowerLeft.getY()) {
            return true;
        } else {
            return false;
        }
    }

    public void multiply(float scaler){
        this.width *= scaler;
        this.height *= scaler;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
