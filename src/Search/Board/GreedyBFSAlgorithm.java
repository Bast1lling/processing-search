package Search.Board;

import Board.*;
import Search.Node;
import processing.core.PVector;

import java.awt.*;
import java.util.*;

public class GreedyBFSAlgorithm extends BoardPathAlgorithm{

    public GreedyBFSAlgorithm(int refreshRate, Tile start, Board board) {
        super(refreshRate);
        setProblem(start, board);
    }

    @Override
    protected Node<Tile, Void, PVector> getNext() {
        PriorityQueue<Node<Tile, Void, PVector>> queue = (PriorityQueue<Node<Tile, Void, PVector>>) frontier;
        return queue.poll();
    }

    public void setProblem(Tile start, Board board) {
        resetDrawables();
        this.problem = new BoardPathProblem(start, board, t -> t.hasColor(Color.BLACK));

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
}
