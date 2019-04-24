package state.objective;

import state.State;
import state.attribute.Attribute;

/**
 * @author Jamie Palka
 * Class to define the functionality of changing a player attribute.
 */
public class ChangeAttributeOutcome implements IObjectiveOutcome {

    private Attribute attribute;
    private int change;

    public ChangeAttributeOutcome(Attribute attribute, int change) {
        this.attribute = attribute;
        this.change = change;
    }

    public String execute(State state) {
        attribute.setValue(attribute.getValue() + change);
        return null;
    }

}
