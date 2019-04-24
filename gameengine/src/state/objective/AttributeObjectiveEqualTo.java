package state.objective;

import state.State;
import state.attribute.Attribute;

public class AttributeObjectiveEqualTo extends AttributeObjective {

    public AttributeObjectiveEqualTo(int id, String title, Attribute attribute, int targetValue, IObjectiveOutcome outcome) {
        super(id, title, attribute, targetValue, outcome);
    }

    /**
     * If given attribute has a value equal to the target value, execute the outcome.
     */
    public void execute(State state) {

        if(attribute.getValue() == targetValue) {
            outcome.execute(state);
        }
    }
}
