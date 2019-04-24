package state.objective;

import state.State;

/**
 * @author Jamie Palka
 * Interface to define functions necessary for all ObjectiveOutcomes.
 */
public interface IObjectiveOutcome {

    /**
     * Executes the ObjeciveOutcome on the respective state.
     * @param state the current state which the outcome will be executed on.
     */
    void execute(State state);
}
