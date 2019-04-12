package state.condition;

import state.agent.Agent;
import state.agent.IAgent;

import java.util.ArrayList;
import java.util.List;

public class DiedCondition extends BaseAgentCondition {
    // TODO: Remove this
    public DiedCondition(Agent base) {
        this.baseAgent = base;
    }

    @Override
    public List<IAgent> getValid(List<IAgent> agents) {

        List<IAgent> newAgents = new ArrayList<>();
        for (IAgent agent: agents){
            if(agent.isDead()){
                newAgents.add(agent);
            }
        }
        return newAgents;
    }
}
