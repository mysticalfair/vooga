package state.objective;

import state.State;

import java.io.Serializable;

/**
 * @author Jamie Palka
 * Class to define the objectives within the a game which incite a change in the game flow.
 */
public class attributeObjective implements IObjective, Serializable {

    private int id;
    private String title;

    public attributeObjective(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() { return this.id; }

    public String getTitle() {
        return this.title;
    }

    public boolean checkObjective(State state) {

        return true;
    }

    public void execute(State state) {

    }
}
