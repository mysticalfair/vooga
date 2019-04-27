package engine;

import authoring.*;
import authoring.exception.ActionDoesNotExistException;
import authoring.exception.ConditionDoesNotExistException;
import authoring.exception.ReflectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    public static final String GAME_FILE_NAME = "GameTest.xml";
    public static final int AGENT_NUM = 10;

    IGameDefinition gameEngine;
    IStateDefinition state;
    GameFactory factory;

    @BeforeEach
    void setUp(){
        try {
            factory = new GameFactory();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * Creates ten Agents all moving in the +x direction with the same speed.
     */
    @Test
    void setUpOne() {
        try {
            factory = new GameFactory();
            gameEngine = factory.createGame();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();

            state.addDefinedAgent(createZombie("zombie"));

            for(int k = 0; k < AGENT_NUM; k++){
                level.addAgent("zombie", 10 + 20 * k, 10, 0);
            }
            state.addLevel(level);
            gameEngine.setState(state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a tower that spins and spawns projectiles.
     */
    @Test
    void setupTwo() {
        try {
            factory = new GameFactory();
            gameEngine = factory.createGame();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();

            state.addDefinedAgent(createPeashooter("peashooter", createPea("pea")));

            level.addAgent("peashooter", 200, 200, 0);

            state.addLevel(level);
            gameEngine.setState(state);
            gameEngine.saveState("John.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates five peashooters that shoot at five zombies
     */
    @Test
    void setupThree() {
        try {
            factory = new GameFactory();
            gameEngine = factory.createGame();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();

            state.addDefinedAgent(createPeashooter("peashooter", createPea("pea")));
            state.addDefinedAgent(createZombie("zombie"));

            for (int i = 0; i < 5; i++) {
                level.addAgent("peashooter", 50, 50 + 50 * i, 0);
            }

            for (int i = 0; i < 5; i++) {
                level.addAgent("zombie", 350, 50 + 50 * i, 180);
            }


            state.addLevel(level);
            gameEngine.setState(state);
            gameEngine.saveState("John.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates ONE peashooter that shoots at ONE zombie.
     */
    @Test
    void setupFour() {
        try {
            GameFactory factory = new GameFactory();
            gameEngine = factory.createGame();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();

            state.addDefinedAgent(createPeashooter("peashooter", createPea("pea")));
            state.addDefinedAgent(createZombie("zombie"));

            level.addAgent("peashooter", 50, 50, 0);
            level.addAgent("zombie", 250, 50, 0);

            state.addLevel(level);
            gameEngine.setState(state);
            gameEngine.saveState("John.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void setUpSeven() {
        try {
            GameFactory factory = new GameFactory();
            gameEngine = factory.createGame();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();
            state.addDefinedAgent(createHealthAgent());
            level.addAgent("Luke", 50, 50, 0);
            state.addLevel(level);
            gameEngine.setState(state);
            gameEngine.saveState("John.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * One peashooter and one zombieshooter
     */
    @Test
    void setUpFive(){
        try {
            factory = new GameFactory();
            gameEngine = factory.createGame();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();

            IAgentDefinition pea = createPea("pea");
            IAgentDefinition zombie = createZombie("zombie");

            state.addDefinedAgent(createPeashooter("peashooter", pea));
            state.addDefinedAgent(createPeashooter("zombieshooter", zombie));

            level.addAgent("peashooter", 50, 50, 0.0);
            level.addAgent("zombieshooter", 50, 150, 0.0);

            state.addLevel(level);
            gameEngine.setState(state);
            gameEngine.saveState("John.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void setUpSix(){
        try {
            factory = new GameFactory();
            gameEngine = new Game();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();

            IAgentDefinition zombie = createZombie("zombie");
            IAgentDefinition pea = createPea("pea");

            state.addDefinedAgent(createPeashooter("peashooter", pea));
            state.addDefinedAgent(createPeashooter("zombieshooter", zombie));
            level.addAgent("peashooter", 50, 50, 0);
            level.addAgent("zombieshooter", 50, 250, 0);
            state.addLevel(level);
            gameEngine.setState(state);
            gameEngine.saveState("John.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void setState() {
    }

    @Test
    void saveState() {
        // deletes GameTestXML
        if(new File(GAME_FILE_NAME).exists()){
            new File(GAME_FILE_NAME).delete();
        }
        gameEngine.saveState(GAME_FILE_NAME);
        assertTrue(new File(GAME_FILE_NAME).exists());
    }

    @Test
    void stop() {
    }

    private IAgentDefinition createZombie(String name) throws ActionDoesNotExistException, ReflectionException, ConditionDoesNotExistException {
        int initHealth = 100;
        String team = "badGuys";

        // list of ADs
        List<IActionDecisionDefinition> AD = new ArrayList<>();

        // create Movement AD
        Map<String, Object> moveParams = new HashMap<>();
        moveParams.put("angle", 0.0);
        moveParams.put("speed", 100);
        IActionDefinition move = factory.createAction("MoveAtRelativeAngle", moveParams);
        List<IConditionDefinition> zombieMoveConditions = new ArrayList<>();
        zombieMoveConditions.add(factory.createCondition("DoOnce", new HashMap<>()));
        AD.add(factory.createActionDecision(move, zombieMoveConditions));

        // create attack

        // create properties
        List<IPropertyDefinition> properties = new ArrayList<>();
        properties.add(factory.createProperty("health", initHealth));
        properties.add(factory.createProperty("team", team));

        //create zombie
        return factory.createAgent(0, 0, 30, 30,
                180, name, "zombie.gif", AD, properties);
    }

    private IAgentDefinition createPeashooter(String name, IAgentDefinition projectile) throws ActionDoesNotExistException, ReflectionException, ConditionDoesNotExistException {
        // MAKING peashooter
        List<IActionDecisionDefinition> AD2 = new ArrayList<>();
        List<IPropertyDefinition> properties = new ArrayList<>();

        //making spawn action
        Map<String, Object> spawnParams = new HashMap<>();
        spawnParams.put("agent", projectile);
        IActionDefinition spawnAction = factory.createAction("SpawnAgentInitialDirection", spawnParams);
        List<IConditionDefinition> conditions = new ArrayList<>();
        Map condParams = new HashMap();
        condParams.put("interval", 5.0);
        conditions.add(factory.createCondition("DoOnce", condParams));
        conditions.add(factory.createCondition("Interval", condParams));
        IActionDecisionDefinition spawnAD = factory.createActionDecision(spawnAction, conditions);
        AD2.add(spawnAD);
        return factory.createAgent(50, 50, 20, 20,
                0,name, "peashooter.gif", AD2, properties);
    }

    private IAgentDefinition createPea(String name) throws ConditionDoesNotExistException, ReflectionException, ActionDoesNotExistException {
        String team = "goodGuys";
        String otherTeam = "badGuys";

        // MAKING PROJECTILE
        // making move action
        Map<String, Object> moveParams = new HashMap<>();
        moveParams.put("angle", 0.0);
        moveParams.put("speed", 100);

        List<IActionDecisionDefinition> AD1 = new ArrayList<>();
        List<IConditionDefinition> cond1 = new ArrayList<>();
        cond1.add(factory.createCondition("DoOnce", new HashMap<>()));
        AD1.add(factory.createActionDecision(
                factory.createAction("MoveAtRelativeAngle", moveParams), cond1));


//        Map<String, Object> damageParams = new HashMap<>();
//        moveParams.put("value", -50.0);
//        moveParams.put("property", "health");
//        List<IConditionDefinition> cond2 = new ArrayList<>();
//        cond2.add(factory.createCondition("Collision", new HashMap<>()));
//
//        Map<String, Object> propertyEqualParams = new HashMap<>();
//        propertyEqualParams.put("team", otherTeam);
//        cond2.add(factory.createCondition("PropertyEqualTo", propertyEqualParams));
//
//        AD1.add(factory.createActionDecision(
//                factory.createAction("AddToProperty", damageParams), cond2));

        List<IPropertyDefinition> properties = new ArrayList<>();
        properties.add(factory.createProperty("team", team));

        return factory.createAgent(500, 500, 10, 10,
                0,name, "pea.gif", AD1, properties);
    }

    private IAgentDefinition createHealthAgent() throws ConditionDoesNotExistException, ReflectionException, ActionDoesNotExistException {
        Map<String, Object> condParams = new HashMap<>();
        condParams.put("property", "health");
        condParams.put("value", 0);
        Map<String, Object> actionParams = new HashMap<>();
        Map<String, Object> action2Params = new HashMap<>();
        action2Params.put("propertyName", "health");
        action2Params.put("amount", 5.0);
        List<IActionDecisionDefinition> actionDecisions = new ArrayList<>();
        List<IConditionDefinition> conditionDefinitions = new ArrayList<>();
        List<IConditionDefinition> cond1 = new ArrayList<IConditionDefinition>();
        cond1.add(factory.createCondition("DoOnce", new HashMap<>()));
        conditionDefinitions.add(factory.createCondition("PropertyLessThanOrEqualTo", condParams));
        actionDecisions.add(factory.createActionDecision(
                factory.createAction("DestroyAgent", actionParams), conditionDefinitions));
        actionDecisions.add(factory.createActionDecision(factory.createAction("DecrementProperty", action2Params), cond1));
        List<IPropertyDefinition> properties = new ArrayList<>();
        var prop = factory.createProperty("health", 50);
        properties.add(prop);
        return factory.createAgent(50, 50, 10, 10 ,1, "Luke", "ArcherQueen.png", actionDecisions, properties);
    }
}