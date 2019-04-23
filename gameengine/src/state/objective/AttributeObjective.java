package state.objective;

import org.w3c.dom.Attr;
import state.State;
import state.attribute.Attribute;

import java.io.Serializable;

/**
 * @author Jamie Palka
 * Class to define the objectives within the game which are triggered by the value of an attribute of a user.
 */
public class AttributeObjective implements IObjective, Serializable {

    private int id;
    private String title;
    private Attribute attribute;
    private int targetValue;
    private IObjectiveOutcome outcome;
    //TODO add player for which want to check attribute
    //TODO add another constructor which also takes a specific level that the objective corresponds to

    public AttributeObjective(int id, String title, Attribute attribute, int targetValue, IObjectiveOutcome outcome) {
        this.id = id;
        this.title = title;
        this.attribute = attribute;
        this.targetValue = targetValue;
        this.outcome = outcome;
    }

    public void execute(State state) {

        if(attribute.getValue() == targetValue) {
            outcome.execute(state);
        }

    }
}
