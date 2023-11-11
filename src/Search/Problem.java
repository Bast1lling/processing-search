package Search;

import java.util.List;

public abstract class Problem<T> implements Reachable<T>{
    protected T state;

    public Problem(T state) {
        this.state = state;
    }

    public abstract List<Action> getActions(T state);

    public abstract T getResult(T state, Action action);

    public abstract float getCost(T state1, T state2, Action action);

}
