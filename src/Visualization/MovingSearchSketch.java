package Visualization;

import Board.Tile;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class MovingSearchSketch extends PApplet {
    /*
    MovableBoard board;
    UCSAlgorithm ucsAlgorithm;
    boolean run = true;

    @Override
    public void settings() {
        size(700,700);
        Color[] colors = {Color.BLUE, Color.RED,Color.GREEN};
        board = new MovableBoard(70,70,new PVector(),new PVector(700,700), this);
        int refreshRate = 1;
        ucsAlgorithm = new UCSAlgorithm(refreshRate,board.getTile(0,0),board);
        MovableTile tile = board.getCurrentPositionInMap(350,350);
        ucsAlgorithm.setProblem(tile,board);
    }

    @Override
    public void setup() {
        frameRate(Visualizer.FRAME_RATE);
    }

    @Override
    public void draw() {
        background(0);
        board.move(ucsAlgorithm.nextAction());
        board.draw();
    }

    @Override
    public void mousePressed() {
        //frameRate(Visualizer.FRAME_RATE);
        Tile tile = board.getCurrentPositionInMap(mouseX,mouseY);
    }

    @Override
    public void keyPressed() {
        if(keyCode == 32)
            run = !run;
    }

    public static void main(String[] args){
        String[] processingArgs = {"MySketch"};
        MovingSearchSketch sketch = new MovingSearchSketch();
        PApplet.runSketch(processingArgs, sketch);
    }

     */
}
