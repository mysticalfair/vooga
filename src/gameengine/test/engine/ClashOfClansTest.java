package engine;

import authoring.*;
import authoring.exception.ActionDoesNotExistException;
import authoring.exception.ConditionDoesNotExistException;
import authoring.exception.ReflectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import state.Property;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClashOfClansTest {

    public static final String CLASH_FILE = "ClashTest1.xml";
    IGameDefinition gameEngine;
    IStateDefinition state;
    GameFactory factory;

    @BeforeEach
    void setUp() {
        try {
            factory = new GameFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * Creates a tower that spins and spawns projectiles.
     */
    @Test
    void gameOne() {
        try {
            factory = new GameFactory();
            gameEngine = factory.createGame();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();

            state.addDefinedAgent(createTownHall("home"));

            level.addAgent("peashooter", 200, 200, 0, new ArrayList<Property>());

            state.addLevel(level);
            gameEngine.setState(state);
            gameEngine.saveState(CLASH_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private IAgentDefinition createTownHall(String name) throws ConditionDoesNotExistException, ReflectionException, ActionDoesNotExistException {
        Map<String, Object> condParams = new HashMap<>();
        condParams.put("property", "health");
        condParams.put("value", 0.0);
        Map<String, Object> actionParams = new HashMap<>();
        Map<String, Object> action2Params = new HashMap<>();
        action2Params.put("propertyName", "health");
        action2Params.put("value", 5.0);
        List<IActionDecisionDefinition> actionDecisions = new ArrayList<>();
        List<IConditionDefinition> conditionDefinitions = new ArrayList<>();
        List<IConditionDefinition> cond1 = new ArrayList<>();
        Map cond2Params = new HashMap();
        cond2Params.put("interval", 5.0);
        cond1.add(factory.createCondition("DoOnce", new HashMap()));
        cond1.add(factory.createCondition("Interval", cond2Params));
        conditionDefinitions.add(factory.createCondition("DoOnce", new HashMap<>()));
        conditionDefinitions.add(factory.createCondition("PropertyLessThanOrEqualTo", condParams));
        actionDecisions.add(factory.createActionDecision(
                factory.createAction("DestroyAgent", actionParams), conditionDefinitions));
        actionDecisions.add(factory.createActionDecision(factory.createAction("DecrementProperty", action2Params), cond1));
        List<IPropertyDefinition> properties = new ArrayList<>();
        var prop = factory.createProperty("health", 10.0);
        properties.add(prop);
        return factory.createAgent(50, 50, 10, 10 ,1, "Luke", "clash/ArcherQueen.png", actionDecisions, properties);
    }

}
