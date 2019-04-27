package state.condition.propertyCondition;

import state.agent.Agent;
import state.condition.Condition;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PropertyLessThanOrEqualToCondition extends PropertyCondition {

    /**
     * Create an IntervalCondition
     */
    public PropertyLessThanOrEqualToCondition(Map<String, Object> params) {
        super(params);
    }

    /**
     * Return all agents if some interval has passed, or else return none
     * @param agents The agents to filter
     * @return All the agents if some interval has passed, or else an empty list
     */
    @Override
    public List<Agent> getValid(List<Agent> agents) {
        return agents.stream().filter(agent -> (this.value.compareTo(agent.getPropertyValue(this.propertyName)) <= 0)).collect(Collectors.toList());
    }
}
