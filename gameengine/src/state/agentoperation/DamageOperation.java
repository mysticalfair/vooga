package state.agentoperation;

import state.agent.IAgent;

/**
 * Subclass of AgentOperation that does damage to an agent
 * @author David Miron
 * @author Jamie Palka
 */
public class DamageOperation extends AgentOperation {

    private int damage;

    public DamageOperation(int damage) {
        this.damage = damage;
    }

    /**
     * Do damage to an agent
     * @param agent The agent on which to operate
     */
    @Override
    public void operateOn(IAgent agent) {
        agent.loseHealth(damage);
    }
}
