package state.objective;

import state.State;
import state.attribute.Attribute;

/**
 * @author Jamie Palka
 * Class to define the objectives within the game which are triggered by the value of an attribute of a user
 * being less than or equal to a target value.
 */
public class AttributeObjectiveLessThan extends AttributeObjective {

    public AttributeObjectiveLessThan(int id, String title, Attribute attribute, int targetValue, IObjectiveOutcome outcome) {
        super(id, title, attribute, targetValue, outcome);
    }

    /**
     * If given attribute has a value less than or equal to the target value, execute the outcome.
     */
    public void execute(State state) {

        if(attribute.getValue() <= targetValue) {
            outcome.execute(state);
        }
    }
}
