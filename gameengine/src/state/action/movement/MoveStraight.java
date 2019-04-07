package state.action.movement;

import state.agent.IAgent;

import java.awt.*;

/**
 * Allows an agent to move straight.
 * @author Jamie Palka
 * @author David Miron
 */
public class MoveStraight extends MovementAction {

    IAgent baseAgent;

    public MoveStraight(IAgent baseAgent, int speed) {
        this.speed = speed;
        this.baseAgent = baseAgent;
    }

    /**
     * Move the baseAgent straight.
     * @param agent The agent to move to.
     */
    @Override
    public void execute(IAgent agent) {

    }
}
