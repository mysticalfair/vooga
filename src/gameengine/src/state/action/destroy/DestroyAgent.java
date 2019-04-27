package state.action.destroy;

import engine.event.events.RemoveAgentEvent;
import state.IRequiresBaseAgent;
import state.action.Action;
import state.agent.Agent;

import java.util.Map;

/**
 * Anything involving removing an agent
 * @Author:Luke_Truitt
 */
public abstract class DestroyAgent extends Action implements IRequiresBaseAgent {

    private Agent baseAgent;

    public DestroyAgent(Map<String, Object> params) {
        super(params);
    }

    protected void destroyAgent() {
        eventMaster.triggerRemoveAgentEvent(new RemoveAgentEvent(this.baseAgent));
    }
}
