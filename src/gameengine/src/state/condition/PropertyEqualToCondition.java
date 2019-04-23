package state.condition;

import state.Property;
import state.agent.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PropertyEqualToCondition extends Condition {

    private Object value;
    private String propertyName;

    /**
     * Create an IntervalCondition
     */
    public PropertyEqualToCondition(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        this.propertyName = (String) params.get("property");
        this.value = params.get("value");
    }

    /**
     * Return all agents if some interval has passed, or else return none
     * @param agents The agents to filter
     * @return All the agents if some interval has passed, or else an empty list
     */
    @Override
    public List<Agent> getValid(List<Agent> agents) {
        return agents.stream().filter(agent -> (this.value == agent.getProperty(this.propertyName))).collect(Collectors.toList());
    }
}
