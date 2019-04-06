package state.actiondecision;

import state.agent.IAgent;

import java.util.List;

/**
 * ActionDecision that executes its Action whenever it collides with an enemy.
 * Special case of OnCollision where only agents on the same team as the baseAgent are considered.
 * @author Jorge Raad
 */
public class OnTeamCollision extends OnCollision {
    @Override
    public void execute(List<IAgent> agents) {
        super.execute(getAgentsOnTeam(baseAgent.getTeam(), agents));
    }
}
