package state.action.movement;

import state.agent.IAgent;

import java.awt.*;

/**
 * Allows an agent to move straight to a specified target agent.
 * @author David Miron
 */
public class MoveStraightToAgent extends MovementAction {
    IAgent baseAgent;

    public MoveStraightToAgent(IAgent baseAgent, int speed) {
        this.speed = speed;
        this.baseAgent = baseAgent;
    }

    /**
     * Move the baseAgent to the target agent in a straight line.
     * @param agent The agent to move to.
     */
    @Override
    public void execute(IAgent agent) {
        Point movement = new Point();
        double xDisplacement = (agent.getLocation().getX() - baseAgent.getLocation().getX());
        double yDisplacement = (agent.getLocation().getY() - baseAgent.getLocation().getY());
        // TODO: Actually get Agent to move towards other agent at its speed.
        //  Consider making protected methods in MovementAction that can be used more specifically in subclasses.
        baseAgent.move(movement);
    }
}
