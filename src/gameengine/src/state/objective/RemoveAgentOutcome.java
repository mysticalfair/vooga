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
    protected String objectiveIdentificationPropertyValue;

    public RemoveAgentOutcome(Map<String, Object> params) { super(params); }

    @Override
    public void setParams(Map<String, Object> params) {

        this.objectiveIdentificationPropertyValue = (String) params.get(OBJECTIVE_IDENTIFICATION_PROPERTY_PARAMS);
    }

    public String execute(State state) {
        agent = ObjectiveUtils.getAgentFromObjectiveIdentificationPropertyValue(state, objectiveIdentificationPropertyValue);
        state.getCurrentLevel().removeAgent(agent);
        return null;
    }

}
