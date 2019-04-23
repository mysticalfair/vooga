package engine.event.events;

import engine.event.GameEvent;
import engine.event.GameEventType;
import state.agent.Agent;

/**
 * Class to represent an event to create a new agent
 * @author David Miron
 */
public class AddAgentEvent extends GameEvent {
    private Agent agent;

    /**
     * Create a AddAgentEvent
     * @param agent The agent to add
     */
    public AddAgentEvent(Agent agent) {
        this.agent = agent;
        this.type = GameEventType.REMOVE_AGENT;
    }

    /**
     * Get the agent to add
     * @return The agent to add
     */
    public Agent getAgent() {
        return this.agent;
    }
}
