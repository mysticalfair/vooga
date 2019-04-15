package state.condition;


import state.agent.Agent;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract class to represent some condition for a group of agents
 * @author David Miron
 */
public abstract class Condition implements Serializable {

    /**
     * Filter out agents based on some condition
     * @param agents The agents to filter
     * @return A new version of the list of agents, filtered based on some condition
     */
    public abstract List<Agent> getValid(List<Agent> agents);

}
