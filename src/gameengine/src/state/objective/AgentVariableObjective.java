package state.objective;

import state.State;
import state.agent.Agent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.*;

/**
 * @author Jamie Palka
 * Abstract class to define the objectives within the game which are triggered by the value of a variable of an agent
 * (x value, y value, or direction)
 */
abstract public class AgentVariableObjective implements IObjective, Serializable {

    protected int id;
    protected String title;
    protected String variableName;
    protected double targetValue;
    protected IObjectiveOutcome outcome;
    protected Agent agent;
    protected double variableValue;
    protected int level;
    //TODO possible extension - make the agent variable a map of agents so can track different
    // targetValues for different agents


    //TODO ok that takes in an agent or want an agent ID? Where check if agent exists?
    public AgentVariableObjective(int id, String title, String variableName, Agent agent,
                                  double targetValue, IObjectiveOutcome outcome, int level) {
        this.id = id;
        this.title = title;
        this.variableName = variableName;
        this.targetValue = targetValue;
        this.outcome = outcome;
        this.agent = agent;
        this.level = level;

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
    abstract public void execute(State state);
    //TODO use reflection & a lambda expression rather than if statements - makes more extendable

}
