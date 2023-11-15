package Search.Board;

import Basics.Board;
import Basics.Drawable;
import Basics.Tile;
import Search.Action;
import Search.Node;
import processing.core.PVector;

import java.awt.*;
import java.util.*;
import java.util.List;

public class RecursiveDFSAlgorithm extends BoardPathAlgorithm{


    public RecursiveDFSAlgorithm(int refreshRate) {
        super(refreshRate);
    }

    public void setProblem(Tile start, Board board) {
        resetDrawables();
        this.problem = new BoardPathProblem(start, board);
    }

    public Collection<Drawable> complete(Set<Tile> explored, Node<Tile,Void, PVector> current, Board board, int depth) {

        if(depth <= 0 || current == null || current.getState() == null || explored.contains(current.getState()))
            return new ArrayList<>();

        explored.add(current.getState());
        if(problem.reached(current.getState())) {
            Drawable d = () -> current.getState().draw(Color.WHITE);
            Collection<Drawable> result = new ArrayList<>();
            result.add(d);
            return result;
        }

        List<Action<Void, PVector>> actions = problem.getActions(current.getState());
        Collection<Drawable>[] part_solutions = new Collection[actions.size()];
        for(int i = 0; i<actions.size(); i++){
            //part_solutions[i] = complete(explored,current.getChild(problem,actions.get(i)),board,depth-1);
        }
        Collection<Drawable> sofar = combineArray(part_solutions);
        if(!sofar.isEmpty())
            sofar.add(() -> current.getState().draw(Color.GRAY));
        return sofar;
    }

    private Collection<Drawable> combineArray(Collection<Drawable>[] array){
        Collection<Drawable> result = new ArrayList<>();
        for(int i = 0; i< array.length; i++){
            result.addAll(array[i]);
        }
        return result;
    }

    @Override
    public Collection<Drawable> next() {
        return null;
    }
}
