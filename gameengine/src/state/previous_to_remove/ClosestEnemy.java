package state.previous_to_remove;

import state.action.IAction;
import state.agent.IAgent;

import java.util.List;

/**
 * ActionDecision to run an action on the closest enemy.
 * Specific case of ClosestAgent where the agents considered are just the enemies.
 * @author Jorge Raad
 */
public class ClosestEnemy extends ClosestAgent {

    /**
     * Create ActionDecision that will run action on closest agent to some base agent
     * @param baseAgent The agent to test distance from
     * @param action The action to run
     */
    public ClosestEnemy(IAgent baseAgent, IAction action) {
        super(baseAgent, action);
    }

    @Override
    public void execute(List<IAgent> agents) {
        // TODO: Decide what to do if there are no enemies. Likely within the action, not here.
        List<IAgent> enemyAgents = agents;
        enemyAgents.removeAll(getAgentsOnTeam(baseAgent.getTeam(), agents));
        // enemyAgents now contains only Agents on teams different from the base Agent's
        super.execute(enemyAgents);
    }
}
