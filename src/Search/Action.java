package Search;

public class Action{
    private Actable actable;
    private int id;

    public Action(Actable actable, int id) {
        this.actable = actable;
        this.id = id;
    }

    public void act(){
        actable.act();
    }

    public int getId() {
        return id;
    }

    public boolean equals(Action action) {
        return action.id == this.id;
    }
}
