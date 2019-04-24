package state;

import authoring.IAgentDefinition;
import engine.Level;
import authoring.ILevelDefinition;
import authoring.IStateDefinition;
import state.agent.Agent;
import state.objective.IObjective;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface to represent the game as the authoring environment should see it
 * @author Jorge Raad
 * @author David Miron
 * @auhor Jamie Palka
 */
public class State implements IStateDefinition, Serializable {
    private static final int START_LEVEL = 0;
    private List<Level> levels;
    private int currentLevel;
    private List<IObjective> currentObjectives;
    private boolean gameOver;

    public State(){
        this.currentLevel = START_LEVEL;
        this.levels = new ArrayList<>();
        this.currentObjectives = new ArrayList<>();
        this.gameOver = false;
    }

    /**
     * The list of current levels levels
     * @return The levels
     */
    @Override
    public List<? extends ILevelDefinition> getLevels() {
        return this.levels;
    }

    /**
     * Remove a level
     * @param index The index of the level
     */
    @Override
    public void removeLevel(int index) {
        if (levels.size() > index) {
            levels.remove(index);
        }
    }

    /**
     * Add a level to the current list of levels. This level would be the last to be played.
     * @param level
     */
    @Override
    public void addLevel(ILevelDefinition level) {
        levels.add((Level)level);
    }

    /**
     * Add an objective to the current list of objectives.
     * @param objective the objective to be added
     */
    public void defineObjective(IObjective objective) {
        currentObjectives.add(objective);
    }

    /**
     * For Player - Iterate through and update your copy based on the corresponding ID.
     */
    public Iterable<IObjective> getImmutableObjectives() {
        return List.copyOf(this.currentObjectives);
    }

    /**
     * Switches the current level being played to the next level. Currently the order in which the levels of a game are
     * played are simply determined by their order within the levels list.
     */
    public void nextLevel(){
        if (levels.get(currentLevel) != levels.get(levels.size() - 1)){
            currentLevel++;
        }
    }

    /**
     * Executes any outcomes necessary for existing objectives and then calls step() on the current level.
     * @param deltaTime the change in time
     */
    public void step(double deltaTime){

        for(IObjective objective : currentObjectives) {
            objective.execute(this);
        }

        levels.get(currentLevel).step(deltaTime);
    }

    /**
     * @return level the current level
     */
    public Level getCurrentLevel() {
        return levels.get(currentLevel);
    }

    /**
     * Gives the Game the status of the state regarding gameOver.
     * @return a boolean value representing if the game is over or not
     */
    public boolean getGameOverStatus() {

        return gameOver;
    }

    /**
     * Changes the value of gameOver to true.
     */
    public void makeGameOver() {
        gameOver = true;
    }

    /**
     * Returns the list of agents that exist in the current level.
     */
    public List<? extends IAgentDefinition> getCurrentAgents() {
        return levels.get(currentLevel).getCurrentAgents();
    }
}
