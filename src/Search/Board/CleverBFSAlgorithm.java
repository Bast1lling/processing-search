package Search.Board;

import Basics.Board;
import Basics.Drawable;
import Basics.Tile;
import Search.Node;

import java.awt.*;
import java.util.*;

public class CleverBFSAlgorithm extends BoardPathAlgorithm {

    protected Deque<Node<Tile>> frontier;
    private Set<Tile> explored_frontier;

    public CleverBFSAlgorithm(int refreshRate, Tile start, Board board) {
        super(refreshRate);
        setProblem(start, board);
    }

    public void setProblem(Tile start, Board board) {
        resetDrawables();
        this.problem = new BoardPathProblem(start, board);
        frontier = new LinkedList<>();
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

        //make sure the option is inside the board
        Node<Tile> node = frontier.pop();
        while (node.getState() == null){
            if(frontier.isEmpty())
                return null;
            node = frontier.pop();
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
                if(child.getState() != null)
                    drawables.add(() -> child.getState().draw(Color.GRAY));
            }
        }
        return drawables;
    }
}
