package state.action;

import state.agent.AgentUtils;
import state.agent.Agent;

/**
 * Action that changes the direction of the agent to face the given Agent.
 * @author Jorge Raad
 */
public class PointToAgent extends BaseAgentAction{

    @Override
    public void execute(Agent agent) throws CloneNotSupportedException {
        //baseAgent.setDirection(AgentUtils.getAngleBetween(baseAgent, agent));
        System.out.println("COLLISION");
    }
}
