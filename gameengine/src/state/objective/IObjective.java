package state.objective;


import state.State;

/**
 * @author Jamie Palka
 * Interface to define functions necessary for all user-created objectives.
 */
public interface IObjective {

    void execute(State state);

}
