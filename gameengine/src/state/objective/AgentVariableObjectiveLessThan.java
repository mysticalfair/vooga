package state.objective;

import state.State;

public class AgentVariableObjectiveLessThan extends AgentVariableObjective {

    public AgentVariableObjectiveLessThan(int id, String title, String variableName,
                                             double targetValue, IObjectiveOutcome outcome) {
        super(id, title, variableName, targetValue, outcome);
    }

    public void execute(State state) {

        //TODO

    }
}
