package panes;

import authoring.GameFactory;
import frontend_objects.CloneableAgentView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import panes.attributes.AttributesPane;
import panes.tools.ToolbarPane;
import util.AuthoringContext;

import java.util.ResourceBundle;

import java.util.ArrayList;
import java.util.List;

public class AuthoringEnvironment extends Application {

    public static final String TITLE = "Electric Voogaloo!";
    public static final String MENU_ITEM_UPLOAD = "Upload Image To Background";
    public static final String MENU_ITEM_SAVE = "Save Game";
    public static final String MENU_ITEM_OPEN = "Open Game";
    public static final String GAME_SAVE_NAME = "AuthorTestGameXML";
    public static final String AGENT_BUTTON_FILE = "add-button.png";
    public static final String BUTTON_STYLE = "img";
    public static final String STYLE = "Midpoint.css";

    public static final double DEFAULT_WIDTH = 1200;
    public static final double DEFAULT_HEIGHT = 650;
    public static final double AGENT_BUTTON_SIZE = 25;
    public static final double AGENT_BUTTON_IMAGE_SIZE = 10;
    public static final double TOOLBAR_HEIGHT = 70;
    public static final double CONSOLE_HEIGHT = DEFAULT_HEIGHT/5;
    public static final double MIDDLE_ROW_HEIGHT = DEFAULT_HEIGHT - TOOLBAR_HEIGHT - CONSOLE_HEIGHT;
    public static final double ATTRIBUTES_WIDTH = DEFAULT_WIDTH/4;
    public static final double AGENT_WIDTH = DEFAULT_WIDTH/7;
    public static final double MAP_WIDTH = DEFAULT_WIDTH - ATTRIBUTES_WIDTH - AGENT_WIDTH;

    private AuthoringContext context;

    private StackPane stackPane;
    private BorderPane borderPane;
    private ConsolePane consolePane;
    private AgentPane agentPane;
    private AttributesPane attributesPane;
    private ToolbarPane toolbarPane;
    private MapPane map;
    private Scene scene;
    private List<MapState> levels;
    private List<Path> paths;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage){
        GameFactory gameFactory = initGameFactory();
        context = new AuthoringContext(ResourceBundle.getBundle("strings/English"),
                null,
                gameFactory,
                gameFactory.createState(),
                gameFactory.createGame());
        context.getGame().setState(context.getState());

        stackPane = new StackPane();
        borderPane = new BorderPane();
        paths = new ArrayList<>();
        stackPane.getChildren().add(borderPane);
        scene = new Scene(stackPane, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        initAllPanes();
        initStage(stage);

        // This is needed here because the context needs to be created to be passed to the consolePane, but it also
        // needs access to this method from the consolePane. It is a bit of circular referencing, but since it's
        // via a lambda it is not as bad.
        context.setDisplayConsoleMessage((message, level) -> consolePane.displayMessage(message, level));
    }

    private GameFactory initGameFactory() {
        try {
            return new GameFactory();
        } catch (Exception e) {
            //ResourceBundle.getBundle("strings/English").getString("GameFactoryInitializationError");
            System.err.println(e.getMessage());
            System.exit(-1);
            return null;
        }
    }

    private void initAllPanes() {
        initMapPane(1);
        initAttributesPane();
        initConsolePane();
        initToolbarPane();
        initAgentPane();
    }

    private void initMapPane(int level) {
        map = new MapPane(context);
        map.accessContainer(borderPane::setCenter);
        map.getStateMapping().put(level, new MapState(null, new ArrayList<>()));
        map.setLevel(level);
    }

    private void initAgentPane() {
        agentPane = new AgentPane(context);
        agentPane.accessContainer(borderPane::setRight);
        agentPane.addButton(AGENT_BUTTON_FILE, AGENT_BUTTON_SIZE, AGENT_BUTTON_IMAGE_SIZE, e -> attributesPane.createNewAgentForm());
        for (CloneableAgentView o : agentPane.getAgentList()) {
            o.setId(BUTTON_STYLE);
            o.setOnMousePressed(e -> o.mousePressedOnClone(e, map, consolePane));
        }
    }

    private void initAttributesPane() {
        attributesPane = new AttributesPane(context);
        attributesPane.accessContainer(borderPane::setLeft);
    }

    private void initConsolePane() {
        consolePane = new ConsolePane(context);
        consolePane.accessContainer(borderPane::setBottom);
        //consolePane.addButton("set background", e -> map.formatBackground());
    }

    private void initToolbarPane() {
        toolbarPane = new ToolbarPane(context, map, scene, paths);
        toolbarPane.accessContainer(borderPane::setTop);
        // TODO: Eliminate magic numbers/text here, switch to for loop through buttons
        toolbarPane.addButton(context.getString("LassoFile"), e -> consolePane.displayMessage("Multi-select tool enabled", ConsolePane.Level.NEUTRAL));
        toolbarPane.addButton(context.getString("PenFile"), e -> consolePane.displayMessage("Path drawing tool enabled", ConsolePane.Level.NEUTRAL));
        toolbarPane.addButton(context.getString("GrabFile"), e -> consolePane.displayMessage("Path dragging tool enabled", ConsolePane.Level.NEUTRAL));
        toolbarPane.addButton(context.getString("DeleteFile"), e -> consolePane.displayMessage("Path removal tool enabled", ConsolePane.Level.NEUTRAL));

        toolbarPane.addAction("File", MENU_ITEM_UPLOAD, e -> map.formatBackground());
        toolbarPane.addAction("File", MENU_ITEM_SAVE, e -> context.getGame().saveState(GAME_SAVE_NAME));
        // TODO: implement loading an old game
        toolbarPane.addAction("File", MENU_ITEM_OPEN, null);

        toolbarPane.getLevelChanger().valueProperty().addListener((obs, oldValue, newValue) -> changeLevel((int)((double) oldValue), (int)((double) newValue)));
    }

    private void changeLevel(int oldValue, int newValue) {
        map.setLevel(newValue);
        if (!map.getStateMapping().containsKey(newValue)) {
            map.getMapPane().getChildren().clear();
            map.getStateMapping().put(newValue, new MapState(null, new ArrayList<>()));
        } else {
            MapState revertToState = map.getStateMapping().get(newValue);
            revertToState.updateMap(map);
        }
    }

    private void updateDimensions(double width, double height){
        var middleWidth = width - ATTRIBUTES_WIDTH - AGENT_WIDTH;
        var middleHeight = height - CONSOLE_HEIGHT - TOOLBAR_HEIGHT;
        consolePane.updateSize(width, CONSOLE_HEIGHT);
        toolbarPane.updateSize(width, TOOLBAR_HEIGHT);
        map.updateSize(middleWidth, middleHeight);
        attributesPane.updateSize(ATTRIBUTES_WIDTH, middleHeight);
        agentPane.updateSize(AGENT_WIDTH, middleHeight);
    }

    private void initStage(Stage stage) {
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.setMinWidth(DEFAULT_WIDTH);
        stage.setMinHeight(DEFAULT_HEIGHT);
        scene.widthProperty().addListener((observable, oldvalue, newvalue) -> updateDimensions((double) newvalue, scene.getHeight()));
        scene.heightProperty().addListener((observable, oldvalue, newvalue) -> updateDimensions(scene.getWidth (), (double) newvalue));
        stage.getScene().getStylesheets().add(STYLE);
        stage.show();
    }
}
