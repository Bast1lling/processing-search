package Search;

import Visualization.Visualizer;

public abstract class Algorithm<T> extends Visualizer {
    protected Problem<T> problem;
    protected int current_step;

    protected Algorithm(int refreshRate) {
        super(refreshRate);
    }

    public abstract Visualizer solution();
}
