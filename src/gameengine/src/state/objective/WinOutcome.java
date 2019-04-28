package state.objective;

import state.State;

import java.util.Map;

/**
 * @author Jamie Palka
 * Class to define the functionality winning the game.
 */
public class WinOutcome extends ObjectiveOutcome {

    private static final String WIN_MESSAGE = "YOU WIN!";

    public WinOutcome(Map<String, Object> params) { super(params); }

    @Override
    public void setParams(Map<String, Object> params) {
        super.setParams(params);
    }

    public String execute(State state) {
        state.makeGameOver();
        return WIN_MESSAGE;
    }

}
