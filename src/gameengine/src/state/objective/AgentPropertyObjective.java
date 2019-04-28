package state.objective;


import engine.Level;
import state.State;
import state.agent.Agent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Jamie Palka
 * Abstract class to define the objectives within the game which are triggered by the value of an agent property.
 */
abstract public class AgentPropertyObjective<T> extends Objective {

    protected String propertyName;
    protected T targetValue;
    protected Agent agent;

    public AgentPropertyObjective(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {

        this.propertyName = (String) params.get("propertyName");
        this.agent = (Agent) params.get("agent");
        this.targetValue = (T) params.get("targetValue");
        super.setParams(params);
    }

    /**
     * Executes the outcome if the given agent has the correct condition for the targetValue.
     */
    abstract public void execute(State state);

}
