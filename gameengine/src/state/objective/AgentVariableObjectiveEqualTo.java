package state.objective;

import state.State;
import state.agent.Agent;

public class AgentVariableObjectiveEqualTo extends AgentVariableObjective {

    public AgentVariableObjectiveEqualTo(int id, String title, String variableName,
                                          double targetValue, IObjectiveOutcome outcome) {
        super(id, title, variableName, targetValue, outcome);
    }

    /**
     * Executes the outcome if every agent in agents has the targetValue for the given variable.
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
