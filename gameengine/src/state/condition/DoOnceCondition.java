package state.condition;

import state.agent.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DoOnceCondition extends Condition {

    public DoOnceCondition(Map<String, Object> params) {
        super(params);
    }

    @Override
    public List<Agent> getValid(List<Agent> agents) {
        if (agents.size() > 0){
            Agent agent = agents.get(0);
            agents = new ArrayList<>();
            agents.add(agent);
        }
        return agents;
    }
}
