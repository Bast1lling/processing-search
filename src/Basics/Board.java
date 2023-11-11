package Basics;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Board implements Drawable{
    private PVector top_left_corner;
    private final Tile[][] board;

    public Set<Tile> marked;
    private final int width;
    private final int height;

    private final int tile_width;
    private final int tile_height;

    public static final Color[] setColors = {Color.RED,Color.GREEN,Color.CYAN,Color.ORANGE};

    int setColorIndex = 0;

    private PApplet sketch;

    public Board(int width, int height, PApplet sketch, Color[] colors){
        this.sketch = sketch;
        tile_width = sketch.width/width;
        tile_height = sketch.height/height;
        board = new Tile[height][width];
        this.width = width;
        this.height = height;
        marked = new HashSet<>();
        top_left_corner = new PVector();
        for(int i = 0; i < height; i++){
            Tile[] row = new Tile[width];
            for(int j = 0; j < width; j++){
                int random = (int) (colors.length*Math.random());
                Color c = colors[random];
                Tile t = new Tile(j*tile_width, i*tile_height, tile_width, tile_height, c, sketch);
                row[j] = t;
            }
            board[i] = row;
        }
        int random_x = (int) (Math.random() * width);
        int random_y = (int) (Math.random() * height);
        board[random_y][random_x].setColor(Color.BLACK);
    }

    //distributes the given colors randomly over the board and chooses a random goal tile
    public void initializeSimpleBoard(Color[] colors){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int random = (int) (colors.length*Math.random());
                Color c = colors[random];
                board[i][j].setColor(c);
            }
        }
        int random_x = (int) (Math.random() * width);
        int random_y = (int) (Math.random() * height);
        board[random_y][random_x].setColor(Color.BLACK);
    }

    //distributes all colors randomly over the board and chooses a random goal tile
    public void initializeAllColorBoard(){
        int max = 200;
        int min = 150;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int randomR = (int) (255*Math.random());
                int randomG = (int) (255*Math.random());
                int randomB = (int) (255*Math.random());
                while ((randomR > max && randomG > max && randomB > max)
                        || (randomR < min && randomG < min && randomB < min)){
                    randomR = (int) (255*Math.random());
                    randomG = (int) (255*Math.random());
                    randomB = (int) (255*Math.random());
                }
                Color c = new Color(randomR,randomG,randomB);
                board[i][j].setColor(c);
            }
        }
        int random_x = (int) (Math.random() * width);
        int random_y = (int) (Math.random() * height);
        board[random_y][random_x].setColor(Color.BLACK);
    }

    //distributes all colors randomly over the board and chooses a random goal tile
    public void initializeDistinctColorBoard(){
        int minDist = 150;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int randomR = (int) (255*Math.random());
                int randomG = (int) (255*Math.random());
                int randomB = (int) (255*Math.random());
                int dist = Math.max(Math.max(randomR,randomG),randomB) - Math.min(Math.min(randomR,randomG),randomB);
                while (dist < minDist){
                    randomR = (int) (255*Math.random());
                    randomG = (int) (255*Math.random());
                    randomB = (int) (255*Math.random());
                    dist = Math.max(Math.max(randomR,randomG),randomB) - Math.min(Math.min(randomR,randomG),randomB);
                }
                Color c = new Color(randomR,randomG,randomB);
                board[i][j].setColor(c);
            }
        }
        int random_x = (int) (Math.random() * width);
        int random_y = (int) (Math.random() * height);
        board[random_y][random_x].setColor(Color.BLACK);
    }

    //uses perlin noise to smoothly distribute colors
    public void initializePerlinColorBoard(){
        float yR = (float) (height*Math.random());
        float yG = (float) (yR + height + height*Math.random());
        float yB = (float) (yR - height - height*Math.random());

        float x = (float) (width*Math.random());

        float step_size = 0.06f;

        int max = 255;
        int min = 0;

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                float perlinX = x + j*step_size;
                int randomR = (int) ((max-min)* sketch.noise(perlinX, yR + i*step_size));
                int randomG = (int) ((max-min)*sketch.noise(perlinX, yG + i*step_size));
                int randomB = (int) ((max-min)*sketch.noise(perlinX, yB + i*step_size));
                Color c = new Color(min+randomR,min+randomG,min+randomB);
                board[i][j].setColor(c);
            }
        }
        int random_x = (int) (Math.random() * width);
        int random_y = (int) (Math.random() * height);
        board[random_y][random_x].setColor(Color.BLACK);
    }

    public void initializeSimplePerlinColorBoard(){

        float step_size = 0.06f;
        float b1 = 0.33f;
        float b2 = 0.66f;

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                float perlin = sketch.noise(i*step_size,j*step_size);
                Color c = Color.BLACK;
                if(perlin < b1)
                    c = Color.RED;
                else if (perlin < b2)
                    c = Color.GREEN;
                else
                    c = Color.BLUE;
                board[i][j].setColor(c);
            }
        }
        int random_x = (int) (Math.random() * width);
        int random_y = (int) (Math.random() * height);
        board[random_y][random_x].setColor(Color.BLACK);
    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return null;
        else return board[y][x];
    }

    public Tile[][] getBoard() {
        return board;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTile_width() {
        return tile_width;
    }

    public int getTile_height() {
        return tile_height;
    }

    public void draw(){
        for (Tile[] row:
             board) {
            for (Tile t:
                 row) {
                if(!t.isMark())
                    t.draw();
            }
        }
        for(Tile t : marked){
            t.draw();
        }
    }
    public void select(Tile t){
        marked.add(t);
    }

    public void deselect(Tile t){
        marked.remove(t);
    }

    public void clearMarked(){
        for(Tile t : marked){
            t.setMark(false);
        }
        marked.clear();
    }

    public void print(){
        for (Tile[] row: board) {
            for (Tile t: row) {
                System.out.println(t.toString() + ", ");
            }
            System.out.println("\n");
        }
    }

    public Tile getCurrentPositionInMap(int mouseX, int mouseY) {
        float x = mouseX - top_left_corner.x;
        float y = mouseY - top_left_corner.y;
        if(x >= 0 && y >= 0)
            return board[(int) (y/tile_height)][(int) (x/tile_width)];
        else return null;
    }

    public int getX(Tile t){
        float x = t.getX() - top_left_corner.x;
        return (int) (x/tile_width);
    }

    public int getY(Tile t){
        float y = t.getY() - top_left_corner.y;
        return (int) (y/tile_height);
    }
}
