package state.action.movement;

import state.agent.Agent;

/**
 * Allows an agent to move on a pre-defined path.
 */
public class MoveOnPointPath extends MovementAction {

    Agent baseAgent;

    public MoveOnPointPath(Agent baseAgent, int speed) {
        this.speed = speed;
        this.baseAgent = baseAgent;
    }

    /**
     * Move the baseAgent on a pre-defined path outlined by points.
     * @param agent The agent to move to.
     */
    @Override
    public void execute(Agent agent, double deltaTime) {

    }
}
