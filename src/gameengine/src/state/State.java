package state;

import authoring.IAgentDefinition;
import engine.Level;
import authoring.ILevelDefinition;
import authoring.IStateDefinition;
import state.agent.Agent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface to represent the game as the authoring environment should see it
 * @author Jorge Raad
 * @author David Miron
 */
public class State implements IStateDefinition, Serializable {

    private static final int START_LEVEL = 0;
    private List<Level> levels;
    private int currentLevel;

    private List<Agent> definedAgents;

    public State(){
        this.currentLevel = START_LEVEL;
        levels = new ArrayList<>();
        this.definedAgents = new ArrayList<>();
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

    @Override
    public List<? extends IAgentDefinition> getDefinedAgents() {
        return definedAgents;
    }

    @Override
    public void removeDefinedAgent(int index) {
        definedAgents.remove(index);
    }

    @Override
    public void addDefinedAgent(IAgentDefinition agent) {
        definedAgents.add((Agent)agent);
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
        // TODO: check "general" objectives (e.g. level change, game over, victory)
        levels.get(currentLevel).step(deltaTime);
    }

}
