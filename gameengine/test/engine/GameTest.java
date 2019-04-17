package engine;

import authoring.*;
import authoring.exception.ActionDoesNotExistException;
import authoring.exception.ReflectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import state.State;

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

    @BeforeEach
    void setUp() {
        try {
            GameFactory factory = new GameFactory();
            gameEngine = new Game();
            state = new State();
            ILevelDefinition level = factory.createLevel();
            List<IAgentDefinition> currentAgents = new ArrayList<>();
//            List<IActionDecisionDefinition> actionDecisions = new ArrayList<>();
            Map<String, Object> actionParams = new HashMap<>();
            actionParams.put("angle", 0.0);
            actionParams.put("speed", 20);

            for(int k = 0; k < AGENT_NUM; k++){
                List<IActionDecisionDefinition> actionDecisions = new ArrayList<>();
                actionDecisions.add(factory.createActionDecision(
                        factory.createAction("MoveAtRelativeAngle", actionParams), new ArrayList<IConditionDefinition>()));
                List<IPropertyDefinition> properties = new ArrayList<>();
                IAgentDefinition agent = factory.createAgent(10 + 20*k, 10, 10, 10,
                        "zombie.gif", 0.0, actionDecisions, properties);
                level.addAgent(agent);
            }
            state.addLevel(level);
            gameEngine.setState(state);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ActionDoesNotExistException e) {
            e.printStackTrace();
        } catch (ReflectionException e) {
            e.printStackTrace();
        }

        //state.addLevel(new Level());
    }

    @Test
    void run() {
        gameEngine.run(GAME_FILE_NAME);
        //assertEquals(AGENT_NUM, );
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