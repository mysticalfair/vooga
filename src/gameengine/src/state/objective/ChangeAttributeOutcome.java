package state.objective;

import state.State;
import state.agent.Agent;
import state.attribute.Attribute;

import java.util.Map;

/**
 * @author Jamie Palka
 * Class to define the functionality of changing a player attribute.
 */
public class ChangeAttributeOutcome extends ObjectiveOutcome {

    private String attributeName;
    private int change;
    protected String objectiveIdentificationPropertyValue;
    private Attribute attribute;

    public ChangeAttributeOutcome(Map<String, Object> params) { super(params); }

    @Override
    public void setParams(Map<String, Object> params) {
        this.attributeName = (String) params.get("attributeName");
        this.change = (Integer) params.get("change");
    }

    public String execute(State state) {
        attribute = state.get
        attribute.setValue(attribute.getValue() + change);
        return null;
    }

}
