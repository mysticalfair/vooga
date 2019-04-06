package state.action;

import state.agent.IAgent;
import state.agentoperation.AgentOperation;

/**
 * Action that applies a specified AgentOperation on an agent
 */
public class ApplyAgentOperationAction extends Action {

    private AgentOperation operation;

    /**
     * Create an ApplyAgentOperation Action
     * @param operation The operation to run on an agent
     */
    public ApplyAgentOperationAction(AgentOperation operation) {
        this.operation = operation;
    }

    @Override
    public void execute(IAgent agent) {
        operation.operateOn(agent);
    }
}
