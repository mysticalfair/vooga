package state.agentoperation;

import state.agent.IAgent;

/**
 * Subclass of AgentOperation that changes the speed of an agent.
 * @author Jamie Palka
 */
public class ChangeSpeed extends AgentOperation {

    private int speedChange;

    public ChangeSpeed(int speedChange) {
        this.speedChange = speedChange;
    }

    /**
     * Change the speed of an agent by speedChange
     * @param agent The agent on which to operate
     */
    @Override
    public void operateOn(IAgent agent) {


    }
}
