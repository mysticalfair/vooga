package state.objective;

import state.LevelState;
import state.agent.IPlayerAgent;

public class Objective implements IPlayerObjective {
    private int id;
    private boolean met;
    private String title;

    public Objective(int id, String title, boolean met) {
        this.id = id;
        this.title = title;
        this.met = met;
    }

    public int getId() { return this.id; }
    public String getTitle() {
        return this.title;
    }

    public boolean getMet() {
        return this.met;
    }

    public void setId(int id) {this.id = id; }
    public void setMet(boolean met) {
        this.met = met;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void execute(LevelState levelState) {
        //TODO: David
    }
}
