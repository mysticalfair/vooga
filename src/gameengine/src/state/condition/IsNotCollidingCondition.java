package state.condition;

import state.IRequiresBaseAgent;
import state.agent.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IsNotCollidingCondition extends Condition implements IRequiresBaseAgent {
    public IsNotCollidingCondition(Map<String, Object> params) {
        super(params);
    }

    private Agent baseAgent;

    @Override
    public List<Agent> getValid(List<Agent> agents) {
        for(Agent a : agents){
            if(baseAgent.isColliding(a)){
                return new ArrayList<>();
            }
        }
        agents = new ArrayList<>();
        agents.add(baseAgent);
        return agents;
    }

    @Override
    public void injectBaseAgent(Agent agent) {
        this.baseAgent = agent;
    }
}
