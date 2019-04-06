package state.actiondecision;

import state.agent.IAgent;

import java.util.List;

public class OnEnemyCollision extends OnCollision{

    @Override
    public void execute(List<IAgent> agents) {
        List<IAgent> enemyAgents = agents;
        enemyAgents.removeAll(getAgentsOnTeam(baseAgent.getTeam(), agents));
        // enemyAgents now contains only Agents on teams different from the base Agent's
        super.execute(enemyAgents);
    }
}
