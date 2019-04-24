package state.objective;

import state.State;

/**
 * @author Jamie Palka
 * Class to define the functionality of moving to the next level.
 */
public class NextLevelOutcome implements IObjectiveOutcome {

    public void execute(State state) {
        state.nextLevel();
    }

}
