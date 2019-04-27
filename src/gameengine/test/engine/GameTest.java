package engine;

import authoring.*;
import authoring.exception.ActionDoesNotExistException;
import authoring.exception.ConditionDoesNotExistException;
import authoring.exception.ReflectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import state.State;
import state.actiondecision.ActionDecision;
import state.agent.Agent;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
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

    /**
     * Creates ten Agents all moving in the +x direction with the same speed.
     */
    void setUpOne() {
        try {
            GameFactory factory = new GameFactory();
            gameEngine = factory.createGame();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();
            List<IAgentDefinition> currentAgents = new ArrayList<>();
            Map<String, Object> actionParams = new HashMap<>();
            actionParams.put("angle", 0.0);

                List<IActionDecisionDefinition> actionDecisions = new ArrayList<>();
                actionDecisions.add(factory.createActionDecision(
                        factory.createAction("MoveAtRelativeAngle", actionParams), new ArrayList<IConditionDefinition>()));
                List<IPropertyDefinition> properties = new ArrayList<>();
 actionParams.put("speed", 20.0);
            IAgentDefinition agent = factory.createAgent(10, 10, 10, 10,
                    0,"zombieee", "zombie.gif", actionDecisions, properties);
            state.addDefinedAgent(agent);

            for(int k = 0; k < AGENT_NUM; k++){
                level.addAgent("zombieee", 10 + 20 * k, 10, 0);
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

            // MAKING PROJECTILE
            // making move action
            Map<String, Object> moveParams = new HashMap<>();
            moveParams.put("angle", 0.0);
            moveParams.put("speed", 20);

            List<IActionDecisionDefinition> AD1 = new ArrayList<>();
            List<IConditionDefinition> cond1 = new ArrayList<IConditionDefinition>();
            cond1.add(factory.createCondition("DoOnce", new HashMap<>()));
            AD1.add(factory.createActionDecision(
                    factory.createAction("MoveAtRelativeAngle", moveParams), cond1));
            List<IPropertyDefinition> properties = new ArrayList<>();
            IAgentDefinition projectile = factory.createAgent(200, 200, 10, 10,
                    0,"projectile", "zombie.gif", AD1, properties);

            // MAKING TOWER
            List<IActionDecisionDefinition> AD2 = new ArrayList<>();
            // making spin action
            Map<String, Object> spinParams = new HashMap<>();
            spinParams.put("angularSpeed", 90.0);
            IActionDefinition spinAction = factory.createAction("Spin", spinParams);
            List<IConditionDefinition> conditions = new ArrayList<>();
            conditions.add(factory.createCondition("DoOnce", new HashMap<>()));
            AD2.add(factory.createActionDecision(spinAction, conditions));
            //making spawn action
            Map<String, Object> spawnParams = new HashMap<>();
            spawnParams.put("agent", projectile);
            IActionDefinition spawnAction = factory.createAction("SpawnAgentInitialDirection", spawnParams);
            conditions = new ArrayList<>();
            Map condParams = new HashMap();
            condParams.put("interval", 1.0);
            conditions.add(factory.createCondition("DoOnce", condParams));
            conditions.add(factory.createCondition("Interval", condParams));
            IActionDecisionDefinition spawnAD = factory.createActionDecision(spawnAction, conditions);
            AD2.add(spawnAD);
            IAgentDefinition tower = factory.createAgent(200, 200, 10, 10,
                    0,"tower", "zombie.gif", AD2, properties);

            state.addDefinedAgent(tower);

            level.addAgent("tower", 200, 200, 0);

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
            String team1 = "team1";
            String team2 = "team2";

            GameFactory factory = new GameFactory();
            gameEngine = factory.createGame();
            state = factory.createState();
            ILevelDefinition level = factory.createLevel();

            // MAKING PROJECTILE
            // making move action
            Map<String, Object> moveParams = new HashMap<>();
            moveParams.put("angle", 0.0);
            moveParams.put("speed", 20);

            List<IActionDecisionDefinition> AD1 = new ArrayList<>();
            List<IConditionDefinition> cond1 = new ArrayList<>();

            // add do once condition
            cond1.add(factory.createCondition("DoOnce", new HashMap<>()));
            AD1.add(factory.createActionDecision(
                    factory.createAction("MoveAtRelativeAngle", moveParams), cond1));

            List<IConditionDefinition> damageConditions = new ArrayList<>();

            // add collision condition
            damageConditions.add(factory.createCondition("Collision", new HashMap<>()));
            // add enemy condition
            List<IPropertyDefinition> properties = new ArrayList<>();
            Map<String, Object> enemyParams = new HashMap();
            enemyParams.put("property", "team");
            enemyParams.put("value", team1);
            damageConditions.add(factory.createCondition("PropertyEqualTo", new HashMap<>()));
            IAgentDefinition projectile = factory.createAgent(500, 500, 10, 10,
                    0,"projectile", "pea.gif", AD1, properties);

            // MAKING TOWER
            List<IActionDecisionDefinition> AD2 = new ArrayList<>();

            //making spawn action
            Map<String, Object> spawnParams = new HashMap<>();
            spawnParams.put("agent", projectile);
            IActionDefinition spawnAction = factory.createAction("SpawnAgentInitialDirection", spawnParams);
            List<IConditionDefinition> conditions = new ArrayList<>();
            Map condParams = new HashMap();
            condParams.put("interval", 1.0);
            conditions.add(factory.createCondition("DoOnce", condParams));
            conditions.add(factory.createCondition("Interval", condParams));
            IActionDecisionDefinition spawnAD = factory.createActionDecision(spawnAction, conditions);
            AD2.add(spawnAD);
            IAgentDefinition tower = factory.createAgent(50, 50, 30, 30,
                    0,"tower", "peashooter.gif", AD2, properties);
            IAgentDefinition tower2 = ((Agent)tower).clone();
            ((Agent) tower2).setLocation(50, 100);

            IAgentDefinition tower3 = ((Agent)tower).clone();
            ((Agent) tower3).setLocation(50, 150);

            IAgentDefinition tower4 = ((Agent)tower).clone();
            ((Agent) tower4).setLocation(50, 200);

            IAgentDefinition tower5 = ((Agent)tower).clone();
            ((Agent) tower5).setLocation(50, 250);




            List<IActionDecisionDefinition> AD3 = new ArrayList<>();
            moveParams.put("speed", 10);
            IActionDefinition move = factory.createAction("MoveAtRelativeAngle", moveParams);
            List<IConditionDefinition> zombieMoveConditions = new ArrayList<>();
            zombieMoveConditions.add(factory.createCondition("DoOnce", condParams));
            AD3.add(factory.createActionDecision(move, zombieMoveConditions));

            IAgentDefinition zombie = factory.createAgent(350, 50, 30, 30,
                    180, "zombie", "zombie.gif", AD3, properties);

            IAgentDefinition zombie2 = ((Agent)zombie).clone();
            ((Agent) tower).setLocation(350, 100);

            IAgentDefinition zombie3 = ((Agent)zombie).clone();
            ((Agent) tower).setLocation(350, 150);

            IAgentDefinition zombie4 = ((Agent)zombie).clone();
            ((Agent) tower).setLocation(350, 200);

            IAgentDefinition zombie5 = ((Agent)zombie).clone();
            ((Agent) tower).setLocation(350, 250);

            state.addDefinedAgent(tower);
            state.addDefinedAgent(zombie);
            state.addDefinedAgent(projectile);

            for (int i = 0; i < 5; i++) {
                level.addAgent("tower", 50, 50 + 30 * i, 0);
            }

            for (int i = 0; i < 5; i++) {
                level.addAgent("zombie", 350, 50 + 30 * i, 0);
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

            // MAKING PROJECTILE
            // making move action
            Map<String, Object> moveParams = new HashMap<>();
            moveParams.put("angle", 0.0);
            moveParams.put("speed", 20);

            List<IActionDecisionDefinition> AD1 = new ArrayList<>();
            List<IConditionDefinition> cond1 = new ArrayList<IConditionDefinition>();
            cond1.add(factory.createCondition("DoOnce", new HashMap<>()));
            AD1.add(factory.createActionDecision(
                    factory.createAction("MoveAtRelativeAngle", moveParams), cond1));
            List<IPropertyDefinition> properties = new ArrayList<>();
            IAgentDefinition projectile = factory.createAgent(500, 500, 10, 10,
                    0,"projectile", "pea.gif", AD1, properties);

            // MAKING TOWER
            List<IActionDecisionDefinition> AD2 = new ArrayList<>();

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
            IAgentDefinition tower = factory.createAgent(50, 50, 30, 30,
                    0,"tower", "peashooter.gif", AD2, properties);


            List<IActionDecisionDefinition> AD3 = new ArrayList<>();
            moveParams.put("speed", 100);
            IActionDefinition move = factory.createAction("MoveAtRelativeAngle", moveParams);
            List<IConditionDefinition> zombieMoveConditions = new ArrayList<>();
            zombieMoveConditions.add(factory.createCondition("DoOnce", condParams));
            AD3.add(factory.createActionDecision(move, zombieMoveConditions));

            IAgentDefinition zombie = factory.createAgent(250, 50, 30, 30,
                    180, "zombie", "zombie.gif", AD3, properties);

            state.addDefinedAgent(tower);
            state.addDefinedAgent(zombie);

            level.addAgent("tower", 50, 50, 0);
            level.addAgent("zombie", 250, 50, 0);

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
}