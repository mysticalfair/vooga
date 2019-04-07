package state.action;

import state.agent.IAgent;
import state.agentoperation.AgentOperation;

/**
 * Action that applies a specified AgentOperation on an agent.
 * @author David Miron
 * @author Jamie Palka
 */
public class ApplyAgentOperation extends Action {

    private AgentOperation operation;

    /**
     * Create an ApplyAgentOperation Action.
     * @param operation The operation to run on an agent.
     */
    public ApplyAgentOperation(AgentOperation operation) {
        this.operation = operation;
    }

    /**
     * Execute the specified operation on the agent.
     * @param agent The agent to apply the AgentOperation on.
     */
    @Override
    public void execute(IAgent agent) {
        operation.operateOn(agent);
    }
}
