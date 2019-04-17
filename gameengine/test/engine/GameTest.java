package engine;

import authoring.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import state.State;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    public static final String GAME_FILE_NAME = "resources/savedgames/GameTestXML";

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
            List<IActionDecisionDefinition> actionDecisions = new ArrayList<>();
//            List<IActionDecisionDefinition> actionDecisions = factory.createActionDecision(factory.createAction(action, 3));
            List<IPropertyDefinition> properties = new ArrayList<>();
            for(int k = 0; k < 10; k++){
                IAgentDefinition agent = factory.createAgent(10 + 20*k, 10, 10, 10,
                        "zombie.gif", actionDecisions, properties);
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
        }

        //state.addLevel(new Level());
    }

    @Test
    void run() {
        gameEngine.run(GAME_FILE_NAME);
    }

    @Test
    void setState() {
    }

    @Test
    void saveState() {
        gameEngine.saveState(GAME_FILE_NAME);
    }

    @Test
    void stop() {
    }
}