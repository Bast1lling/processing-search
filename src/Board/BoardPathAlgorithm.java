package Board;

import Basics.Drawable;
import Search.Algorithm;
import Search.Node;
import Visualization.StackVisualizer;
import Visualization.Visualizer;
import processing.core.PVector;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BoardPathAlgorithm extends Algorithm<Tile,Void,PVector> {

    protected BoardPathProblem problem;
    protected Set<Tile> explored;
    protected Set<Tile> explored_tiles;

    protected Collection<Node<Tile,Void, PVector>> frontier;

    protected Node<Tile,Void, PVector> goal;

    protected BoardPathAlgorithm(int refreshRate) {
        super(refreshRate);
    }

    public Node<Tile,Void, PVector> getGoal() {
        return goal;
    }

    protected abstract Node<Tile,Void, PVector> getNext();

    protected abstract void setProblem(Tile start, Board board);

    @Override
    public Collection<Drawable> next() {
        //if no options left, give up OR if goal is already reached, there is no step to take
        if(frontier.isEmpty() || goal != null)
            return null;

        //make sure the option is inside the board
        Node<Tile,Void, PVector> node = getNext();
        while (node.getState() == null){
            if(frontier.isEmpty())
                return null;
            node = getNext();
        }

        //explore option
        explored.add(node.getState());

        //create visualization of current node
        Collection<Drawable> drawables = new ArrayList<>();
        Node<Tile,Void, PVector> finalNode = node;
        drawables.add(() -> finalNode.getState().draw(Color.WHITE));

        //if option is good enough, set goal and return
        if(problem.reached(node.getState())) {
            goal = node;
            return drawables;
        }

        //else select frontier by iterating over children
        for(Node<Tile,Void, PVector> child : node.getChildren(problem)){
            //select existing child, which has not yet been explored/ set out to be explored
            if(child != null && !explored.contains(child.getState()) && !frontier.contains(child) &&!explored_tiles.contains(child.getState())) {
                frontier.add(child);
                explored_tiles.add(child.getState());
                if(child.getState() != null)
                    drawables.add(() -> child.getState().draw(Color.GRAY));
            }
        }
        return drawables;
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
