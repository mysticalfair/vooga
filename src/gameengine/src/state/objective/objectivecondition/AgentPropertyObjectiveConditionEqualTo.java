package state.objective.objectivecondition;

import state.State;
import state.objective.ObjectiveUtils;

import java.util.Map;

/**
 * @author Jamie Palka
 * Class to define the ObjectiveConditions within the game which are triggered by the value of an agent property
 * being equal to a target value.
 */
public class AgentPropertyObjectiveConditionEqualTo<T> extends AgentPropertyObjectiveCondition {

    public AgentPropertyObjectiveConditionEqualTo(Map<String, Object> params) {
        super(params);
    }

    /**
     * Returns true if the given agent's property is equal to the target value.
     */
    public boolean evaluate(State state) {

        agent = ObjectiveUtils.getAgentFromObjectiveIdentificationPropertyValue(state, objectiveIdentificationPropertyValue);

        return ((state.getCurrentLevelInt() == level || level == -1) &&
                ((Comparable) agent.getPropertyValue(targetPropertyName)).compareTo(targetPropertyValue) == 0);
    }
}
