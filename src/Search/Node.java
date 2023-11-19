package Search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Node<T,U,V> {
    private T state;
    private Node<T,U,V> parent;
    private Action<U,V> action;
    private int cost;
    private int depth = 1;

    public Node(T state, Node<T,U,V> parent, Action<U,V> action) {
        this.state = state;
        this.parent = parent;
        this.action = action;
        this.cost = 1;
        this.depth = (parent != null)?(parent.depth + this.depth):0;
    }

    public Node<T,U,V> getChild(Problem<T,U,V> problem, Action<U,V> action){
        T next = problem.getResult(state,action);
        return new Node<T,U,V>(next,this,action);
    }

    public Node<T,U,V> getParent() {
        return parent;
    }

    public Action<U, V> getAction() {
        return action;
    }

    public List<Node<T,U,V>> getRootPath(){
        List<Node<T,U,V>> rootPath = new ArrayList<>();
        rootPath.add(this);
        Node<T,U,V> parent = this.parent;
        while (parent != null){
            rootPath.add(parent);
            parent = parent.parent;
        }
        return rootPath;
    }

    public List<Node<T,U,V>> getChildren(Problem<T,U,V> problem){
        List<Node<T,U,V>> children = new ArrayList<>();
        List<Action<U,V>> actions = problem.getActions(state);
        for(Action<U,V> a : actions){
            children.add(getChild(problem,a));
        }
        return children;
    }

    public List<Action<U,V>> getSolution(){
        List<Node<T,U,V>> rootPath = getRootPath();
        return rootPath.stream().map(tNode -> tNode.action).collect(Collectors.toList());
    }

    public T getState() {
        return state;
    }
}
