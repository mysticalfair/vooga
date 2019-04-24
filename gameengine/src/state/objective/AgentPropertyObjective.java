package state.objective;


import state.State;
import state.agent.Agent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamie Palka
 * Abstract class to define the objectives within the game which are triggered by the value of an agent property.
 */
abstract public class AgentPropertyObjective<T> implements IObjective, Serializable {

    protected int id;
    protected String title;
    protected String propertyName;
    protected T targetValue;
    protected IObjectiveOutcome outcome;
    protected Agent agent;
    //TODO possible extension - make the agent variable a map of agents so can track different
    // targetValues for different agents


    //TODO ok that takes in an agent or want an agent ID? Where check if agent exists?
    public AgentPropertyObjective(int id, String title, String propertyName, Agent agent, T targetValue, IObjectiveOutcome outcome) {
        this.id = id;
        this.title = title;
        this.propertyName = propertyName;
        this.agent = agent;
        this.targetValue = targetValue;
        this.outcome = outcome;
    }

    /**
     * Executes the outcome if the given agent has the correct condition for the targetValue.
     */
    abstract public void execute(State state);

}
