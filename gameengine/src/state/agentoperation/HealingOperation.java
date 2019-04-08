package state.agentoperation;

import state.agent.IAgent;

/**
 * Subclass of AgentOperation that heals an agent
 * @author Jamie Palka
 */
public class HealingOperation extends AgentOperation {

    private int healing;

    public HealingOperation(int healing) {
        this.healing = healing;
    }

    /**
     * Heal an agent using the value of healing.
     * @param agent The agent on which to operate
     */
    @Override
    public void operateOn(IAgent agent) {
        agent.gainHealth(healing);
    }
}
