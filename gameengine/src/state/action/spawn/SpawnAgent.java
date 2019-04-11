package state.action.spawn;

import engine.event.events.AddAgentEvent;
import state.action.Action;
import state.agent.Agent;

/**
 * Class to provide functionality of spawning an agent.
 * @author David Miron
 */
public abstract class SpawnAgent extends Action {

    protected void spawnAgent(Agent agent) {

        eventMaster.triggerAddAgentEvent(new AddAgentEvent(agent));
    }

}
