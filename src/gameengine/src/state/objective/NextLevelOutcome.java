package state.objective;

import state.State;

import java.util.Map;

/**
 * @author Jamie Palka
 * Class to define the functionality of moving to the next level.
 */
public class NextLevelOutcome extends ObjectiveOutcome {

    public NextLevelOutcome(Map<String, Object> params) { super(params); }

    @Override
    public void setParams(Map<String, Object> params) {
        super.setParams(params);
    }

    public String execute(State state) {
        state.nextLevel();
        return null;
    }

}
