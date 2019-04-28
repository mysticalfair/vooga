package state.objective;

import state.State;
import state.agent.Agent;

import java.util.Map;

/**
 * @author Jamie Palka
 * Class to define the objectives within the game which are triggered by the value of an agent property
 * being equal to a target value.
 */
public class AgentPropertyObjectiveEqualTo<T> extends AgentPropertyObjective {

    public AgentPropertyObjectiveEqualTo(Map<String, Object> params) {
        super(params);
    }

    /**
     * Executes the outcome if the given agent's property is equal to the target value.
     */
    public void execute(State state) {

        if((state.getCurrentLevelInt() == level || level == -1) &&
                ((Comparable) agent.getPropertyValue(propertyName)).compareTo(targetValue) == 0) {
            outcome.execute(state);
        }
    }
}
