package state.objective;

import org.w3c.dom.Attr;
import state.State;
import state.agent.Agent;
import state.attribute.Attribute;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Jamie Palka
 * Abstract class to define the objectives within the game which are triggered by the value of an attribute of a user.
 */
abstract public class AttributeObjective extends Objective {

    protected Attribute attribute;
    protected int targetValue;

    public AttributeObjective(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {

        this.id = (int) params.get("id");
        this.title = (String) params.get("title");
        this.outcome = (IObjectiveOutcome) params.get("outcome");
        this.level = (int) params.get("level");
        this.attribute = (Attribute) params.get("attribute");
        this.targetValue = (int) params.get("targetValue");
    }

    /**
     * Abstract method which will define the conditions necessary regarding the value of the attribute prior to
     * executing the ObjectiveOutcome
     */
    abstract public void execute(State state);
}
