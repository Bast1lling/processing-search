package Search.Board;

import Basics.Board;
import Basics.Drawable;
import Basics.Tile;
import Search.Algorithm;
import Search.Node;
import Visualization.Visualizer;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CleverDFSAlgorithm extends BoardPathAlgorithm {

    protected List<Node<Tile>> frontier;
    private Set<Tile> explored_frontier;

    public CleverDFSAlgorithm(float interval_in_sec, Tile start, Board board) {
        super(interval_in_sec);
        setProblem(start, board);
    }

    public void setProblem(Tile start, Board board) {
        resetDrawables();
        this.problem = new BoardPathProblem(start, board);
        frontier = new ArrayList<>();
        frontier.add(new Node<>(start,null,null));
        explored = new HashSet<>();
        explored_frontier = new HashSet<>();
        explored_frontier.add(start);
        goal = null;
    }

    @Override
    public Collection<Drawable> next() {
        //if no options left, give up OR if goal is already reached, there is no step to take
        if(frontier.isEmpty() || goal != null)
            return null;

        int last = frontier.size()-1;
        //make sure the option is inside the board
        Node<Tile> node = frontier.get(last);
        frontier.remove(node);
        while (node.getState() == null){
            last = frontier.size()-1;
            if(frontier.isEmpty())
                return null;
            node = frontier.get(last);
            frontier.remove(node);
        }

        //explore option
        explored.add(node.getState());

        //create visualization of current node
        Collection<Drawable> drawables = new ArrayList<>();
        Node<Tile> finalNode = node;
        drawables.add(() -> finalNode.getState().draw(Color.WHITE));

        //if option is good enough, set goal and return
        if(problem.reached(node.getState())) {
            goal = node;
            return drawables;
        }

        //else select frontier by iterating over children
        for(Node<Tile> child : node.getChildren(problem)){
            //select existing child, which has not yet been explored/ set out to be explored
            if(child != null && !explored.contains(child.getState()) && !frontier.contains(child) &&!explored_frontier.contains(child.getState())) {
                frontier.add(child);
                explored_frontier.add(child.getState());
            }
        }
        return drawables;
    }
}
