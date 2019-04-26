package state.objective;

import state.State;

/**
 * @author Jamie Palka
 * Class to define the functionality of ending the game.
 */
public class GameOverOutcome implements IObjectiveOutcome {

    private static final String GAME_OVER_MESSAGE = "GAME OVER";

    public String execute(State state) {
        state.makeGameOver();
        return GAME_OVER_MESSAGE;
    }
}
