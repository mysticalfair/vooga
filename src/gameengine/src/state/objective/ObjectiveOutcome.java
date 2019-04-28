package state.objective;

import authoring.IObjectiveOutcomeDefinition;
import state.State;

import java.util.Map;

/**
 * @author Jamie Palka
 * Abstract class to define functions necessary for all ObjectiveOutcomes.
 */
abstract public class ObjectiveOutcome implements IObjectiveOutcomeDefinition {

    protected Map<String, Object> params;

    public ObjectiveOutcome(Map<String, Object> params) {
        this.params = params;
        setParams(params);
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void setParams(Map<String, Object> params) {
        // ObjectiveOutcome classes that need parameters will implement this
    }

    /**
     * Executes the ObjectiveOutcome on the respective state.
     * @param state the current state which the outcome will be executed on.
     */
    abstract String execute(State state);

}
