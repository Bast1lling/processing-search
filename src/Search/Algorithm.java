package Search;

import Visualization.Visualizer;

public abstract class Algorithm<T,V,U> extends Visualizer {
    protected Problem<T,V,U> problem;
    protected int current_step;

    protected Algorithm(int refreshRate) {
        super(refreshRate);
    }

    public abstract Visualizer solution();
}
