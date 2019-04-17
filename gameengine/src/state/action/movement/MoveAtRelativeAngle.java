package state.action.movement;

import state.Property;
import state.agent.Agent;

import java.util.Map;

/**
 * Allows an agent to move at a constant angle, relative to its orientation
 * @author Jamie Palka
 * @author David Miron
 */
public class MoveAtRelativeAngle extends MovementAction {

    /* The angle to move at, relative to the current orientation, in degrees.
     *              0 means forwards, positive angle means move to the right, and negative angle means left.
     *              ex. angle = 45 means always move forwards and to the right, -90 means always move left.
     */
    private double angle;

    public MoveAtRelativeAngle(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        this.angle = (Double) params.get("angle");
        this.speed = (Integer) params.get("speed");
    }

    /**
     * Move the baseAgent straight.
     *
     * @param agent Should be ignored
     */
    @Override
    public void execute(Agent agent, double deltaTime) {
//        TODO: update speed according to speed property
        var properties = agent.getProperties();
        double xVel = (double) agent.getProperty("xVelocity");
        double yVel = (double) agent.getProperty("yVelocity");

        double curr_speed = Math.sqrt(Math.pow(xVel, 2) + Math.pow(yVel, 2));
        double newAbsoluteAngle = agent.getDirection() - angle;
        double newXVelocity = curr_speed * Math.cos(newAbsoluteAngle);
        double newYVelocity = curr_speed * Math.sin(newAbsoluteAngle);
        baseAgent.setProperties(new String[] {"xVelocity", "yVelocity"}, new Object[] {newXVelocity, newYVelocity});
    }
}
