package state.condition;

import state.agent.Agent;
import state.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * A Condition that narrows down the given list of agents to only include the agents that are currently intersecting
 * with the baseAgent.
 * @author Jorge Raad
 */
public class CollisionCondition extends BaseAgentCondition{

    // TODO: Remove this
    public CollisionCondition(Agent base) {
        this.baseAgent = base;
    }

    @Override
    public List<Agent> getValid(List<Agent> agents) {

        List<Agent> newAgents = new ArrayList<>();
        for (Agent agent: agents){
            if(baseAgent.isColliding(agent)){
                newAgents.add(agent);
            }
        }
        return newAgents;
    }
}
