package state.objective;

import authoring.IAgentDefinition;
import state.Property;
import state.State;
import state.agent.Agent;

/**
 * @author Jamie Palka
 * A class to define the objectives within the game which are triggered by the existence of agents with a given property value
 */
public class AgentExistenceObjective<T> implements IObjective {

    protected int id;
    protected String title;
    protected int targetNumber; // target number of agents with given property
    protected IObjectiveOutcome outcome;
    protected String propertyName; // the property the user wishes to check agents by
    protected T targetValue; // the value for the property needed
    protected int level;

    public AgentExistenceObjective(int id, String title, String propertyName, int targetNumber,
                                   IObjectiveOutcome outcome, int level) {
        this.id = id;
        this.title = title;
        this.propertyName = propertyName;
        this.targetNumber = targetNumber;
        this.outcome = outcome;
        this.level = level;
    }

    /**
     * Executes the outcome if the correct number of agents with the given property value exist.
     */
    public void execute(State state) {

        int count = 0;
        for(IAgentDefinition agent : state.getCurrentAgents()) {
            if(((Comparable) agent.getPropertyValue(propertyName)).compareTo(targetValue) == 0) {
                count++;
            }
        }
        if((state.getCurrentLevelInt() == level || level == -1) && count == targetNumber) { outcome.execute(state); }
    }
}
