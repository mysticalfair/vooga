package state.condition;


import state.agent.IAgent;

/**
 * Abstract class to represent some condition for a group of agents that requires the injection of a base agent.
 * @author Jorge Raad
 */
public abstract class BaseAgentCondition extends Condition {
    protected IAgent baseAgent;
}
