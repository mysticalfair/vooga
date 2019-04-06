package state.actiondecision;

import state.agent.IAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * ActionDecision to run an action on the closest enemy.
 * @author Jorge Raad
 */
public class ClosestEnemy extends ActionDecision {

    @Override
    public void execute(List<IAgent> agents) {
        // TODO: Decide what to do if there are no enemies. Likely within the action, not here.
        IAgent closestAgent = getClosestAgent(getEnemyAgents(agents));
        action.execute(closestAgent);
    }

    // TODO: Resolve duplicate code. Unable to use inheritance to avoid repeated code because of distinction between
    //  interval and non-interval ActionDecision.

    private List<IAgent> getEnemyAgents(List<IAgent> allAgents){
        List<IAgent> enemies = new ArrayList<>();
        for(IAgent agent : allAgents){
            if(!agent.getTeam().equals(baseAgent.getTeam())){
                enemies.add(agent);
            }
        }
        return enemies;
    }

    protected IAgent getClosestAgent(List<IAgent> agents) {
        if (agents.size() < 1) {
            return null;
        }
        IAgent closest = agents.get(0);
        for (IAgent agent : agents) {
            if (baseAgent.calculateDistance(agent) < baseAgent.calculateDistance(closest)) {
                closest = agent;
            }
        }
        return closest;
    }
}
