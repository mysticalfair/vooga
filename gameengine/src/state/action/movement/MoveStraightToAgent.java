package state.action.movement;

import state.agent.AgentUtils;
import state.agent.IAgent;

/**
 * Allows an agent to move straight to a specified target agent.
 * @author Jorge Raad
 * @author David Miron
 */
public class MoveStraightToAgent extends MovementAction {
    IAgent baseAgent;

    public MoveStraightToAgent(IAgent baseAgent) {
        this.baseAgent = baseAgent;
    }

    /**
     * Move the baseAgent to the target agent in a straight line.
     * @param agent The agent to move to.
     */
    @Override
    public void execute(IAgent agent) {
        double speed = Math.sqrt(Math.pow(baseAgent.getXVelocity(), 2) + Math.pow(baseAgent.getYVelocity(), 2));
        double absoluteAngle = AgentUtils.getAngleBetween(baseAgent, agent);
        baseAgent.updateVelocity(speed*Math.cos(absoluteAngle), speed*Math.sin(absoluteAngle));
    }
}
