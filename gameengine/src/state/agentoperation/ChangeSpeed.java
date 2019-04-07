package state.agentoperation;

import state.agent.IAgent;

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

        // TODO speed of WHICH ActionDecision/Action pair?? Again, we need to somehow define a current or preferred action
        //  or something. This issue came up with spawning agents as well.

    }
}
