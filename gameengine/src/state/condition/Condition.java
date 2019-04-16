package state.condition;


import authoring.GameFactory;
import authoring.IConditionDefinition;
import state.agent.Agent;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Abstract class to represent some condition for a group of agents
 * @author David Miron
 */
public abstract class Condition implements IConditionDefinition, Serializable {

    private String name;
    private Map<String, ? extends Object> params;

    public Condition(Map<String, ? extends Object> params) {
        this.params = params;
        setParams(params);
    }

    @Override
    public Map<String, ? extends Object> getParams() {
        return this.params;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Filter out agents based on some condition
     * @param agents The agents to filter
     * @return A new version of the list of agents, filtered based on some condition
     */
    public abstract List<Agent> getValid(List<Agent> agents);

}
