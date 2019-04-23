package state.objective;


import state.State;
import state.agent.Agent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamie Palka
 * Class to define the objectives within the game which are triggered by the value of an agent property.
 */
public class AgentPropertyObjective<T> implements IObjective, Serializable {

    private int id;
    private String title;
    private String propertyName;
    private T targetValue;
    private IObjectiveOutcome outcome;
    private List<Agent> agents;
    //TODO add another constructor which also takes a specific level that the objective corresponds to
    //TODO make this a map, so can track different targetValues for different agents

    public AgentPropertyObjective(int id, String title, String propertyName, T targetValue, IObjectiveOutcome outcome) {
        this.id = id;
        this.title = title;
        this.propertyName = propertyName;
        this.targetValue = targetValue;
        this.outcome = outcome;
        agents = new ArrayList<>();
    }

    /**
     * Adds an agent to the list of agents for which to check the property value.
     * @param agent the agent to add to the list
     */
    public void addAgent(Agent agent) {
        agents.add(agent);
    }

    /**
     * Executes the outcome if every agent in agents has the targetValue for the given property.
     */
    public void execute(State state) {

        for(Agent agent : agents) {

            if(!agent.getPropertyValue(title).equals(targetValue)) {
                return;
            }

            outcome.execute(state);
        }
    }

}
