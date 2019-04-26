package state.action.movement;

import state.IRequiresBaseAgent;
import state.agent.AgentUtils;
import state.agent.Agent;

import java.util.Map;

/**
 * Allows an agent to move straight to a specified target agent.
 * @author Jorge Raad
 * @author David Miron
 */
public class MoveStraightToAgent extends MovementAction {

    public MoveStraightToAgent(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        this.speed = (Integer) params.get("speed");
    }

    /**
     * Move the baseAgent to the target agent in a straight line.
     * @param agent The agent to move to.
     */
    @Override
    public void execute(Agent agent, double deltaTime) {
        double currentSpeed = speed;
        double absoluteAngle = AgentUtils.getAngleBetween(baseAgent, agent);
        double xVel = currentSpeed*Math.cos(absoluteAngle);
        double yVel = currentSpeed*Math.sin(absoluteAngle);
        baseAgent.setLocation(xVel*deltaTime, yVel*deltaTime);
    }
}
