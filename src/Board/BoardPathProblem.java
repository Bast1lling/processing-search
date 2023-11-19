package Board;

import Board.Board;
import Board.Tile;
import Search.Action;
import Search.Problem;
import Search.Valuable;
import processing.core.PVector;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;

public class BoardPathProblem extends Problem<Tile,Void,PVector> {
    private final Board board;

    public BoardPathProblem(Tile state, Board board, Predicate<Tile> reached) {
        super(state,reached);
        this.board = board;
        //define cost by absolute difference of two colors
        this.costCalculator = compareCostByColorDist();
    }

    @Override
    public List<Action<Void,PVector>> getActions(Tile state) {
        List<Action<Void,PVector>> actions = new ArrayList<>();
        Action<Void,PVector> up = new Action<Void,PVector>(x-> new PVector(0,-1));
        actions.add(up);
        Action<Void,PVector> right = new Action<Void,PVector>(x-> new PVector(1,0));
        actions.add(right);
        Action<Void,PVector> down = new Action<Void,PVector>(x-> new PVector(-1,0));
        actions.add(down);
        Action<Void,PVector> left = new Action<Void,PVector>(x-> new PVector(0,1));
        actions.add(left);
        Collections.shuffle(actions);
        return actions;
    }

    @Override
    public Tile getResult(Tile state, Action<Void,PVector> action) {
        int x = board.getX(state);
        int y = board.getY(state);
        PVector p = action.act(null);
        return board.getTile(x + (int)p.x, y+ (int)p.y);
    }

    public static Valuable<Float,Tile> compareCostByColorDist(){
        return (o1,o2,a) -> {
            if(o1 == null || o2 == null)
                return Float.MAX_VALUE;
            Color c1 = o1.getColor();
            PVector p1 = new PVector(c1.getRed(),c1.getGreen(),c1.getBlue());
            Color c2 = o2.getColor();
            PVector p2 = new PVector(c2.getRed(),c2.getGreen(),c2.getBlue());
            return p1.dist(p2);
        };
    }
}
