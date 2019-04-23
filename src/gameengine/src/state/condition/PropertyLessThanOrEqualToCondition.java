package state.condition;

import state.agent.Agent;

import java.util.List;
import java.util.Map;

public class PropertyLessThanOrEqualToCondition extends Condition {
    public PropertyLessThanOrEqualToCondition(Map<String, Object> params) {
        super(params);
    }

    @Override
    public List<Agent> getValid(List<Agent> agents) {
        return null;
    }
}
