package gameengine;

import engine.Game;
import engine.Level;
import engine.event.GameEventMaster;
import gameengine.exception.ConditionDoesNotExistException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import state.State;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Factory to create everything needed to define and save a game
 * @author David Miron
 */
public class GameFactory {

    public static final String FIELD_DEFINITIONS_FILE = "fields.xml";
    public static final String CONDITION_DEFINITIONS_FILE = "conditions.xml";
    public static final String ACTION_DEFINITIONS_FILE = "actions.xml";

    private GameEventMaster eventMaster;

    private Document fieldsDoc;
    private Document conditionsDoc;
    private Document actionsDoc;

    public GameFactory() throws ParserConfigurationException, SAXException, IOException {
        this.eventMaster = new GameEventMaster();
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        this.fieldsDoc = documentBuilder.parse(getClass().getClassLoader().getResourceAsStream(FIELD_DEFINITIONS_FILE));
        this.conditionsDoc = documentBuilder.parse(getClass().getClassLoader().getResourceAsStream(CONDITION_DEFINITIONS_FILE));
        this.actionsDoc = documentBuilder.parse(getClass().getClassLoader().getResourceAsStream(ACTION_DEFINITIONS_FILE));
    }

    /**
     * Create a default game, with no agents or objectives
     * @return A default game
     */
    public IGameDefinition createGame() {
        return new Game();
    }

    /**
     * Create a default state, with no levels
     * @return A default state
     */
    public IStateDefinition createState() {
        return new State();
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
    public IAgentDefinition createAgent(int x, int y, int width, int height, String imageURL,
                                        List<IActionDecisionDefinition> actionDecisions,
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
    public IConditionDefinition createCondition(String name, Object ... params) throws ConditionDoesNotExistException {

        if (!hasNameTagWithText(conditionsDoc, name))
            throw new ConditionDoesNotExistException();

        NodeList conditions = conditionsDoc.getChildNodes();
        Node targetNode = null;
        for (int i = 0; i < conditions.getLength(); i++)
            if (conditions.item(i).)

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

    private boolean hasNameTagWithText(Document doc, String name) {
        NodeList nameTags = doc.getElementsByTagName("name");
        for (int i = 0; i < nameTags.getLength(); i++) {
            if (nameTags.item(i).getTextContent().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
