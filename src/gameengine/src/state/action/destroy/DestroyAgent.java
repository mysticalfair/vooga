package state.action.destroy;

import engine.event.events.RemoveAgentEvent;
import state.IRequiresBaseAgent;
import state.action.Action;
import state.agent.Agent;

import java.util.Map;

/**
 * @author:Luke_Truitt
 */
public class DestroyAgent extends Action implements IRequiresBaseAgent {

    private Agent baseAgent;

    public DestroyAgent(Map<String, Object> params) {
        super(params);
    }

    protected void destroyAgent() {
        eventMaster.triggerRemoveAgentEvent(new RemoveAgentEvent(this.baseAgent));
    }

    @Override
    public void setParams(Map<String, Object> params) {
    }

    @Override
    public void injectBaseAgent(Agent agent) {
        this.baseAgent = agent;
    }

    /**
     * Spawn the spawnAgent at the location of the baseAgent. Uses clone method, which allows the new agent to take on all
     * properties of the pre-defined spawnAgent that an instantiation of a SpawnAgentInitialDirection action will own.
     * @param agent The spawnAgent will be given a movement with the destination of this agent.
     */
    @Override
    public void execute(Agent agent, double deltaTime) throws CloneNotSupportedException {
        this.destroyAgent();
    }
}
