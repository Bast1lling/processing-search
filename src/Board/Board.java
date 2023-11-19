package Board;

import Basics.Drawable;
import Search.Action;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class Board implements Drawable {
    private final PVector position;

    private final Tile[][] board;
    private final int width;
    private final int height;

    private float step_size = 0.05f;

    private final float tile_scale;
    private final PApplet sketch;

    public Board(float tile_scale, PApplet sketch){
        this.tile_scale = tile_scale;
        this.sketch = sketch;
        this.width = (int) (sketch.width/tile_scale);
        this.height = (int) (sketch.height/tile_scale);
        board = new Tile[height][width];
        position = new PVector();
        initializeTiles();
        initializeSimplePerlinColorBoard();
    }

    protected Board(float tile_scale, PApplet sketch, PVector topLeft, PVector bottomRight){
        this.tile_scale = tile_scale;
        this.sketch = sketch;
        this.position = topLeft.copy();
        PVector boardScales = PVector.sub(bottomRight,topLeft);
        this.width = (int) (boardScales.x/tile_scale);
        this.height = (int) (boardScales.y/tile_scale);
        board = new Tile[height][width];
        initializeTiles();
        initializeSimplePerlinColorBoard();
    }

    public void move(Action<Void,PVector> action){
        this.position.add(action.act(null).mult(tile_scale));
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

        this.step_size = 0.06f;

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

    public void initializePerlinGreyBoard(){
        this.step_size = 0.085f;

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                float perlin = sketch.noise(i*step_size,j*step_size);
                perlin = (perlin <= 0.17)?0:perlin;
                Color c = new Color(perlin,perlin,perlin);
                board[i][j].setColor(c);
            }
        }
    }

    public void initializeSimplePerlinColorBoard(){

        this.step_size = 0.035f;
        float b1 = 0.33f;
        float b2 = 0.66f;

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                float perlin = sketch.noise(i*step_size,j*step_size);
                Color c;
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

    private void initializeTiles(){
        for(int i = 0; i < this.height; i++){
            Tile[] row = new Tile[this.width];
            for(int j = 0; j < this.width; j++){
                row[j] = new Tile(this.position.x + j*this.tile_scale, this.position.y + i*this.tile_scale, this.tile_scale, this.sketch);
            }
            board[i] = row;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(){
        for (Tile[] row:
             board) {
            for (Tile t:
                 row) {
                t.draw();
            }
        }
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
        float x = mouseX - position.x;
        float y = mouseY - position.y;
        if(x >= 0 && y >= 0)
            return board[(int) (y/tile_scale)][(int) (x/tile_scale)];
        else return null;
    }

    public int getX(Tile t){
        float x = t.getX() - position.x;
        return (int) (x/tile_scale);
    }

    public int getY(Tile t){
        float y = t.getY() - position.y;
        return (int) (y/tile_scale);
    }
}
