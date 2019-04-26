package state.objective;


import state.State;

/**
 * @author Jamie Palka
 * Interface to define functions necessary for all user-created objectives.
 */
public interface IObjective {

    /**
     * Checks necessary conditions and if applicable, executes the outcome on the given state.
     * @param state current state of game
     */
    void execute(State state);

}
