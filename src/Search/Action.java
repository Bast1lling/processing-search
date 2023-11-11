package Search;

public class Action{
    private Actor actor;
    private int id;

    public Action(Actor actor, int id) {
        this.actor = actor;
        this.id = id;
    }

    public void act(){
        actor.act();
    }

    public int getId() {
        return id;
    }

    public boolean equals(Action action) {
        return action.id == this.id;
    }
}
