package state.objective;

import state.State;

import java.util.Map;

/**
 * @author Jamie Palka
 * Class to define the ObjectiveConditions within the game which are triggered by the value of an agent property
 * being less than or equal to a targetPropertyValue.
 */
public class AgentPropertyObjectiveConditionLessThan<T> extends AgentPropertyObjectiveCondition {

    public AgentPropertyObjectiveConditionLessThan(Map<String, Object> params) {
        super(params);
    }

    /**
     * Returns true if the given agent's property is less than or equal to the target value.
     */
    public boolean evaluate(State state) {

        agent = ObjectiveUtils.getAgentFromObjectiveIdentificationPropertyValue(state, objectiveIdentificationPropertyValue);

        return ((state.getCurrentLevelInt() == level || level == -1) &&
                ((Comparable) agent.getPropertyValue(targetPropertyName)).compareTo(targetPropertyValue) <= 0);
    }
}
