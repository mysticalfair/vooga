package state.actiondecision;

import state.action.IAction;
import state.agent.IAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to decide when to do an action, and execute that action if necessary
 * @author David Miron
 */
public abstract class ActionDecision {

    protected IAgent baseAgent;
    protected IAction action;

    /**
     * Run an action if some condition is true
     * @param agents All other agents in play
     */
    public abstract void execute(List<IAgent> agents);

    protected List<IAgent> getAgentsOnTeam(String team, List<IAgent> allAgents){
        List<IAgent> teamAgents = new ArrayList<>();
        for(IAgent agent : allAgents){
            if(!agent.getTeam().equals(team)){
                teamAgents.add(agent);
            }
        }
        return teamAgents;
    }
}
