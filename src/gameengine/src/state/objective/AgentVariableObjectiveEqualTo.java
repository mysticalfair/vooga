package state.objective;

import state.State;
import state.agent.Agent;

import java.util.Map;

/**
 * @author Jamie Palka
 * Class to define the objectives within the game which are triggered by the value of a variable of an agent
 * (x value, y value, or direction) being equal to a targetValue.
 */
public class AgentVariableObjectiveEqualTo extends AgentVariableObjective {

    public AgentVariableObjectiveEqualTo(Map<String, Object> params) {
        super(params);
    }

    /**
     * Executes the outcome if the given agent's property is equal to the target value.
     */
    public void execute(State state) {

        if((state.getCurrentLevelInt() == level || level == -1) && variableValue == targetValue) {
            outcome.execute(state);
        }
    }
}
