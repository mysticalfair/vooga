package state.condition;

import state.action.IAction;
import state.agent.IAgent;

import java.util.List;

/**
 * ActionDecision that executes its Action whenever it collides with an enemy.
 * Special case of OnCollision where only agents on the same team as the baseAgent are considered.
 * @author Jorge Raad
 */
public class OnTeamCollision extends OnCollision {

    public OnTeamCollision(IAgent baseAgent, IAction action){
        super(baseAgent, action);
    }

    @Override
    public void execute(List<IAgent> agents) {
        super.execute(getAgentsOnTeam(baseAgent.getTeam(), agents));
    }
}
