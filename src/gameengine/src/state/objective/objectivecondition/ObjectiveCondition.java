package state.objective.objectivecondition;

import authoring.IObjectiveConditionDefinition;
import state.State;
import state.agent.Agent;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Jamie Palka
 * An abstract class to define the common functionality of Objectives.
 */
public abstract class ObjectiveCondition implements IObjectiveConditionDefinition, Serializable {

    // name that corresponds to the value of the OBJECTIVE_IDENTIFICATION_PROPERTY in params (from authoring) that
    // the target agent has
    public static final String OBJECTIVE_IDENTIFICATION_PROPERTY_PARAMS = "objectiveIdentificationValue";

    protected Map<String, Object> params;
    protected String title;
    protected int level;

    public ObjectiveCondition(Map<String, Object> params) {
        this.params = params;
        setParams(params);
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void setParams(Map<String, Object> params) {
        this.title = (String) params.get("title");
        this.level = (Integer) params.get("level");
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() { return this.level; }
    public void setLevel(int level) { this.level = level; }

    /**
     * Checks necessary conditions and
     * @return boolean if the condition is true or not
     * @param state - current state of the game
     */
    abstract public boolean evaluate(State state);
}