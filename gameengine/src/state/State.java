package state;

import engine.Level;
import gameengine.ILevelDefinition;
import gameengine.IStateDefinition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface to represent the game as the authoring environment should see it
 * @author Jorge Raad
 * @author David Miron
 */
public class State implements IStateDefinition, Serializable {
    private static final int START_LEVEL = 1;
    private List<Level> levels;
    private int currentLevel;

    public State(){
        this.currentLevel = START_LEVEL;
        levels = new ArrayList<>();
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
     * Switches the current level being played to the next level. Currently the order in which the levels of a game are
     * played are simply determined by their order within the levels list.
     */
    private void nextLevel(){
        if (levels.get(currentLevel) != levels.get(levels.size() - 1)){
            currentLevel++;
        }
    }

    public void step(double deltaTime){
        // check "general" objectives (e.g. level change, game over, victory)
        levels.get(currentLevel).step(deltaTime);
    }

}
