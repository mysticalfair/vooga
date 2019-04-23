package state.objective;


import state.State;
import state.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamie Palka
 * Class to define the objectives within the game which are triggered by the value of an agent property.
 */
public class AgentPropertyObjective {

    private int id;
    private String title;
    private String propertyName;
    private int targetValue;
    private IObjectiveOutcome outcome;
    private List<Agent> agents;
    //TODO add player for which want to check attribute
    //TODO add another constructor which also takes a specific level that the objective corresponds to

    public AgentPropertyObjective(int id, String title, String propertyName, int targetValue, IObjectiveOutcome outcome) {
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

        for(Agent agent : agents)

            if(agent.)
            outcome.execute(state);
        }

    }

}
