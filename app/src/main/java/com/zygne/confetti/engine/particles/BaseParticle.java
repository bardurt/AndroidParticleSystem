package com.zygne.confetti.engine.particles;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.zygne.confetti.engine.components.RendableObject;
import com.zygne.confetti.engine.components.UpdatableObject;
import com.zygne.confetti.engine.util.Randomizer;


/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 04/09/2018.
 */


public abstract class BaseParticle implements RendableObject, UpdatableObject {

    public static final int STATE_ALIVE = 0;        // particle is alive
    public static final int STATE_DEAD = 1;         // particle is dead

    public static final int DEFAULT_LIFETIME = 80;  // play with this
    public static int MAX_DIMENSION = 8;            // the maximum width or height
    public static final int MAX_SPEED = 8;          // maximum speed (per update)

    protected Paint paint;
    protected int state;                              // particle is alive or dead
    protected float width;                            // width of the particle
    protected float height;                           // height of the particle
    protected float x;                                // horizontal position
    protected float y;                                // vertical position
    private float xv;                               // horizontal velocity
    private float yv;                               // vertical velocity
    private int age;                                // current age of the particle
    private int lifetime;                           // particle dies when it reaches this value
    private int type = 0;
    private float expansion = 1.01f;                // rate of expansion
    protected int alpha = 255;
    private int alphaRate = 2;

    public BaseParticle(float x, float y) {
        init(x, y);

        paint = new Paint();
        paint.setColor(Color.RED);
    }

    public void init(float x, float y) {

        this.x = x;
        this.y = y;
        this.state = BaseParticle.STATE_ALIVE;
        this.width = Randomizer.rndInt(1, MAX_DIMENSION);
        this.height = this.width;
        this.lifetime = DEFAULT_LIFETIME;
        this.age = 0;
        this.xv = (Randomizer.rndDbl(0, MAX_SPEED * 2) - MAX_SPEED);
        this.yv = (Randomizer.rndDbl(0, MAX_SPEED * 2) - MAX_SPEED);
        this.alpha = 255;
        this.alphaRate = (alpha + 5) / lifetime;
        type = Randomizer.rndInt(0, 6);

        // smoothing out the diagonal speed
        if (xv * xv + yv * yv > MAX_SPEED * MAX_SPEED) {
            xv *= 0.7;
            yv *= 0.7;
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
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

    public float getXv() {
        return xv;
    }

    public void setXv(float xv) {
        this.xv = xv;
    }

    public float getYv() {
        return yv;
    }

    public void setYv(float yv) {
        this.yv = yv;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public void setExpansion(float expansion) {
        this.expansion = expansion;
    }

    // helper methods -------------------------
    public boolean isAlive() {
        return this.state == STATE_ALIVE;
    }

    public boolean isDead() {
        return this.state == STATE_DEAD;
    }

    public void rest(float x, float y) {
        init(x, y);
    }

    @Override
    public void update(float dt) {
        if (this.state != STATE_DEAD) {
            this.x += this.xv;
            this.y += this.yv;

            this.age++;                        // increase the age of the particle

            this.alpha -= alphaRate;

            this.width *= expansion;
            this.height *= expansion;

            if (this.age >= this.lifetime) {    // reached the end if its life
                this.state = STATE_DEAD;
            }

            if (alpha < 10) {
                this.state = STATE_DEAD;
            }

            paint.setAlpha(alpha);
        }
    }

    @Override
    public void render(Canvas canvas) {
    }
}
