package state.condition;

import state.action.IAction;
import state.agent.IAgent;

import java.util.List;

/**
 * ActionDecision to run an action on the closest enemy within a range, within some interval.
 * A specific case of ClosestAgentWithinRangeInterval where only enemies are taken into account, rather than all agents.
 * @author Jorge Raad
 */
public class ClosestEnemyWithinRangeInterval extends ClosestAgentWithinRangeInterval {

    /**
     * @see ClosestAgentWithinRangeInterval#ClosestAgentWithinRangeInterval(IAgent, double, int, IAction)
     */
    public ClosestEnemyWithinRangeInterval(IAgent baseAgent, double interval, int range, IAction action) {
        super(baseAgent, interval, range, action);
    }

    /**
     * Call an action on the closest enemy within some range
     * @param agents The list of other agents in play
     */
    @Override
    public void execute(List<IAgent> agents) {
        List<IAgent> enemyAgents = agents;
        enemyAgents.removeAll(getAgentsOnTeam(baseAgent.getTeam(), agents));
        // enemyAgents now contains only Agents on teams different from the base Agent's
        IAgent closestAgent = getClosestAgentWithinRange(enemyAgents);
        if (intervalHasPassed() && closestAgent != null) {
            action.execute(closestAgent);
            resetInterval();
        }
    }
}
