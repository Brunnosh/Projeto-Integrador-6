package br.puc.pi6.client.physics;

public class Rect {
    public float x, y, w, h;

    public Rect(float x, float y, float w, float h) {
        this.x = x; this.y = y; this.w = w; this.h = h;
    }
    public float left()   { return x; }
    public float right()  { return x + w; }
    public float bottom() { return y; }
    public float top()    { return y + h; }

    public void setLeft(float nx)   { this.x = nx; }
    public void setRight(float nx)  { this.x = nx - w; }
    public void setBottom(float ny) { this.y = ny; }
    public void setTop(float ny)    { this.y = ny - h; }
}
