package state.objective;

import state.State;
import state.agent.Agent;

/**
 * @author Jamie Palka
 * Abstract class to define the objectives within the game which are triggered by the value of a variable of an agent
 * (x value, y value, or direction)
 */
public class AgentVariableObjectiveEqualTo extends AgentVariableObjective {

    public AgentVariableObjectiveEqualTo(int id, String title, String variableName,
                                          double targetValue, IObjectiveOutcome outcome) {
        super(id, title, variableName, targetValue, outcome);
    }

    /**
     * Executes the outcome if the given agent's property is equal to the target value.
     */
    public void execute(State state) {

        double variableValue = 0;

        for(Agent agent : agents) {

            if(variableName.equals("x")) { variableValue = agent.getX(); }
            if(variableName.equals("y")) { variableValue = agent.getY(); }
            if(variableName.equals("direction")) { variableValue = agent.getDirection(); }
            if(variableValue != targetValue) { return; }
            outcome.execute(state);
        }
    }
}
