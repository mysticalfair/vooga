package engine;

import authoring.*;
import authoring.exception.ActionDoesNotExistException;
import authoring.exception.ConditionDoesNotExistException;
import authoring.exception.ReflectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

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

            level.addAgent("home", 300, 300, 0, new ArrayList<>());

            state.addLevel(level);
            gameEngine.setState(state);
            gameEngine.saveState(CLASH_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private IAgentDefinition createTownHall(String name) throws ConditionDoesNotExistException, ReflectionException, ActionDoesNotExistException {
        List<IActionDecisionDefinition> actionDecisions = new ArrayList<>();
        List<IPropertyDefinition> properties = new ArrayList<>();
        var prop = factory.createProperty("health", 100.0);
        properties.add(prop);
        return factory.createAgent(0, 0, 50, 50 ,1, "home", "clash/TH10.png", actionDecisions, properties);
    }



}
