package state.action;

import state.agent.AgentUtils;
import state.agent.IAgent;

/**
 * Action that changes the direction of the agent to face the given Agent.
 * @author Jorge Raad
 */
public class PointToAgent extends BaseAgentAction{

    @Override
    public void execute(IAgent agent) throws CloneNotSupportedException {
        baseAgent.setDirection(AgentUtils.getAngleBetween(baseAgent, agent));
    }
}
