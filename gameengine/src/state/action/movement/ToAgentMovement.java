package state.action.movement;

import state.agent.IAgent;

import java.awt.*;

public class ToAgentMovement extends MovementAction {
    IAgent baseAgent;

    public ToAgentMovement(IAgent baseAgent, int speed) {
        this.speed = speed;
        this.baseAgent = baseAgent;
    }


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
