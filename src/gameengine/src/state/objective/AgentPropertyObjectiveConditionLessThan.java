package state.objective;

import state.State;

import java.util.Map;

/**
 * @author Jamie Palka
 * Class to define the objectives within the game which are triggered by the value of an agent property
 * being less than or equal to a targetValue.
 */
public class AgentPropertyObjectiveConditionLessThan<T> extends AgentPropertyObjectiveCondition {

    public AgentPropertyObjectiveConditionLessThan(Map<String, Object> params) {
        super(params);
    }

    /**
     * Executes the outcome if the given agent's property is less than or equal to the target value.
     */
    public boolean evaluate(State state) {

        return ((state.getCurrentLevelInt() == level || level == -1) &&
                ((Comparable) agent.getPropertyValue(propertyName)).compareTo(targetValue) <= 0);
    }
}
