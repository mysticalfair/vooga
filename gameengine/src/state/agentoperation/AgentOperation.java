package state.agentoperation;

import state.agent.IAgent;

/**
 * Class to hold an operation to be carried out on an agent
 * @author David Miron
 */
public abstract class AgentOperation {

    /**
     * Do some operation on an agent
     * @param agent The agent on which to operate
     */
    public abstract void operateOn(IAgent agent);

}
