package Search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node<T> {
    private T state;
    private Node<T> parent;
    private Action action;
    private int cost;
    private int depth = 1;

    public Node(T state, Node<T> parent, Action action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.cost = 1;
        this.depth = (parent != null)?(parent.depth + this.depth):0;
    }

    public Node<T> getChild(Problem<T> problem, Action action){
        T next = problem.getResult(state,action);
        return new Node<T>(next,this,action);
    }

    public Node<T> getParent() {
        return parent;
    }

    public List<Node<T>> getRootPath(){
        List<Node<T>> rootPath = new ArrayList<>();
        rootPath.add(this);
        Node<T> parent = this.parent;
        while (parent != null){
            rootPath.add(parent);
            parent = parent.parent;
        }
        return rootPath;
    }

    public List<Node<T>> getChildren(Problem<T> problem){
        List<Node<T>> children = new ArrayList<>();
        List<Action> actions = problem.getActions(state);
        for(Action a : actions){
            children.add(getChild(problem,a));
        }
        return children;
    }

    public List<Action> getSolution(){
        List<Node<T>> rootPath = getRootPath();
        return rootPath.stream().map(tNode -> tNode.action).collect(Collectors.toList());
    }

    public T getState() {
        return state;
    }
}
