package com.zygne.confetti.engine.particles;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.zygne.confetti.engine.components.DynamicObject2d;
import com.zygne.confetti.engine.math.Rectangle;
import com.zygne.confetti.engine.math.Vector2;
import com.zygne.confetti.engine.util.Randomizer;


/**
 * TODO define class.
 *
 * @author Bardur Thomsen
 * @version 1.0 04/09/2018.
 */

public abstract class BaseParticle extends DynamicObject2d {

    public static final int STATE_ALIVE = 0;        // particle is alive
    public static final int STATE_DEAD = 1;         // particle is dead

    public static final int DEFAULT_LIFETIME = 80;  // default lifetime for a particle
    public static final int MAX_DIMENSION = 8;      // the maximum width or height
    public static final int MAX_SPEED = 8;          // maximum speed (per update)
    public static final float DEFAULT_EXPANSION = 1.01f;

    protected Paint paint;                          //
    protected int state;                            // particle is alive or dead

    private int age;                                // current age of the particle
    private int lifetime;                           // particle dies when it reaches this value
    private float expansion;                        // rate of expansion
    private int alpha = 255;                        // alpha value of color
    private int alphaRate;                          // rate at which alpha value decreases
    private boolean fadeAlpha = true;               // should alpha value be reduced
    private boolean shouldExpand = true;

    public BaseParticle(){}

    public BaseParticle(float x, float y){
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(x, y, 0, 0);
        this.velocity = new Vector2();
        paint = new Paint();
        paint.setColor(Color.RED);
        init(x, y);
    }

    public BaseParticle(float x, float y, float width, float height) {
        super(x, y, width, height);

        init(x, y);
    }

    private void init(float x, float y) {

        this.position.set(x, y);
        this.state = BaseParticle.STATE_ALIVE;
        int radius = Randomizer.rndInt(1, MAX_DIMENSION);
        this.bounds.set(x, y, radius, radius);
        this.lifetime = DEFAULT_LIFETIME;
        this.age = 0;

        this.velocity = new Vector2((Randomizer.rndDbl(0, MAX_SPEED * 2) - MAX_SPEED),
                (Randomizer.rndDbl(0, MAX_SPEED * 2) - MAX_SPEED));
        this.alpha = 255;
        this.alphaRate = (alpha + 5) / lifetime;
        this.expansion = DEFAULT_EXPANSION;

        // smoothing out the diagonal speed
        if (velocity.distSquared(0, 0) > MAX_SPEED * MAX_SPEED) {
            velocity.multiply(0.7f);
        }

        randomizeColor();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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

    public boolean isAlive() {
        return this.state == STATE_ALIVE;
    }

    public boolean isDead() {
        return this.state == STATE_DEAD;
    }

    public void reset(float x, float y) {
        init(x, y);
    }

    @Override
    public void update(float dt) {
        if (this.state != STATE_DEAD) {

            this.age++;
            this.position.sub(velocity);
            this.alpha -= alphaRate;
            this.bounds.multiply(expansion);

            if (this.age >= this.lifetime) {    // reached the end if its life
                this.state = STATE_DEAD;
            }

            if (alpha < 10) {
                this.state = STATE_DEAD;
            }

            paint.setAlpha(alpha);
        }
    }

    private void randomizeColor(){
        paint.setARGB(255, Randomizer.rndInt(0,255), Randomizer.rndInt(0,255), Randomizer.rndInt(0,255));
    }

    @Override
    public void render(Canvas canvas) {
        throw new RuntimeException("BaseParticle.render(Canvas canvas) should only be called by the child class!");
    }
}
