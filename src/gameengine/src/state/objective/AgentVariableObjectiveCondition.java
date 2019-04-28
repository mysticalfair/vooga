package state.objective;

import state.State;
import state.agent.Agent;

import java.util.Map;

/**
 * @author Jamie Palka
 * Abstract class to define the ObjectiveConditions within the game which are triggered by the value of a variable of an agent
 * (x value, y value, or direction)
 */
abstract public class AgentVariableObjectiveCondition extends ObjectiveCondition {

    protected String variableName;
    protected double targetValue;
    protected Agent agent;
    protected double variableValue;
    protected int objectiveIdentificationPropertyValue;

    public AgentVariableObjectiveCondition(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {

        this.variableName = (String) params.get("variableName");
        this.targetValue = (double) params.get("targetPropertyValue");
        this.objectiveIdentificationPropertyValue = (Integer) params.get(OBJECTIVE_IDENTIFICATION_PROPERTY_PARAMS);
        super.setParams(params);
    }

    protected void setVariableValue(Agent agent) {

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
     * Returns true if the agent has the correct condition for the targetPropertyValue
     */
    abstract public boolean evaluate(State state);
    //TODO use reflection & a lambda expression rather than if statements - makes more extendable
}
