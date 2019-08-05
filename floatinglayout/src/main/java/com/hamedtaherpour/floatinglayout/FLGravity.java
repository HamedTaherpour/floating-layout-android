package com.hamedtaherpour.floatinglayout;

import java.io.Serializable;

public class FLGravity implements Serializable {

    private int gravity;
    private int x;
    private int y;

    public FLGravity(int gravity, int x, int y) {
        this.gravity = gravity;
        this.x = x;
        this.y = y;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getGravity() {
        return gravity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
