package Board;

import Basics.Drawable;
import processing.core.PApplet;

import java.awt.*;

public class Tile implements Drawable {
    protected float x;
    protected float y;
    protected float scale;
    private Color color;
    protected PApplet sketch;

    public Tile(float x, float y, float scale, Color color, PApplet sketch) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.color = color;
        this.sketch = sketch;
    }

    public Tile(float x, float y, float scale, PApplet sketch){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.sketch = sketch;
    }

    public void draw(){
        sketch.fill(color.getRGB());
        sketch.strokeWeight(1);
        sketch.stroke(0);
        sketch.rect(x,y,scale,scale);
    }

    public void draw(Color color){
        sketch.fill(color.getRGB());
        sketch.strokeWeight(1);
        sketch.stroke(255);
        sketch.rect(x,y,scale,scale);
    }

    private Color damp(int intensity){
        int red = (color.getRed() > intensity)?(color.getRed() - intensity):0;
        int blue = (color.getBlue() > intensity)?(color.getBlue() - intensity):0;
        int green = (color.getGreen() > intensity)?(color.getGreen() - intensity):0;
        return new Color(red,green,blue);
    }
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String toString(){
        return "{x=" + x + ", y=" + y + ", =c=" + color.toString() +"}";
    }

    public boolean hasColor(Color color){
        return this.getColor().equals(color);
    }
}
