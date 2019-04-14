package gameengine;

import engine.Game;
import engine.Level;
import engine.event.GameEventMaster;

import java.util.List;

/**
 * Factory to create everything needed to define and save a game
 * @author David Miron
 */
public class GameFactory {

    private GameEventMaster eventMaster;

    public GameFactory() {
        this.eventMaster = new GameEventMaster();
    }

    /**
     * Create a default game, with no agents or objectives
     * @return A default game
     */
    public IGameDefinition createGame() {
        return new Game();
    }

    /**
     * Create a default level, with no agents defined
     * @return A default level
     */
    public ILevelDefinition createLevel() {
        Level level = new Level();
        level.injectGameEventMaster(eventMaster);
        return level;
    }

    /**
     * Create an agent with a list of action decisions and properties
     * @param actionDecisions The action decisions
     * @param properties The properties
     * @return The new agent
     */
    public IAgentDefinition createAgent(List<IActionDecisionDefinition> actionDecisions,
                                        List<IPropertyDefinition> properties) {
        return null;
    }

    /**
     * Create an action decision with an action and list of conditions
     * @param action The action
     * @param conditions The conditions
     * @return The new action decision
     */
    public IActionDecisionDefinition createActionDecision(IActionDefinition action,
                                                          List<IConditionDefinition> conditions) {
        return null;
    }

    /**
     * Create an action
     * @param name The name of the action, specified in xml file
     * @param params The parameters of that action, should match fields in xml file
     * @return The action object
     */
    public IActionDefinition createAction(String name, Object ... params) {
        return null;
    }

    /**
     * Create a condition
     * @param name The name of the condition, specified in xml file
     * @param params The parameters of that condition, should match fields in xml file
     * @return The condition object
     */
    public IConditionDefinition createCondition(String name, Object ... params) {
        return null;
    }

    /**
     * Create a property
     * @param name The name of the property
     * @param value The value of the property
     * @param <T> The type of the property
     * @return The property
     */
    public <T> IPropertyDefinition createProperty(String name, T value) {
        return null;
    }

}
