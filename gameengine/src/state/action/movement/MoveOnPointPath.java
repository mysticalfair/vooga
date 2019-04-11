package state.action.movement;

import state.agent.IAgent;

/**
 * Allows an agent to move on a pre-defined path.
 */
public class MoveOnPointPath extends MovementAction {

    IAgent baseAgent;

    public MoveOnPointPath(IAgent baseAgent, int speed) {
        this.speed = speed;
        this.baseAgent = baseAgent;
    }

    /**
     * Move the baseAgent on a pre-defined path outlined by points.
     * @param agent The agent to move to.
     */
    @Override
    public void execute(IAgent agent) {

    }
}
