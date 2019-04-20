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
        var properties = agent.getProperties();
        double currentSpeed = speed;
        // possibly apply some kind of multiplier or something from properties idk
        double newAbsoluteAngle = agent.getDirection() - angle;
        double xVel = currentSpeed*Math.cos(Math.PI/180*newAbsoluteAngle);
        double yVel = currentSpeed*Math.sin(Math.PI/180*newAbsoluteAngle);
        baseAgent.setLocation(xVel*deltaTime + baseAgent.getX(), yVel*deltaTime + baseAgent.getY());
    }
}
