package state.objective.objectivecondition;

import state.State;
import state.attribute.Attribute;

import java.util.Map;

/**
 * @author Jamie Palka
 * Abstract class to define the ObjectiveConditions within the game which are triggered by the value of an attribute of a user.
 */
abstract public class AttributeObjectiveCondition extends ObjectiveCondition {

    protected Attribute attribute;
    protected int targetValue;
    protected String attributeName;

    public AttributeObjectiveCondition(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {

        this.attributeName = (String) params.get("attributeName");
        this.targetValue = (Integer) params.get("targetPropertyValue");
        super.setParams(params);
    }

    /**
     * Abstract method which will define the conditions necessary regarding the value of the attribute.
     */
    abstract public boolean evaluate(State state);
}
