package state.objective;

import state.State;
import state.agent.Agent;

/**
 * @author Jamie Palka
 * Class to define the objectives within the game which are triggered by the value of a variable of an agent
 * (x value, y value, or direction) being equal to a targetValue.
 */
public class AgentVariableObjectiveEqualTo extends AgentVariableObjective {

    public AgentVariableObjectiveEqualTo(int id, String title, String variableName, Agent agent,
                                         double targetValue, IObjectiveOutcome outcome) {
        super(id, title, variableName, agent, targetValue, outcome);
    }

    /**
     * Executes the outcome if the given agent's property is equal to the target value.
     */
    public void execute(State state) {

        if(variableValue == targetValue) {
            outcome.execute(state);
        }
    }
}
