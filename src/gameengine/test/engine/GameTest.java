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
            GameFactory factory = new GameFactory();
            gameEngine = factory.createGame();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();

            state.addDefinedAgent(createZombie());

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
            GameFactory factory = new GameFactory();
            gameEngine = factory.createGame();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();

            state.addDefinedAgent(createPeashooter(createPea()));

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

            GameFactory factory = new GameFactory();
            gameEngine = factory.createGame();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();

            state.addDefinedAgent(createPeashooter(createPea()));
            state.addDefinedAgent(createZombie());

            for (int i = 0; i < 5; i++) {
                level.addAgent("peashooter", 50, 50 + 50 * i, 0);
            }

            for (int i = 0; i < 5; i++) {
                level.addAgent("zombie", 350, 50 + 50 * i, 0);
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

            state.addDefinedAgent(createPeashooter(createPea()));
            state.addDefinedAgent(createZombie());

            level.addAgent("tower", 50, 50, 0);
            level.addAgent("zombie", 250, 50, 0);

            state.addLevel(level);
            gameEngine.setState(state);
            gameEngine.saveState("John.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates ten Agents all moving in the +x direction with the same speed.
     */
    @Test
    void setUpSix() {
        try {
            GameFactory factory = new GameFactory();
            gameEngine = factory.createGame();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();
            Map<String, Object> condParams = new HashMap<>();
            condParams.put("property", "health");
            condParams.put("value", 0);
            Map<String, Object> actionParams = new HashMap<>();
            List<IActionDecisionDefinition> actionDecisions = new ArrayList<>();
            Map<String, Object> healthCond = new HashMap<>();
            healthCond.put("value", 0);
            healthCond.put("property", "health");
//            actionDecisions.add(factory.createActionDecision(
//                    factory.createAction("PropertyLessThanOrEqualToCondition", actionParams), factory.createCondition("PropertyLessThanOrEqualToCondition", healthCond)));
            List<IPropertyDefinition> properties = new ArrayList<>();
            var prop = factory.createProperty("health", 50);
            properties.add(prop);
            IAgentDefinition healthyAgent = factory.createAgent(50, 50, 10, 10 ,1, "Luke", "ArcherQueen.png", actionDecisions, properties);
            state.addDefinedAgent(healthyAgent);
            state.addLevel(level);
            gameEngine.setState(state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void setUpFive(){
        try {
            GameFactory factory = new GameFactory();
            gameEngine = new Game();
            ILevelDefinition level = factory.createLevel();

            // MAKING PROJECTILE
            // making move action
            Map<String, Object> moveParams = new HashMap<>();
            moveParams.put("angle", 0.0);

            Map condParams = new HashMap();
            List<IPropertyDefinition> properties = new ArrayList<>();
            List<IActionDecisionDefinition> AD3 = new ArrayList<>();
            moveParams.put("speed", 100);
            IActionDefinition move = factory.createAction("MoveAtRelativeAngle", moveParams);
            List<IConditionDefinition> zombieMoveConditions = new ArrayList<>();
            zombieMoveConditions.add(factory.createCondition("DoOnce", condParams));
            AD3.add(factory.createActionDecision(move, zombieMoveConditions));

            IAgentDefinition zombie = factory.createAgent(250, 50, 30, 30,
                    180, "zombie", "zombie.gif", AD3, properties);

           IAgentDefinition zombie2 = factory.createAgent(300, 50, 30, 30,
                    200, "zombie2", "zombie.gif", AD3, properties);


            state.addLevel(level);
            gameEngine.setState(state);
            gameEngine.saveState("John.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    void run() {
        try {
            Game game = new Game();
            game.loadState("John.xml");
            game.run();
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

    private IAgentDefinition createZombie() throws ActionDoesNotExistException, ReflectionException, ConditionDoesNotExistException {
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
                180, "zombie", "zombie.gif", AD, properties);
    }

    private IAgentDefinition createPeashooter(IAgentDefinition projectile) throws ActionDoesNotExistException, ReflectionException, ConditionDoesNotExistException {
        int initHealth = 100;
        double shootInterval = 5.0;
        String team = "goodGuys";

        // list of ADs
        List<IActionDecisionDefinition> AD = new ArrayList<>();

        // create shoot AD
        Map<String, Object> spawnParams = new HashMap<>();
        spawnParams.put("agent", projectile);
        IActionDefinition spawnAction = factory.createAction("SpawnAgentInitialDirection", spawnParams);
        List<IConditionDefinition> spawnConditions = new ArrayList<>();
        spawnConditions.add(factory.createCondition("DoOnce", new HashMap<>()));
        Map condParams = new HashMap();
        condParams.put("interval", shootInterval);
        spawnConditions.add(factory.createCondition("Interval", condParams));
        IActionDecisionDefinition spawnAD = factory.createActionDecision(spawnAction, spawnConditions);

        // create properties
        List<IPropertyDefinition> properties = new ArrayList<>();
        properties.add(factory.createProperty("health", initHealth));
        properties.add(factory.createProperty("team", team));

        //create peashooter
        return factory.createAgent(0, 0, 30, 30,
                180, "peashooter", "peashooter.gif", AD, properties);
    }

    private IAgentDefinition createPea() throws ConditionDoesNotExistException, ReflectionException, ActionDoesNotExistException {
        String team = "goodGuys";
        String otherTeam = "badGuys";

        // making move action
        Map<String, Object> moveParams = new HashMap<>();
        moveParams.put("angle", 0.0);
        moveParams.put("speed", 20);

        List<IActionDecisionDefinition> AD1 = new ArrayList<>();
        List<IConditionDefinition> cond1 = new ArrayList<>();
        cond1.add(factory.createCondition("DoOnce", new HashMap<>()));
        AD1.add(factory.createActionDecision(
                factory.createAction("MoveAtRelativeAngle", moveParams), cond1));


        Map<String, Object> damageParams = new HashMap<>();
        moveParams.put("value", -50.0);
        moveParams.put("property", "health");
        List<IConditionDefinition> cond2 = new ArrayList<>();
        cond2.add(factory.createCondition("OnCollision", new HashMap<>()));

        Map<String, Object> propertyEqualParams = new HashMap<>();
        propertyEqualParams.put("team", otherTeam);
        cond2.add(factory.createCondition("PropertyEqualTo", propertyEqualParams));

        AD1.add(factory.createActionDecision(
                factory.createAction("AddToProperty", damageParams), cond2));

        List<IPropertyDefinition> properties = new ArrayList<>();
        properties.add(factory.createProperty("team", team));

        return factory.createAgent(500, 500, 10, 10,
                0,"projectile", "pea.gif", AD1, properties);
    }


}