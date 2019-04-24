package state.objective;

import state.State;

/**
 * @author Jamie Palka
 * Class to define the functionality of ending the game.
 */
public class GameOverOutcome implements IObjectiveOutcome {

    public void execute(State state) {
        state.makeGameOver();
    }
}
