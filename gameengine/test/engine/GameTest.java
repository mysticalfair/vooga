package engine;

import authoring.GameFactory;
import authoring.ILevelDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import state.State;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game gameEngine;
    State state;

    @BeforeEach
    void setUp() {
        try {
            GameFactory factory = new GameFactory();
            gameEngine = new Game();
            state = new State();
            ILevelDefinition level = factory.createLevel();
            state.addLevel(level);
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
    }

    @Test
    void setState() {
    }

    @Test
    void saveState() {
        gameEngine.saveState("resources/savedgames/GameTestXML");
    }

    @Test
    void stop() {
    }
}