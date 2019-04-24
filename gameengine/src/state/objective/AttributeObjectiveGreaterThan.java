package state.objective;

import state.State;
import state.attribute.Attribute;

public class AttributeObjectiveGreaterThan extends AttributeObjective {

    public AttributeObjectiveGreaterThan(int id, String title, Attribute attribute, int targetValue, IObjectiveOutcome outcome) {
        super(id, title, attribute, targetValue, outcome);
    }

    /**
     * If given attribute has a value greater than or equal to the target value, execute the outcome.
     */
    public void execute(State state) {

        if(attribute.getValue() >= targetValue) {
            outcome.execute(state);
        }
    }
}
