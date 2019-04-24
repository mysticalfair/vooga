package state.objective;

import state.State;
import state.agent.Agent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.*;

/**
 * @author Jamie Palka
 * Class to define the objectives within the game which are triggered by the value of a variable of an agent
 * (x value, y value, or direction)
 */
abstract public class AgentVariableObjective implements IObjective, Serializable {

    protected int id;
    protected String title;
    protected String variableName;
    protected double targetValue;
    protected IObjectiveOutcome outcome;
    protected List<Agent> agents;
    //TODO add another constructor which also takes a specific level that the objective corresponds to
    //TODO make this a map, so can track different targetValues for different agents
    //TODO use reflection & a lambda expression rather than if statements - makes more extendable

    public AgentVariableObjective(int id, String title, String variableName, double targetValue, IObjectiveOutcome outcome) {
        this.id = id;
        this.title = title;
        this.variableName = variableName;
        this.targetValue = targetValue;
        this.outcome = outcome;
        agents = new ArrayList<Agent>();
    }

    /**
     * Adds an agent to the list of agents for which to check the variable value.
     * @param agent the agent to add to the list
     */
    public void addAgent(Agent agent) {
        agents.add(agent);
    }

    /**
     * Executes the outcome if every agent in agents has the correct condition for the targetValue
     */
    abstract public void execute(State state);

}
