package state.objective;

import state.State;

import java.util.Map;

/**
 * @author Jamie Palka
 * Class to define the ObjectiveConditions within the game which are triggered by the value of an attribute of a user
 * being less than or equal to a target value.
 */
public class AttributeObjectiveConditionLessThan extends AttributeObjectiveCondition {

    public AttributeObjectiveConditionLessThan(Map<String, Object> params) {
        super(params);
    }

    /**
     * Returns true if the given attribute has a value less than or equal to the target value.
     */
    public boolean evaluate(State state) {

        return ((state.getCurrentLevelInt() == level || level == -1) && attribute.getValue() <= targetValue);
    }
}
