package Search.Board;

import Basics.Board;
import Basics.Tile;
import Search.Action;
import Search.Actor;
import Search.Node;
import Search.Problem;
import processing.core.PVector;

import java.awt.*;
import java.util.*;
import java.util.List;

public class BoardPathProblem extends Problem<Tile> {
    private Board board;

    public BoardPathProblem(Tile state, Board board) {
        super(state);
        this.board = board;
    }

    @Override
    public List<Action> getActions(Tile state) {
        int x_index = board.getX(state);
        int y_index = board.getY(state);
        Actor move_up = () -> {
            board.deselect(state);
            Tile next = board.getTile(x_index,y_index-1);
            board.select(next);
        };
        Actor move_right = () -> {
            board.deselect(state);
            Tile next = board.getTile(x_index+1,y_index);
            board.select(next);
        };
        Actor move_left = () -> {
            board.deselect(state);
            Tile next = board.getTile(x_index-1,y_index);
            board.select(next);
        };
        Actor move_down = () -> {
            board.deselect(state);
            Tile next = board.getTile(x_index,y_index+1);
            board.select(next);
        };
        List<Action> actions = new ArrayList<>();
        Action up = new Action(move_up,0);
        actions.add(up);
        Action right = new Action(move_right,1);
        actions.add(right);
        Action down = new Action(move_down,2);
        actions.add(down);
        Action left = new Action(move_left,3);
        actions.add(left);
        Collections.shuffle(actions);
        return actions;
    }

    @Override
    public Tile getResult(Tile state, Action action) {
        int x = board.getX(state);
        int y = board.getY(state);
        switch (action.getId()) {
            //UP
            case 0 -> {
                return board.getTile(x, y - 1);
            }//RIGHT
            case 1 -> {
                return board.getTile(x + 1, y);
            }//DOWN
            case 2 -> {
                return board.getTile(x, y + 1);
            }//LEFT
            default -> {
                return board.getTile(x - 1, y);
            }
        }
    }

    @Override
    public float getCost(Tile state1, Tile state2, Action action) {
        if(state1 == null || state2 == null)
            return Float.MAX_VALUE;
        Color c1 = state1.getColor();
        PVector p1 = new PVector(c1.getRed(),c1.getGreen(),c1.getBlue());
        Color c2 = state2.getColor();
        PVector p2 = new PVector(c2.getRed(),c2.getGreen(),c2.getBlue());
        return p1.dist(p2);
    }

    @Override
    public boolean reached(Tile current) {
        return current.hasColor(Color.BLACK) == 0;
    }
}
