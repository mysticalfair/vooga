package state.Objective;

import state.IStateComplete;

public class ObjectiveComplete extends ObjectivePlayer implements IObjectiveComplete {
    public ObjectiveComplete(String title, boolean met) {
        super(title);
        setMet(met);
    }

    public void setMet(boolean met) {
        this.met = met;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void execute(IStateComplete state) {
        //TODO: David
    }
    public IObjective toImmutable() {
        return new ObjectivePlayer(this);
    }
}
