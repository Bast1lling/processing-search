package Search;

import java.util.List;

public abstract class Problem<T,U,V> implements Reachable<T>{
    protected T state;
    protected Valuable<Float, T> costCalculator;

    public Problem(T state) {
        this.state = state;
    }

    public abstract List<Action<U,V>> getActions(T state);

    public abstract T getResult(T state, Action<U,V> action);

    public float getCost(T state1, T state2, Action<U,V> action){
        return costCalculator.getValue(state1, state2, action);
    }

    public void setCost(Valuable<Float, T> costCalculator) {
        this.costCalculator = costCalculator;
    }
}
