package state.condition;

import state.agent.IAgent;

import java.util.List;

/**
 * Condition that narrows the given list of agents to only include those within a given range.
 * @author Jorge Raad
 */
public class RangeCondition extends BaseAgentCondition {
    private double range;

    @Override
    public List<IAgent> getValid(List<IAgent> agents) {
        for(int k = 0; k < agents.size(); k++){
            if(baseAgent.calculateDistance(agents.get(k)) > range){
                agents.remove(agents.get(k));
            }
        }
        return agents;
    }
}
