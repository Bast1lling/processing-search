package Visualization;

import Basics.Drawable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

public class StackVisualizer extends Visualizer{
    Deque<Drawable> stack;

    public StackVisualizer(int refreshRate, Collection<Drawable> drawables) {
        super(refreshRate);
        stack = new LinkedList<>(drawables);
    }

    @Override
    public Collection<Drawable> next() {
        if(stack.isEmpty())
            return null;
        Collection<Drawable> frame = new ArrayList<>();
        frame.add(stack.pop());
        return frame;
    }
}
