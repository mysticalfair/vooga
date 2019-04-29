package panes;

import authoring.GameFactory;
import authoring.ILevelDefinition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import panes.attributes.AttributesPane;
import panes.tools.PathPenTool;
import panes.tools.ToolbarPane;
import util.AuthoringContext;

import java.util.*;

public class AuthoringEnvironment extends Application {

    private AuthoringContext context;

    private StackPane stackPane;
    private BorderPane borderPane;
    private ConsolePane consolePane;
    private PathPane pathPane;
    private AgentPane agentPane;
    private AttributesPane attributesPane;
    private ToolbarPane toolbarPane;
    LevelHandler levelHandler;
    private MapPane map;
    private Scene scene;
    private ObservableList<Path> currentPaths;
    private PathPenTool pen;

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
        currentPaths = FXCollections.observableArrayList();
        stackPane.getChildren().add(borderPane);
        scene = new Scene(stackPane, context.getDouble("DefaultWidth"), context.getDouble("DefaultHeight"));
        initAllPanes();
        //initPathListeners();
        initStage(stage);
        levelHandler = new LevelHandler(context, map, pathPane, consolePane, toolbarPane, currentPaths);

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
        initAttributesPane();
        initMapPane(1);
        initToolbarPane();
        initBottomPanes();
        initAgentPane();
    }

    private void initAttributesPane() {
        attributesPane = new AttributesPane(context);
        attributesPane.accessContainer(borderPane::setLeft);
    }

    private void initMapPane(int level) {
        map = new MapPane(context, consolePane);
        map.accessContainer(borderPane::setCenter);

        ILevelDefinition gameLevel = context.getGameFactory().createLevel();
        context.getState().addLevel(gameLevel);

        map.getStateMapping().put(level, new MapState(context, null, new ArrayList<>(), FXCollections.observableArrayList()));
        map.setLevel(level);
    }

    private void initAgentPane() {
        agentPane = new AgentPane(context);
        agentPane.accessContainer(borderPane::setRight);
        agentPane.addButton(context.getString("AddButtonImageFile"), context.getDouble("ButtonSize"), context.getDouble("ButtonImageSize"),
                e -> attributesPane.createNewAgentForm(agent -> agentPane.refreshAgentList(1, map)));
        agentPane.refreshAgentList(1, map);
    }

    private void initBottomPanes() {
        consolePane = new ConsolePane(context);
        pathPane = new PathPane(context, map, scene, currentPaths, pen);
        var bottomBox = new HBox();
        consolePane.accessContainer(bottomBox.getChildren()::add);
        pathPane.accessContainer(bottomBox.getChildren()::add);
        borderPane.setBottom(bottomBox);
    }

    private void initToolbarPane() {
        toolbarPane = new ToolbarPane(context, map, scene, currentPaths);
        toolbarPane.accessContainer(borderPane::setTop);
        // TODO: Eliminate magic numbers/text here, switch to for loop through buttons
        toolbarPane.accessAddEmpty(button -> button.setOnAction(e -> levelHandler.makeLevel(toolbarPane.getMaxLevel() + 1, new MapState(context, null, new ArrayList<>(), FXCollections.observableArrayList()), false)));
        toolbarPane.accessAddExisting(button -> button.setOnAction(e ->  levelHandler.makeLevel(toolbarPane.getMaxLevel() + 1, new MapState(map.getStateMapping().get(toolbarPane.getExistingLevelValue()), map), true)));
        toolbarPane.accessClear(button -> button.setOnAction(e -> levelHandler.clearLevel()));
        toolbarPane.addButton(context.getString("LassoFile"), e -> consolePane.displayMessage("Multi-select tool enabled", ConsolePane.Level.NEUTRAL));
        toolbarPane.addButton(context.getString("PenFile"), e -> consolePane.displayMessage("Path drawing tool enabled", ConsolePane.Level.NEUTRAL));
        toolbarPane.addButton(context.getString("GrabFile"), e -> consolePane.displayMessage("Path dragging tool enabled", ConsolePane.Level.NEUTRAL));
        toolbarPane.addButton(context.getString("DeleteFile"), e -> consolePane.displayMessage("Path removal tool enabled", ConsolePane.Level.NEUTRAL));

        toolbarPane.addAction("File", context.getString("MenuItemUpload"), e -> map.formatBackground());
        toolbarPane.addAction("File", context.getString("MenuItemSave"), e -> context.getGame().saveState(context.getString("GameSaveName")));
        // TODO: implement loading an old game
        toolbarPane.addAction("File", context.getString("MenuItemOpen"), null);
        toolbarPane.getLevelChanger().valueProperty().addListener((obs, oldValue, newValue) -> levelHandler.changeToExistingLevel((int)((double) newValue)));

        pen = toolbarPane.getPen();
    }

    private void updateDimensions(double width, double height){
        var middleWidth = width - context.getDouble("AttributesWidth") - context.getDouble("AgentPaneWidth");
        var middleHeight = height - context.getDouble("ConsoleHeight") - context.getDouble("ToolbarPaneHeight") - context.getDouble("MiddleRowPadding");
        consolePane.updateSize(width/2, context.getDouble("ConsoleHeight"));
        toolbarPane.updateSize(width, context.getDouble("ToolbarPaneHeight"));
        map.updateSize(middleWidth, middleHeight);
        attributesPane.updateSize(context.getDouble("AttributesWidth"), middleHeight);
        agentPane.updateSize(context.getDouble("AgentPaneWidth"), middleHeight);
    }

    private void initStage(Stage stage) {
        stage.setTitle(context.getString("Title"));
        stage.setScene(scene);
        stage.setMinWidth(context.getDouble("DefaultWidth"));
        stage.setMinHeight(context.getDouble("DefaultHeight"));
        scene.widthProperty().addListener((observable, oldvalue, newvalue) -> updateDimensions((double) newvalue, scene.getHeight()));
        scene.heightProperty().addListener((observable, oldvalue, newvalue) -> updateDimensions(scene.getWidth (), (double) newvalue));
        stage.getScene().getStylesheets().add(context.getString("MainStyle"));
        stage.show();
    }
}
