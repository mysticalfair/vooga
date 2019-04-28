package state.objective;

import state.State;

import java.util.Map;

/**
 * @author Jamie Palka
 * Class to define the objectives within the game which are triggered by the value of an attribute of a user
 * being equal to a target value.
 */
public class AttributeObjectiveConditionEqualTo extends AttributeObjectiveCondition {

    public AttributeObjectiveConditionEqualTo(Map<String, Object> params) {
        super(params);
    }

    /**
     * If given attribute has a value equal to the target value, execute the outcome.
     */
    public boolean evaluate(State state) {

        return ((state.getCurrentLevelInt() == level || level == -1) && attribute.getValue() == targetValue);
    }
}
