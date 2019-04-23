package state.objective;

import state.State;

public interface IObjectiveOutcome {

    /**
     * Executes the ObjeciveOutcome on the respective state.
     * @param state the current state which the outcome will be executed on.
     */
    void execute(State state);
}
