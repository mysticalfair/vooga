package state.condition;

import state.agent.Agent;
import state.agent.Agent;

import java.util.ArrayList;
import java.util.List;

public class DiedCondition extends BaseAgentCondition {
    // TODO: Remove this
    public DiedCondition(Agent base) {
        this.baseAgent = base;
    }

    @Override
    public List<Agent> getValid(List<Agent> agents) {

        List<Agent> newAgents = new ArrayList<>();
        for (Agent agent: agents){
            if(agent.isDead()){
                newAgents.add(agent);
            }
        }
        return newAgents;
    }
}
