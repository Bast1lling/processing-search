package Search.Board;

import Board.*;
import Basics.Drawable;
import Search.Node;
import processing.core.PVector;

import java.awt.*;
import java.util.*;

public class UCSAlgorithm extends BoardPathAlgorithm {
    public UCSAlgorithm(int refreshRate, Tile start, Board board) {
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
        frontier = new PriorityQueue<>((n1,n2) -> {
            if(n1.getParent() == null || n2.getParent() == null)
                return Integer.MAX_VALUE;
            //cost to go to n1 from parent
            float c1 = problem.getCost(n1.getParent().getState(), n1.getState(), null);
            float c2 = problem.getCost(n2.getParent().getState(), n2.getState(), null);
            //the smaller value has higher priority
            return Float.compare(c1,c2);
        });
        frontier.add(new Node<>(start,null,null));
        explored = new HashSet<>();
        explored_tiles = new HashSet<>();
        explored_tiles.add(start);
        goal = null;
    }
}
