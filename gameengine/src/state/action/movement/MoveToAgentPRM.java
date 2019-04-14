package state.action.movement;

import state.agent.Agent;

/**
 * Allows an agent to move to a specified target agent using the shortest path.
 * @author David Miron
 */
public class MoveToAgentPRM extends MovementAction {

    Agent baseAgent;

    public MoveToAgentPRM(Agent baseAgent, int speed) {
        this.speed = speed;
        this.baseAgent = baseAgent;
    }

    /**
     * Move the baseAgent to the specified agent using the shortest path.
     * @param agent The agent to move to.
     */
    @Override
    public void execute(Agent agent) {

    }
}
