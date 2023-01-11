package org.example;

import processing.core.PApplet;

public class Bola {

    private PApplet sketch;

    private float x;
    private float y;
    private float tamanho;
    private float xSpeed;
    private float ySpeed;
    private int value;
    private boolean isPivot = false;

    public Bola(PApplet sketch, float x, float y, int value) {
        this.sketch = sketch;
        this.x = x;
        this.y = y;
        this.tamanho = (value * 10) + 10;
        this.value = value;
        this.xSpeed = 1;
        this.ySpeed = 1;
    }


    public void render() {
        if (!isPivot) {
            sketch.fill(0, 255, 0);
            sketch.ellipse(x, y, tamanho, tamanho);
            sketch.fill(255, 255, 255);
            sketch.textSize(18);
            sketch.text(String.valueOf(this.value), this.x - 6, this.y - tamanho - 20);
        } else {
            sketch.fill(255, 0, 0);
            sketch.ellipse(x, y, tamanho, tamanho);
            sketch.fill(255, 255, 255);
            sketch.textSize(18);
            sketch.text(String.valueOf(this.value), this.x - 6, this.y - tamanho - 20);
        }

    }

    public void updatePosition(float x) {
        float valorTotal = (x * 100) + 140;
        if (this.x > valorTotal) {
            while (this.x > valorTotal) {
                this.x -= xSpeed;
            }
        } else {
            while (this.x < valorTotal) {
                this.x += xSpeed;
            }
        }
    }

    public PApplet getSketch() {
        return sketch;
    }

    public void setSketch(PApplet sketch) {
        this.sketch = sketch;
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

    public float getTamanho() {
        return tamanho;
    }

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isPivot() {
        return isPivot;
    }

    public void setPivot(boolean pivot) {
        isPivot = pivot;
    }
}
