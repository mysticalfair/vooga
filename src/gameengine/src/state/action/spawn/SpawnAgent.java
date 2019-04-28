package state.action.spawn;

import engine.event.events.AddAgentEvent;
import state.AgentReference;
import state.action.Action;
import state.agent.Agent;

import java.util.Map;

/**
 * Class to provide functionality of spawning an agent.
 * @author David Miron
 */
public abstract class SpawnAgent extends Action {

    public SpawnAgent(Map<String, Object> params) {
        super(params);
    }

    protected void spawnAgent(AgentReference agent) {
        eventMaster.triggerAddAgentEvent(new AddAgentEvent(agent));
    }

}
