package state.condition;

import state.agent.Agent;
import state.agent.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A Condition that narrows down the given list of agents to only include the agents that are currently intersecting
 * with the baseAgent.
 * @author Jorge Raad
 */
public class CollisionCondition extends BaseAgentCondition{

    public CollisionCondition(Map<String, ? extends Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, ? extends Object> params) {
        // Do nothing
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
