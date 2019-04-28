package state.objective;

import authoring.IAgentDefinition;
import state.Property;
import state.State;
import state.agent.Agent;
import state.attribute.Attribute;

import java.util.Map;

/**
 * @author Jamie Palka
 * A class to define the objectives within the game which are triggered by the existence of agents with a given property value
 */
public class AgentExistenceObjective<T> extends Objective {

    protected int targetNumber; // target number of agents with given property
    protected String propertyName; // the property the user wishes to check agents by
    protected T targetValue; // the value for the property needed

    public AgentExistenceObjective(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {

        this.targetNumber = (int) params.get("targetNumber");
        this.propertyName = (String) params.get("propertyName");
        this.targetValue = (T) params.get("targetValue");
        super.setParams(params);

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
