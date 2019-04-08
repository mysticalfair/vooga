package state.condition.previous_to_remove;

import state.action.IAction;
import state.agent.IAgent;

import java.util.List;

/**
 * ActionDecision that executes its Action whenever it collides with an enemy.
 * Special case of OnCollision where only enemy agents are considered.
 * @author Jorge Raad
 */
public class OnEnemyCollision extends OnCollision{

    public OnEnemyCollision(IAgent baseAgent, IAction action){
        super(baseAgent, action);
    }

    @Override
    public void execute(List<IAgent> agents) {
        List<IAgent> enemyAgents = agents;
        enemyAgents.removeAll(getAgentsOnTeam(baseAgent.getTeam(), agents));
        // enemyAgents now contains only Agents on teams different from the base Agent's
        super.execute(enemyAgents);
    }
}
