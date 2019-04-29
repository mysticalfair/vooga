package state.objective.objectivecondition;

import state.State;
import state.objective.ObjectiveUtils;

import java.util.Map;

/**
 * @author Jamie Palka
 * Class to define the ObjectiveConditions within the game which are triggered by the value of a variable of an agent
 * (x value, y value, or direction) being less than or equal to a targetPropertyValue.
 */
public class AgentVariableObjectiveConditionLessThan extends AgentVariableObjectiveCondition {

    public AgentVariableObjectiveConditionLessThan(Map<String, Object> params) { super(params); }

    /**
     * Returns true if the given agent's property is less than or equal to the target value.
     */
    public boolean evaluate(State state) {

        agent = ObjectiveUtils.getAgentFromObjectiveIdentificationPropertyValue(state, objectiveIdentificationPropertyValue);
        setVariableValue(agent);

        return ((state.getCurrentLevelInt() == level || level == -1) && variableValue <= targetValue);
    }
}
