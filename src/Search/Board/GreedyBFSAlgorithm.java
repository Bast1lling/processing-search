package Search.Board;

import Basics.Board;
import Basics.Drawable;
import Basics.Tile;
import Search.Node;
import processing.core.PVector;

import java.awt.*;
import java.util.*;

public class GreedyBFSAlgorithm extends InformedBoardPathAlgorithm{

    private PriorityQueue<Node<Tile,Void, PVector>> frontier;
    private Set<Tile> explored_tiles;

    public GreedyBFSAlgorithm(int refreshRate, Tile start, Board board) {
        super(refreshRate);
        setProblem(start, board);
    }

    public void setProblem(Tile start, Board board) {
        resetDrawables();
        this.problem = new BoardPathProblem(start, board);

        //set heuristic to dist from Color Red
        f = (s1,s2,action) ->{
            if(s2 == null)
                return Float.MAX_VALUE;
            Color c1 = Color.BLACK;
            PVector p1 = new PVector(c1.getRed(),c1.getGreen(),c1.getBlue());
            Color c2 = s2.getColor();
            PVector p2 = new PVector(c2.getRed(),c2.getGreen(),c2.getBlue());
            return p1.dist(p2);
        };

        frontier = new PriorityQueue<>((n1, n2) -> {
            if(n1.getParent() == null || n2.getParent() == null)
                return Integer.MAX_VALUE;
            //cost to go to n1 from parent
            float h1 = f.getValue(n1.getParent().getState(), n1.getState(), null);
            float h2 = f.getValue(n2.getParent().getState(), n2.getState(), null);
            //the smaller value has higher priority
            return Float.compare(h1,h2);
        });
        frontier.add(new Node<>(start,null,null));
        explored = new HashSet<>();
        explored_tiles = new HashSet<>();
        explored_tiles.add(start);
        goal = null;
    }

    @Override
    public Collection<Drawable> next() {
        //if no options left, give up OR if goal is already reached, there is no step to take
        if(frontier.isEmpty() || goal != null)
            return null;

        //make sure the option is inside the board
        Node<Tile,Void, PVector> node = frontier.poll();
        while (node.getState() == null){
            if(frontier.isEmpty())
                return null;
            node = frontier.poll();
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
}
