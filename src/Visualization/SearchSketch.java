package Visualization;

import Board.Board;
import Board.Tile;
import Search.Board.*;
import processing.core.PApplet;
import processing.event.MouseEvent;

import java.awt.*;

public class SearchSketch extends PApplet {
    Board board;
    DFSAlgorithm cleverDFS;
    BFSAlgorithm cleverBFS;

    UCSAlgorithm ucsAlgorithm;

    GreedyBFSAlgorithm greedyBFS;

    Visualizer visualizer;

    boolean run = false;

    double drawingSpeed = 0.05f;

    @Override
    public void settings() {
        size(700,700);
        Color[] colors = {Color.BLUE, Color.RED,Color.GREEN};
        board = new Board(10,this);
        board.initializePerlinGreyBoard();
        int refreshRate = 1;
        cleverDFS = new DFSAlgorithm(refreshRate,board.getTile(0,0),board);
        cleverBFS = new BFSAlgorithm(refreshRate,board.getTile(0,0),board);
        ucsAlgorithm = new UCSAlgorithm(refreshRate,board.getTile(0,0),board);
        greedyBFS = new GreedyBFSAlgorithm(refreshRate,board.getTile(0,0),board);
    }

    @Override
    public void setup() {
        frameRate(Visualizer.FRAME_RATE);
    }

    @Override
    public void draw() {
        //background(0);
        board.draw();
        if(run && (visualizer !=null || cleverBFS != null || cleverDFS != null)) {
            //cleverDFS.visualize();
            //visualizer.visualize();
            greedyBFS.visualize();
        }
    }

    @Override
    public void mousePressed() {
        //frameRate(Visualizer.FRAME_RATE);
        Tile tile = board.getCurrentPositionInMap(mouseX,mouseY);
        cleverBFS.setProblem(tile,board);
        cleverDFS.setProblem(tile,board);
        ucsAlgorithm.setProblem(tile,board);
        greedyBFS.setProblem(tile,board);
        //visualizer = new StackVisualizer(0.005f, recurDFS.complete(new HashSet<>(),new Node<>(tile,null,null),board,1000));
        //visualizer = naiveBPA.solution();
    }

    @Override
    public void keyPressed() {
        if(keyCode == 32)
            run = !run;
    }

    @Override
    public void mouseWheel(MouseEvent event) {
        double old_value = drawingSpeed;
        drawingSpeed = (event.getCount() > 0)?(drawingSpeed + 0.005d):(drawingSpeed - 0.005d);
        if(drawingSpeed < 0.0001 || drawingSpeed > 0.3)
            drawingSpeed = old_value;
        cleverBFS.changeVisualizationSpeed(drawingSpeed);
        cleverDFS.changeVisualizationSpeed(drawingSpeed);
        println("DrawingSpeed: " + drawingSpeed);
    }

    public static void main(String[] args){
        String[] processingArgs = {"MySketch"};
        SearchSketch sketch = new SearchSketch();
        PApplet.runSketch(processingArgs, sketch);
    }
}
