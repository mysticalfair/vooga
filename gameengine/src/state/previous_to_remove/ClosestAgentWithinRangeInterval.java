package state.previous_to_remove;

import state.action.IAction;
import state.agent.IAgent;

import java.util.List;

/**
 * ActionDecision to run an action on the closest agent within a range, within some interval
 * @author David Miron
 * @author Jorge Raad
 */
public class ClosestAgentWithinRangeInterval extends IntervalDecision {

    private int range;

    /**
     * Create ActionDecision that will run action on closest agent to some base agent, within some range,
     * using the time interval
     * @param baseAgent The agent to test distance from
     * @param interval Minimum time that must occur between action calls
     * @param range Range to use
     * @param action The action to run
     */
    public ClosestAgentWithinRangeInterval(IAgent baseAgent, double interval, int range, IAction action) {
        super(interval, action);
        this.baseAgent = baseAgent;
        this.range = range;
    }

    /**
     * Call an action on the closest agent within some range
     * @param agents The list of other agents in play
     */
    @Override
    public void execute(List<IAgent> agents) {
        IAgent closestAgent = getClosestAgentWithinRange(agents);
        if (intervalHasPassed() && closestAgent != null) {
            action.execute(closestAgent);
            resetInterval();
        }
    }

    /**
     * Find the closest agent to the base agent
     * @param agents List of agents to compare
     * @return The closest Agent, or null if there are no agents within a range
     */
    protected IAgent getClosestAgentWithinRange(List<IAgent> agents) {
        if (agents.size() < 1) {
            return null;
        }
        IAgent closest = agents.get(0);
        for (IAgent agent : agents) {
            if (baseAgent.calculateDistance(agent) < baseAgent.calculateDistance(closest)) {
                closest = agent;
            }
        }
        if (baseAgent.calculateDistance(closest) >= range) {
            return closest;
        }
        return null;
    }
}
