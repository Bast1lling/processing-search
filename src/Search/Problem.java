package Search;

import java.util.List;
import java.util.function.Predicate;

public abstract class Problem<T,U,V> {
    protected T state;
    protected Predicate<T> reached;
    protected Valuable<Float, T> costCalculator;

    public Problem(T state, Predicate<T> reached) {
        this.state = state;
        this.reached = reached;
    }

    public abstract List<Action<U,V>> getActions(T state);

    public abstract T getResult(T state, Action<U,V> action);

    public float getCost(T state1, T state2, Action<U,V> action){
        return costCalculator.getValue(state1, state2, action);
    }

    public boolean reached(T input){
        return reached.test(input);
    }

    public void setCost(Valuable<Float, T> costCalculator) {
        this.costCalculator = costCalculator;
    }
}
