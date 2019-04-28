package state.objective;

import state.State;
import state.agent.Agent;

import java.util.Map;

/**
 * @author Jamie Palka
 * Class to define the functionality of removing an agent.
 */
public class RemoveAgentOutcome extends ObjectiveOutcome {

    private Agent agent;

    public RemoveAgentOutcome(Map<String, Object> params) { super(params); }

    @Override
    public void setParams(Map<String, Object> params) {
        this.agent = (Agent) params.get("agent");
    }

    public String execute(State state) {
        state.getCurrentLevel().removeAgent(agent);
        return null;
    }

}
