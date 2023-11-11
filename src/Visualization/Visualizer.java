package Visualization;

import Basics.Drawable;

import java.util.*;

public abstract class Visualizer {

    protected static final int FRAME_RATE = 120;

    protected int max_duration;

    private int counter;

    private Collection<Drawable> current_frame;

    protected Visualizer(int refreshRate) {
        current_frame = new ArrayList<>();
        max_duration = refreshRate;
        counter = max_duration;
    }

    //get next frame
    public abstract Collection<Drawable> next();

    public void visualizeStep(int refreshRate){
        //if buffer time is completed, change frame
        if(counter >= refreshRate) {
            current_frame = next();
            counter = 0;
        }
        counter++;
        //if there is no next frame, your work is done
        if(current_frame == null)
            return;

        //else display the frame
        for(Drawable d : current_frame){
            d.draw();
        }
    }

    public void resetDrawables(){
        current_frame = new ArrayList<>();
    }

    public void visualize(){
        //if buffer time is completed, add frame
        if(counter >= max_duration) {
            Collection<Drawable> next = next();
            if(next != null)
                current_frame.addAll(next);
            counter = 0;
        }
        for(Drawable d : current_frame){
            d.draw();
        }
        counter++;
    }

    public void changeVisualizationSpeed(double interval_in_sec){
        double percentage = ((double) counter)/max_duration;
        max_duration = (int) (FRAME_RATE * interval_in_sec);
        counter = (int) (max_duration*percentage);
    }
}
