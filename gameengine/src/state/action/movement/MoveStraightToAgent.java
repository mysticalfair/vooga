package state.action.movement;

import state.agent.AgentUtils;
import state.agent.Agent;

/**
 * Allows an agent to move straight to a specified target agent.
 * @author Jorge Raad
 * @author David Miron
 */
public class MoveStraightToAgent extends MovementAction {
    Agent baseAgent;

    public MoveStraightToAgent(Agent baseAgent) {
        this.baseAgent = baseAgent;
    }

    /**
     * Move the baseAgent to the target agent in a straight line.
     * @param agent The agent to move to.
     */
    @Override
    public void execute(Agent agent, double deltaTime) {
        double speed = Math.sqrt(Math.pow(baseAgent.getXVelocity(), 2) + Math.pow(baseAgent.getYVelocity(), 2));
        double absoluteAngle = AgentUtils.getAngleBetween(baseAgent, agent);
        baseAgent.updateVelocity(speed*Math.cos(absoluteAngle), speed*Math.sin(absoluteAngle));
    }
}
