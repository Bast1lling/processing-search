package Search;

import java.util.List;

public abstract class Problem<T> implements Reachable<T>{
    protected T state;
    protected Valuable<Float, T> costCalculator;

    public Problem(T state) {
        this.state = state;
    }

    public abstract List<Action> getActions(T state);

    public abstract T getResult(T state, Action action);

    public float getCost(T state1, T state2, Action action){
        return costCalculator.getValue(state1, state2, action);
    }

    public void setCost(Valuable<Float, T> costCalculator) {
        this.costCalculator = costCalculator;
    }
}
