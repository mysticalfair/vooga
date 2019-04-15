package state.condition;

import state.agent.Agent;
import java.util.List;

/**
 * Class to that narrows down a group of agents to the single closest agent.
 * @author Jorge Raad
 */
public class ClosestCondition extends BaseAgentCondition {
    @Override
    public List<Agent> getValid(List<Agent> agents) {
        // if list has 0 or one agents, then return same list.
        if (agents.size() <= 1) {
            return agents;
        }
        for (int k = 0; k < agents.size() - 1; k++) {
            agents.remove(getFartherAgent(agents.get(k), agents.get(k+1)));
        }
        return agents;
    }

    private Agent getFartherAgent(Agent a, Agent b){
        double aDistance = baseAgent.calculateDistance(a);
        double bDistance = baseAgent.calculateDistance(b);
        if(aDistance >= bDistance) return a;
        return b;
    }

}
