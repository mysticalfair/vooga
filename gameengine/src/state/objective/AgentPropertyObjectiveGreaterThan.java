package state.objective;

import state.State;

public class AgentPropertyObjectiveGreaterThan<T> extends AgentPropertyObjective {

    public AgentPropertyObjectiveGreaterThan(int id, String title, String propertyName, T targetValue, IObjectiveOutcome outcome) {
        super(id, title, propertyName, targetValue, outcome);
    }

    public void execute(State state) {

        //TODO - how compare values of T?

    }

}
