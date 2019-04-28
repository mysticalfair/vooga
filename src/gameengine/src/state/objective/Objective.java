package state.objective;

import state.State;

import java.util.Map;

/**
 * @author Jamie Palka
 * An abstract class to define the common functionality of Objectives.
 */
public abstract class Objective {

    protected Map<String, Object> params;
    protected int id;
    protected String title;
    protected IObjectiveOutcome outcome;
    protected int level;

    public Objective(Map<String, Object> params) {
        this.params = params;
        setParams(params);
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    public void setParams(Map<String, Object> params) {
        // Does nothing -> this method should be overridden by subclasses that need parameters
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }

    public IObjectiveOutcome getOutcome() { return this.outcome; }
    public void setOutcome(IObjectiveOutcome outcome) { this.outcome = outcome; }

    /**
     * Executes the outcome if the given agent has the correct condition for the targetValue.
     */
    abstract public void execute(State state);
}
