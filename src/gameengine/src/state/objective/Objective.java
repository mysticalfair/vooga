package state.objective;

import authoring.IObjectiveDefinition;
import state.State;

import java.util.Map;

/**
 * @author Jamie Palka
 * An abstract class to define the common functionality of Objectives.
 */
public abstract class Objective implements IObjectiveDefinition {

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
        this.id = (int) params.get("id");
        this.title = (String) params.get("title");
        this.outcome = (IObjectiveOutcome) params.get("outcome");
        this.level = (int) params.get("level");
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

    public int getLevel() { return this.level; }
    public void setLevel(int level) { this.level = level; }

    /**
     * Checks necessary conditions and if applicable, executes the outcome on the given state.
     * @param state current state of game
     */
    abstract public void execute(State state);
}
