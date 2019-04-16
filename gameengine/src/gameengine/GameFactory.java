package gameengine;

import engine.Game;
import engine.Level;
import engine.event.GameEventMaster;
import gameengine.exception.ActionDoesNotExistException;
import gameengine.exception.ConditionDoesNotExistException;
import gameengine.exception.IncorrectParametersException;
import gameengine.exception.ReflectionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import state.State;
import state.actiondecision.ActionDecision;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Factory to create everything needed to define and save a game
 * @author David Miron
 */
public class GameFactory {

    public static final String CONDITION_DEFINITIONS_FILE = "conditions.xml";
    public static final String ACTION_DEFINITIONS_FILE = "actions.xml";

    public static final String CONDITION_CLASSNAMES_FILE = "conditions.properties";
    public static final String ACTION_CLASSNAMES_FILE = "actions.properties";

    private GameEventMaster eventMaster;

    private List<AvailableCondition> availableConditions;
    private List<AvailableAction> availableActions;

    private Properties conditionClasses;
    private Properties actionClasses;

    public GameFactory() throws ParserConfigurationException, SAXException, IOException {
        this.eventMaster = new GameEventMaster();
        XMLToAvailbableObjectsParser parser = new XMLToAvailbableObjectsParser();
        availableConditions = parser.getNameFieldsList(CONDITION_DEFINITIONS_FILE);
        availableActions = parser.getNameFieldsList(ACTION_DEFINITIONS_FILE);

        conditionClasses.load(getClass().getClassLoader().getResourceAsStream(CONDITION_CLASSNAMES_FILE));
        actionClasses.load(getClass().getClassLoader().getResourceAsStream(ACTION_CLASSNAMES_FILE));
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
        // TODO: return new ActionDecision(action, conditions);
        return null;
    }

    /**
     * Create an action
     * @param name The name of the action, specified in xml file
     * @param params The parameters of that action, should match fields in xml file
     * @return The action object
     */
    public IActionDefinition createAction(String name, Object ... params) throws ActionDoesNotExistException,
            IncorrectParametersException, ReflectionException {

        if (!nameFieldsExists(availableActions, name))
            throw new ActionDoesNotExistException();

        return instantiateClass(actionClasses.getProperty(name), params);
    }

    /**
     * Create a condition
     * @param name The name of the condition, specified in xml file
     * @param params The parameters of that condition, should match fields in xml file
     * @return The condition object
     */
    public IConditionDefinition createCondition(String name, Object ... params) throws ConditionDoesNotExistException,
            IncorrectParametersException, ReflectionException {

        if (!nameFieldsExists(availableConditions, name))
            throw new ConditionDoesNotExistException();

        return instantiateClass(conditionClasses.getProperty(name), params);
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

    private <T> T instantiateClass(String className, Object ... params) throws IncorrectParametersException, ReflectionException {

        try {
            Class clazz = Class.forName(className);
            Class[] arguments = new Class[params.length];
            for (int i = 0; i < params.length; i++) {
                arguments[i] = params[i].getClass();
            }

            Constructor constructor = clazz.getConstructor(arguments);

            return (T)constructor.newInstance(params);
        } catch (NoSuchMethodException e) {
            throw new IncorrectParametersException();
        } catch (Exception e) {
            throw new ReflectionException();
        }

    }

    private boolean nameFieldsExists(List<? extends AvailableNameFields> list, String name) {
        for (AvailableNameFields anf: list) {
            if (anf.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private class XMLToAvailbableObjectsParser {

        private DocumentBuilder documentBuilder;

        public XMLToAvailbableObjectsParser() throws ParserConfigurationException {
            this.documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }

        public <T extends AvailableNameFields> List<T> getNameFieldsList(String file) throws SAXException, IOException {

            Document doc = documentBuilder.parse(getClass().getClassLoader().getResourceAsStream(file));
            NodeList nodes = doc.getChildNodes();
            List<T> nameFieldsList = new ArrayList<>();

            for (int i = 0; i < nodes.getLength(); i++) {
                String name = "";
                List<Field> fields = new ArrayList<>();
                Node node = nodes.item(i);
                NodeList childNodes = node.getChildNodes();

                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node n = childNodes.item(j);
                    if (n.getNodeName().equals("name")) {
                        name = n.getTextContent();
                    } else if (n.getNodeName().equals("fields")) {
                        fields = parseFieldsList(n);
                    }
                }
                nameFieldsList.add((T)new AvailableNameFields(name, fields));
            }
            return nameFieldsList;
        }

        private List<Field> parseFieldsList(Node fieldsNode) {
            NodeList children = fieldsNode.getChildNodes();
            List<Field> fields = new ArrayList<>();
            for (int i = 0; i < children.getLength(); i++) {
                Node node = children.item(i);
                String fieldName = node.getTextContent();
                String fieldType = node.getNodeName();
                fields.add(new Field(fieldName, fieldType));
            }
            return fields;
        }

    }

}
