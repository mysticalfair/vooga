package state.objective;

import state.State;
import state.agent.Agent;

public class AgentPropertyObjectiveGreaterThan<T> extends AgentPropertyObjective {

    public AgentPropertyObjectiveGreaterThan(int id, String title, String propertyName, Comparable<T> targetValue, IObjectiveOutcome outcome) {
        super(id, title, propertyName, targetValue, outcome);
    }

    public void execute(State state) {

        //TODO - how compare values of T?

        for(Agent agent : this.agents) {

            //TODO fix this error. do a check??

            if(!(((Comparable) agent.getPropertyValue(title)).compareTo(targetValue) > 0) {
                return;
            }

            outcome.execute(state);
        }

    }

}
