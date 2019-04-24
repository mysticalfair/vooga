package state.objective;

import state.State;
import state.agent.Agent;

/**
 * @author Jamie Palka
 * Class to define the objectives within the game which are triggered by the value of an agent property
 * being greater than or equal to a targetValue.
 */
public class AgentPropertyObjectiveGreaterThan<T> extends AgentPropertyObjective {

    public AgentPropertyObjectiveGreaterThan(int id, String title, String propertyName, Agent agent,
                                         T targetValue, IObjectiveOutcome outcome, int level) {
        super(id, title, propertyName, agent, targetValue, outcome, level);
    }

    /**
     * Executes the outcome if the given agent's property is greater than or equal to the target value.
     */
    public void execute(State state) {

        if((state.getCurrentLevelInt() == level || level == -1) &&
                ((Comparable) agent.getPropertyValue(propertyName)).compareTo(targetValue) >= 0) {
            outcome.execute(state);
        }

    }

}
