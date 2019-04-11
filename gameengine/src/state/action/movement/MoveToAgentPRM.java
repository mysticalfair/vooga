package state.action.movement;

import state.agent.IAgent;

/**
 * Allows an agent to move to a specified target agent using the shortest path.
 * @author David Miron
 */
public class MoveToAgentPRM extends MovementAction {

    IAgent baseAgent;

    public MoveToAgentPRM(IAgent baseAgent, int speed) {
        this.speed = speed;
        this.baseAgent = baseAgent;
    }

    /**
     * Move the baseAgent to the specified agent using the shortest path.
     * @param agent The agent to move to.
     */
    @Override
    public void execute(IAgent agent) {

    }
}
