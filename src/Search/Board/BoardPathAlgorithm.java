package Search.Board;

import Basics.Board;
import Basics.Drawable;
import Basics.Tile;
import Search.Algorithm;
import Search.Node;
import Visualization.StackVisualizer;
import Visualization.Visualizer;
import processing.core.PVector;

import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BoardPathAlgorithm extends Algorithm<Tile,Void,PVector> {

    protected BoardPathProblem problem;
    protected Set<Tile> explored;

    protected Node<Tile,Void, PVector> goal;

    protected BoardPathAlgorithm(int refreshRate) {
        super(refreshRate);
    }

    public Node<Tile,Void, PVector> getGoal() {
        return goal;
    }

    @Override
    public Visualizer solution() {
        while (goal == null)
            next();

        Collection<Drawable> collection = goal.getRootPath().stream().map(tileNode -> (Drawable) (() -> tileNode.getState().draw(Color.WHITE))).collect(Collectors.toList());
        //reset goal
        this.goal = null;
        return new StackVisualizer(1,collection);
    }

}
