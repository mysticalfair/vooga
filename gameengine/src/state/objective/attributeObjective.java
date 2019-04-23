package state.objective;

import org.w3c.dom.Attr;
import state.State;
import state.attribute.Attribute;

import java.io.Serializable;

/**
 * @author Jamie Palka
 * Class to define the objectives within the a game which incite a change in the game flow.
 */
public class attributeObjective implements IObjective, Serializable {

    private int id;
    private String title;
    private Attribute attribute;
    private int targetValue;
    private IObjectiveOutcome outcome;
    //TODO add player for which want to check attribute
    //TODO add another constructor which also takes a specific level that the objective corresponds to

    public attributeObjective(int id, String title, Attribute attribute, int targetValue, IObjectiveOutcome outcome) {
        this.id = id;
        this.title = title;
        this.attribute = attribute;
        this.targetValue = targetValue;
        this.outcome = outcome;
    }

    public void execute(State state) {
        

    }
}
