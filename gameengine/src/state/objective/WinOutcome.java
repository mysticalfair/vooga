package state.objective;

import state.State;

/**
 * @author Jamie Palka
 * Class to define the functionality winning the game.
 */
public class WinOutcome implements IObjectiveOutcome {

    private static final String WIN_MESSAGE = "YOU WIN!";

    public String execute(State state) {
        state.makeGameOver();
        return WIN_MESSAGE;
    }

}
