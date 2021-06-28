package com.example.jetpackdemo.view.bean;

import java.util.Random;

/**
 * 颗粒类
 */
public class Particle {

    public float cx; //center x of circle
    public float cy; //center y of circle
    public float radius;

    public int color;
    public float alpha;

    public static int particleCount = 10;

    Random random = new Random();

    public Particle(float cx, float cy, float radius, int color) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.color = color;
    }

    public void broken(float factor, int width, int height) {
        cx = cx + factor * random.nextInt(width) * (random.nextFloat() - 0.5f);
        cy = cy + factor * random.nextInt(height / 2);

        radius = radius - factor * random.nextInt(2);

        alpha = (1f - factor) * (1 + random.nextFloat());
    }

}
