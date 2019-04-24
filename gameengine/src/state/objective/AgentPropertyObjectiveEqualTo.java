package state.objective;

import state.State;
import state.agent.Agent;

import java.util.ArrayList;

public class AgentPropertyObjectiveEqualTo<T> extends AgentPropertyObjective {

    public AgentPropertyObjectiveEqualTo(int id, String title, String propertyName, T targetValue, IObjectiveOutcome outcome) {
        super(id, title, propertyName, targetValue, outcome);
        agents = new ArrayList<Agent>();
    }

    /**
     * Executes the outcome if every agent in agents has a value for the given property that is equal to the target value.
     */
    public void execute(State state) {

        for(Agent agent : agents) {

            //TODO fix this error

            if(!agent.getPropertyValue(title).equals(targetValue)) {
                return;
            }

            outcome.execute(state);
        }
    }


}
