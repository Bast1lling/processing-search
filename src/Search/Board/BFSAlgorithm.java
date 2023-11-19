package Search.Board;

import Board.*;
import Search.Node;
import processing.core.PVector;

import java.awt.*;
import java.util.*;

public class BFSAlgorithm extends BoardPathAlgorithm {

    public BFSAlgorithm(int refreshRate, Tile start, Board board) {
        super(refreshRate);
        setProblem(start, board);
    }

    public void setProblem(Tile start, Board board) {
        resetDrawables();
        this.problem = new BoardPathProblem(start, board, t -> t.hasColor(Color.BLACK));
        frontier = new LinkedList<>();
        frontier.add(new Node<>(start,null,null));
        explored = new HashSet<>();
        explored_tiles = new HashSet<>();
        explored_tiles.add(start);
        goal = null;
    }

    @Override
    protected Node<Tile, Void, PVector> getNext() {
        Deque<Node<Tile, Void, PVector>> queue = (LinkedList<Node<Tile, Void, PVector>>) frontier;
        return queue.pop();
    }
}
