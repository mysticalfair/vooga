package state.objective;

import authoring.IObjectiveDefinition;
import state.State;

public class Objective implements IObjectiveDefinition {

    private ObjectiveCondition condition;
    private ObjectiveOutcome outcome;

    public Objective(ObjectiveCondition condition, ObjectiveOutcome outcome) {
        this.condition = condition;
        this.outcome = outcome;
    }

    public void execute(State state) {

        if(condition.evaluate(state)) {
            outcome.execute(state);
        }
    }
}

