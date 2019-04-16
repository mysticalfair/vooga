package state.condition;


import state.agent.Agent;

import java.util.Map;

/**
 * Abstract class to represent some condition for a group of agents that requires the injection of a base agent.
 * @author Jorge Raad
 */
public abstract class BaseAgentCondition extends Condition {

    protected Agent baseAgent;

    public BaseAgentCondition(Map<String, ? extends Object> params) {
        super(params);
    }

    public void injectBaseAgent(Agent agent) {
        this.baseAgent = agent;
    }
}
