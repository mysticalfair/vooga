package state.agentoperation;

import state.agent.IAgent;

/**
 * Subclass of AgentOperation that changes the speed of an agent.
 * @author Jamie Palka
 */
public class ChangeSpeed extends AgentOperation {

    private double newXVelocity;
    private double newYVelocity;

    public ChangeSpeed(double newXVelocity, double newYVelocity) {

        this.newXVelocity = newXVelocity;
        this.newYVelocity = newYVelocity;
    }

    /**
     * Change the speed of an agent to speedChange
     * @param agent The agent on which to operate
     */
    @Override
    public void operateOn(IAgent agent) {
        agent.updateVelocity(newXVelocity, newYVelocity);
    }
}
