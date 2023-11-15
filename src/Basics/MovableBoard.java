package Basics;

import Search.Action;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class MovableBoard {

    private PVector center;
    private final Tile[][] board;
    private final int width;
    private final int height;

    private final float tile_width;
    private final float tile_height;

    private final float step_size = 0.03f;

    private PApplet sketch;

    public MovableBoard(int width, int height, PVector bottomLeft, PVector topRight, PApplet sketch){
        this.sketch = sketch;
        float boardWidth = topRight.x - bottomLeft.x;
        float boardHeight = bottomLeft.y - topRight.y;
        tile_width = (boardWidth/width);
        tile_height = (boardHeight/height);
        board = new Tile[height][width];
        this.width = width;
        this.height = height;
        center = bottomLeft.add(PVector.sub(topRight,bottomLeft).div(2));
        for(int i = 0; i < height; i++){
            Tile[] row = new Tile[width];
            for(int j = 0; j < width; j++){
                Tile t = new Tile(j*tile_width, i*tile_height, tile_width, tile_height, sketch);
                row[j] = t;
            }
            board[i] = row;
        }
    }

    public void draw(){
        float perlin = 0;
        Color c = new Color(0);
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                perlin = sketch.noise(center.x + i*step_size,center.y + j*step_size);
                c = new Color(0,perlin,0,0.5f);
                board[i][j].draw(c);
            }
        }
    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return null;
        else return board[y][x];
    }

    public Tile[][] getBoard() {
        return board;
    }

    public Tile getCurrentPositionInMap(int mouseX, int mouseY) {
        PVector mousePos = new PVector(mouseX,mouseY);
        PVector relativePos = PVector.sub(mousePos,center);
        return getTile((int) (relativePos.y/tile_height),(int) (relativePos.x/tile_width));
    }

}
