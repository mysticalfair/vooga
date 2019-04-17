package state.action.movement;

import state.agent.Agent;

import java.util.Map;

/**
 * Allows an agent to move to a specified target agent using the shortest path.
 * @author David Miron
 */
public class MoveToAgentPRM extends MovementAction {

    Agent baseAgent;

    public MoveToAgentPRM(Map<String, ? extends Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, ? extends Object> params) {
        // TODO this
    }

    /**
     * Move the baseAgent to the specified agent using the shortest path.
     * @param agent The agent to move to.
     */
    @Override
    public void execute(Agent agent, double deltaTime) {

    }
}
