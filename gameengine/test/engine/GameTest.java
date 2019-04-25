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
    public static final String GAME_FILE_NAME = "resources/savedgames/GameTestXML";
    public static final int AGENT_NUM = 10;

    Game gameEngine;
    State state;

    /**
     * Creates ten Agents all moving in the +x direction with the same speed.
     */
    void setUpOne() {
        try {
            GameFactory factory = new GameFactory();
            gameEngine = new Game();
            state = new State();
            ILevelDefinition level = factory.createLevel();
            List<IAgentDefinition> currentAgents = new ArrayList<>();
            Map<String, Object> actionParams = new HashMap<>();
            actionParams.put("angle", 0.0);
            actionParams.put("speed", 20.0);

            for(int k = 0; k < AGENT_NUM; k++){
                List<IActionDecisionDefinition> actionDecisions = new ArrayList<>();
                actionDecisions.add(factory.createActionDecision(
                   factory.createAction("MoveAtRelativeAngle", actionParams), new ArrayList<IConditionDefinition>()));
                List<IPropertyDefinition> properties = new ArrayList<>();
                IAgentDefinition agent = factory.createAgent(10 + 20*k, 10, 10, 10,
                   0,"zombieee", "zombie.gif", actionDecisions, properties);

                level.addAgent(agent);
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
            gameEngine = new Game();
            state = new State();
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

            level.addAgent(tower);

            state.addLevel(level);
            gameEngine.setState(state);
            gameEngine.saveState("John.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a tower that spins and spawns projectiles.
     */
    @Test
    void setupThree() {
        try {
            GameFactory factory = new GameFactory();
            gameEngine = new Game();
            state = new State();
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

            level.addAgent(projectile);

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
            ((Agent) tower).setLocation(50, 100);

            IAgentDefinition tower3 = ((Agent)tower).clone();
            ((Agent) tower).setLocation(50, 150);

            IAgentDefinition tower4 = ((Agent)tower).clone();
            ((Agent) tower).setLocation(50, 200);

            IAgentDefinition tower5 = ((Agent)tower).clone();
            ((Agent) tower).setLocation(50, 250);


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

            level.addAgent(tower);
            level.addAgent(tower2);
            level.addAgent(tower3);
            level.addAgent(tower4);
            level.addAgent(tower5);
            level.addAgent(zombie);
            level.addAgent(zombie2);
            level.addAgent(zombie3);
            level.addAgent(zombie4);
            level.addAgent(zombie5);




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
            new Game().run("John.xml");
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