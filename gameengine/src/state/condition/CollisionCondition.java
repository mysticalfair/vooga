package state.condition;

import state.agent.IAgent;

import java.util.List;

/**
 * A Condition that narrows down the given list of agents to only include the agents that are currently intersecting
 * with the baseAgent.
 * @author Jorge Raad
 */
public class CollisionCondition extends BaseAgentCondition{
    @Override
    public List<IAgent> getValid(List<IAgent> agents) {
        for(int k = 0; k < agents.size(); k++){
            if(!baseAgent.isColliding(agents.get(k))){
                agents.remove(k);
            }
        }
        return agents;
    }
}
