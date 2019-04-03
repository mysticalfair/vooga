package state.Objective;

public class ObjectivePlayer extends Objective {

    public ObjectivePlayer(String title) {
        this.title = title;
        this.met = false;
    }

    public ObjectivePlayer(ObjectiveComplete objective) {
        this.title = objective.getTitle();
    }

    public String getTitle() {
        return this.title + "";
    }

    public boolean getMet() {
        return this.met;
    }
}
