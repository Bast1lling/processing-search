package Search;

import Basics.Drawable;
import Visualization.Visualizer;
import processing.core.PApplet;

import java.util.List;

public abstract class Algorithm<T> extends Visualizer {
    protected Problem<T> problem;
    protected int current_step;

    protected Algorithm(float interval_in_sec) {
        super(interval_in_sec);
    }

    public abstract Visualizer solution();
}
