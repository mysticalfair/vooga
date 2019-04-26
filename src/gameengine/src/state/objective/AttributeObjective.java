package state.objective;

import org.w3c.dom.Attr;
import state.State;
import state.attribute.Attribute;

import java.io.Serializable;

/**
 * @author Jamie Palka
 * Abstract class to define the objectives within the game which are triggered by the value of an attribute of a user.
 */
abstract public class AttributeObjective implements IObjective, Serializable {

    protected int id;
    protected String title;
    protected Attribute attribute;
    protected int targetValue;
    protected IObjectiveOutcome outcome;
    protected int level;
    //TODO add player for which want to check attribute

    //TODO okay that need an Attribute object? Where check if Attribute even exists in list of current Attributes?
    public AttributeObjective(int id, String title, Attribute attribute, int targetValue,
                              IObjectiveOutcome outcome, int level) {
        this.id = id;
        this.title = title;
        this.attribute = attribute;
        this.targetValue = targetValue;
        this.outcome = outcome;
        this.level = level;
    }

    /**
     * Abstract method which will define the conditions necessary regarding the value of the attribute prior to
     * executing the ObjectiveOutcome
     */
    abstract public void execute(State state);
}
