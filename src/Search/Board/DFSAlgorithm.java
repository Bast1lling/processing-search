package Search.Board;

import Board.*;
import Search.Node;
import processing.core.PVector;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DFSAlgorithm extends BoardPathAlgorithm {

    public DFSAlgorithm(int refreshRate, Tile start, Board board) {
        super(refreshRate);
        setProblem(start, board);
    }

    @Override
    protected Node<Tile, Void, PVector> getNext() {
        List<Node<Tile, Void, PVector>> list = (ArrayList<Node<Tile, Void, PVector>>) frontier;
        int last = list.size()-1;
        return list.get(last);
    }

    public void setProblem(Tile start, Board board) {
        resetDrawables();
        this.problem = new BoardPathProblem(start, board, t -> t.hasColor(Color.BLACK));
        frontier = new ArrayList<>();
        frontier.add(new Node<>(start,null,null));
        explored = new HashSet<>();
        explored_tiles = new HashSet<>();
        explored_tiles.add(start);
        goal = null;
    }
}
