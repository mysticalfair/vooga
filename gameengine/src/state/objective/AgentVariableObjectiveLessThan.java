package state.objective;

import state.State;

/**
 * @author Jamie Palka
 * Abstract class to define the objectives within the game which are triggered by the value of a variable of an agent
 * (x value, y value, or direction)
 */
public class AgentVariableObjectiveLessThan extends AgentVariableObjective {

    public AgentVariableObjectiveLessThan(int id, String title, String variableName,
                                             double targetValue, IObjectiveOutcome outcome) {
        super(id, title, variableName, targetValue, outcome);
    }

    /**
     * Executes the outcome if the given agent's property is equal to the target value.
     */
    public void execute(State state) {

        //TODO

    }
}
