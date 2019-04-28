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

    private Attribute attribute;
    private int change;

    public ChangeAttributeOutcome(Map<String, Object> params) { super(params); }

    @Override
    public void setParams(Map<String, Object> params) {
        this.attribute = (Attribute) params.get("attribute");
        this.change = (int) params.get("change");
    }

    public String execute(State state) {
        attribute.setValue(attribute.getValue() + change);
        return null;
    }

}
