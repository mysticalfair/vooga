package state.objective.objectiveoutcome;

import state.State;
import state.agent.Agent;
import state.objective.ObjectiveUtils;
import state.objective.objectiveoutcome.ObjectiveOutcome;

import java.util.Map;

/**
 * @author Jamie Palka
 * @author Luke Truitt
 * Class to define the functionality of removing an agent.
 */
public class RemoveAgentOutcome extends ObjectiveOutcome {

    private Agent agent;
    protected String objectiveIdentificationPropertyValue;

    public RemoveAgentOutcome(Map<String, Object> params) { super(params); }

    @Override
    public void setParams(Map<String, Object> params) {
        super.setParams(params);
        this.objectiveIdentificationPropertyValue = (String) params.get(OBJECTIVE_IDENTIFICATION_PROPERTY_PARAMS);
    }

    public String execute(State state) {
        agent = ObjectiveUtils.getAgentFromObjectiveIdentificationPropertyValue(state, objectiveIdentificationPropertyValue);
        state.getCurrentLevel().removeAgent(agent);
        return null;
    }

}
