package state.action.movement;

import state.action.BaseAgentAction;
import state.agent.IAgent;

import java.awt.*;

/**
 * Allows an agent to move at a constant angle, relative to its orientation
 * @author Jamie Palka
 * @author David Miron
 */
public class MoveAtRelativeAngle extends BaseAgentAction {

    private double angle;

    /**
     * Create the Class
     * @param angle The angle to move at, relative to the current orientation, in degrees.
     *              0 means forwards, positive angle means move to the right, and negative angle means left.
     *              ex. angle = 45 means always move forwards and to the right, -90 means always move left.
     */
    public MoveAtRelativeAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Move the baseAgent straight.
     * @param agent Should be ignored
     */
    @Override
    public void execute(IAgent agent) {

        double curr_speed = Math.sqrt(Math.pow(agent.getXVelocity(), 2) + Math.pow(agent.getYVelocity(), 2));
        double newAbsoluteAngle = agent.getDirection() - angle;
        double newXVelocity = curr_speed * Math.cos(newAbsoluteAngle);
        double newYVelocity = curr_speed * Math.sin(newAbsoluteAngle);
        baseAgent.updateVelocity(newXVelocity, newYVelocity);
    }
}
