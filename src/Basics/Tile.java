package Basics;

import processing.core.PApplet;

import java.awt.*;

public class Tile implements Drawable{
    private float x;
    private float y;
    private float sX;
    private float sY;
    private Color color;
    private PApplet sketch;

    boolean mark = false;

    public Tile(float x, float y, float sX, float sY, Color color, PApplet sketch) {
        this.x = x;
        this.y = y;
        this.sX = sX;
        this.sY = sY;
        this.color = color;
        this.sketch = sketch;
    }

    public Tile(float x, float y, float sX, float sY, PApplet sketch){
        this.x = x;
        this.y = y;
        this.sX = sX;
        this.sY = sY;
        this.sketch = sketch;
    }

    public void draw(){
        if(mark){
            sketch.fill(damp(50).getRGB());
            sketch.strokeWeight((sX+sY)/32f);
            sketch.stroke(255);
        }
        else {
            sketch.fill(color.getRGB());
            sketch.strokeWeight(1);
            sketch.stroke(0);
        }
        sketch.rect(x,y,sX,sY);
    }

    public void draw(Color color){
        sketch.fill(color.getRGB());
        sketch.strokeWeight((sX+sY)/32f);
        sketch.stroke(255);
        sketch.rect(x,y,sX,sY);
    }

    private Color damp(int intensity){
        int red = (color.getRed() > intensity)?(color.getRed() - intensity):0;
        int blue = (color.getBlue() > intensity)?(color.getBlue() - intensity):0;
        int green = (color.getGreen() > intensity)?(color.getGreen() - intensity):0;
        return new Color(red,green,blue);
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
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

    public int hasColor(Color color){
        if(this.color.equals(color))
            return 0;
        else return 1;
    }
}
