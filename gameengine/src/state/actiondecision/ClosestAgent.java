package state.actiondecision;

import state.agent.IAgent;

import java.util.List;

public class ClosestAgent extends ActionDecision{

    @Override
    public void execute(List<IAgent> agents) {
        // TODO: Decide what to do if there are no agents.
        IAgent closestAgent = getClosestAgent(agents);
        action.execute(closestAgent);
    }

    // TODO: Resolve duplicate code. Unable to use inheritance to avoid repeated code because of distinction between
    //  interval and non-interval ActionDecision.
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
