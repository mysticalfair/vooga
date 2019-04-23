package state.condition;

import state.agent.Agent;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PropertyLessThanCondition extends Condition {

    private Comparable value;
    private String propertyName;

    /**
     * Create an IntervalCondition
     */
    public PropertyLessThanCondition(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        this.propertyName = (String) params.get("property");
        this.value = (Comparable) params.get("value");
    }

    /**
     * Return all agents if some interval has passed, or else return none
     * @param agents The agents to filter
     * @return All the agents if some interval has passed, or else an empty list
     */
    @Override
    public List<Agent> getValid(List<Agent> agents) {
        return agents.stream().filter(agent -> (this.value.compareTo(agent.getProperty(this.propertyName)) < 0)).collect(Collectors.toList());
    }
}
