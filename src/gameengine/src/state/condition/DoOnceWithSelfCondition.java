package state.condition;

import state.IRequiresBaseAgent;
import state.agent.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DoOnceCondition extends Condition implements IRequiresBaseAgent {
    private Agent baseAgent;
    public DoOnceCondition(Map<String, Object> params) {
        super(params);
    }

    @Override
    public List<Agent> getValid(List<Agent> agents) {
        // TODO : handle case of only one agent on screen
        if (agents.size() > 0){
            agents = new ArrayList<>();
            agents.add(baseAgent);
        }
//        // pass a dummy agent always, that way the action will always occur once, even if the given list is empty.
//        // This means that other conditions such as interval must be checked afterwards
//        agents = new ArrayList<>();
//        agents.add(this.baseAgent);
        return agents;
    }

    @Override
    public void injectBaseAgent(Agent agent) {
        this.baseAgent = agent;
    }
}
