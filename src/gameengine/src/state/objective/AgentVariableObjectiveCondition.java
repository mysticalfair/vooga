package state.objective;

import state.State;
import state.agent.Agent;

import java.util.Map;

/**
 * @author Jamie Palka
 * Abstract class to define the objectives within the game which are triggered by the value of a variable of an agent
 * (x value, y value, or direction)
 */
abstract public class AgentVariableObjectiveCondition extends ObjectiveCondition {

    protected String variableName;
    protected double targetValue;
    protected Agent agent;
    protected double variableValue;

    public AgentVariableObjectiveCondition(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {

        this.variableName = (String) params.get("variableName");
        this.targetValue = (double) params.get("targetValue");
        this.agent = (Agent) params.get("agent");
        super.setParams(params);

        if(variableName.equals("x")) {
            variableValue = agent.getX();
        }

        if(variableName.equals("y")) {
            variableValue = agent.getY();
        }

        if(variableName.equals("direction")) {
            variableValue = agent.getDirection();
        }
    }

    /**
     * Executes the outcome the agent has the correct condition for the targetValue
     */
    abstract public boolean evaluate(State state);
    //TODO use reflection & a lambda expression rather than if statements - makes more extendable
}
