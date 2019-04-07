package state.agentoperation;

import state.agent.IAgent;

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
        // TODO: Complete this, something like agent.loseHealth(damage) or something
    }
}
