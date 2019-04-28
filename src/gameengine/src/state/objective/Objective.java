package state.objective;

import state.State;

public class Objective {

    private ObjectiveCondition condition;
    private ObjectiveOutcome outcome;

    public void execute(State state) {

        if(condition.evaluate(state)) {
            outcome.execute(state);
        }
    }


}

