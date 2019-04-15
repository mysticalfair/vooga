package state.objective;


import state.State;

/**
 * @author Jamie Palka
 * Interface to define functions necessary for all user-created objectives.
 */
public interface IObjective {

    public int getId();

    public String getTitle();

    public boolean checkObjective(State state);

    public void execute(State state);

}
