package panes;

import authoring.GameFactory;
import authoring.ILevelDefinition;
import frontend_objects.DraggableAgentView;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import panes.attributes.AttributesPane;
import panes.tools.PathPenTool;
import panes.tools.ToolbarPane;
import state.AgentReference;
import util.AuthoringContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class AuthoringEnvironment extends Application {

    private AuthoringContext context;

    private StackPane stackPane;
    private BorderPane borderPane;
    private ConsolePane consolePane;
    private PathPane pathPane;
    private AgentPane agentPane;
    private AttributesPane attributesPane;
    private ToolbarPane toolbarPane;
    private MapPane map;
    private Scene scene;
    private List<MapState> levels;
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
        initPathListeners();
        initStage(stage);

        // This is needed here because the context needs to be created to be passed to the consolePane, but it also
        // needs access to this method from the consolePane. It is a bit of circular referencing, but since it's
        // via a lambda it is not as bad.
        context.setDisplayConsoleMessage((message, level) -> consolePane.displayMessage(message, level));
    }

    private void initPathListeners(){
        currentPaths.addListener((ListChangeListener<Path>) c -> onPathListChange(c));
    }

    // Code adapted from https://docs.oracle.com/javase/8/javafx/api/javafx/collections/ListChangeListener.Change.html
    private void onPathListChange(ListChangeListener.Change<? extends Path> c){
        while (c.next()) {
            for (Path removed : c.getRemoved()) {
                pathPane.removePathRow(removed);
                map.getCurrentState().removePath(removed);
            }
            for (Path added : c.getAddedSubList()) {
                pathPane.addPathRow(added);
                map.getCurrentState().addToPaths(added);
            }
        }
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
        map.getCurrentState().accessSelectCount(countProperty -> establishSelectCountListener(countProperty));
    }

    private void establishSelectCountListener(SimpleIntegerProperty selectCount) {
        selectCount.addListener((observable, oldValue, newValue) -> updateOnLevelSelection((int) newValue));
    }

    private void updateOnLevelSelection(int newValue){
        map.handleSelectionChange(newValue);
        currentPaths.removeListener((ListChangeListener<Path>) c -> onPathListChange(c));
        for(Path path: currentPaths){
            currentPaths.remove(path);
            pathPane.removePathRow(path);
        }
        for(Path path: map.getCurrentState().getPaths()){
            currentPaths.add(path);
            pathPane.addPathRow(path);
        }
        initPathListeners();
        //pathPane.setNewPathList(map.getCurrentState().getPaths());
    }

    private void initAgentPane() {
        agentPane = new AgentPane(context);
        agentPane.accessContainer(borderPane::setRight);
        agentPane.addButton(context.getString("AddButtonImageFile"), context.getDouble("ButtonSize"),
                e -> attributesPane.createNewAgentForm(a -> agentPane.refreshAgentList(1), null, false));
        agentPane.setOnImageClicked((e, agent) -> {
            //if (e.getClickCount() == getContext().getInt("CloneClickCount")) { // Only add on double click to allow editing action on single click
            DraggableAgentView copy = new DraggableAgentView(context, agent.getImageURL());
            map.addAgent(map.getLevel(), copy);
            context.displayConsoleMessage(context.getString("AgentAdded") + map.getAgentCount(), ConsolePane.Level.NEUTRAL);
            copy.setMouseActionsForDrag(map);
            //}
        });
        agentPane.setOnEdit((e, agent) ->
            attributesPane.createNewAgentForm(a -> agentPane.refreshAgentList(1), agent, false)
        );
        agentPane.setOnCopy((e, agent) ->
            attributesPane.createNewAgentForm(a -> agentPane.refreshAgentList(1), agent, true)
        );
        agentPane.setOnDelete((e, agent) -> {
            // TODO: Delete agent from overall list of defined agents, and all references of it in levels
        });
        agentPane.setOnCheckChanged((b, agent) -> {
            // TODO: Add or remove agent from list of placeables in current level
        });
        agentPane.refreshAgentList(1);
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
        toolbarPane.accessAddEmpty(button -> button.setOnAction(e -> makeLevel(toolbarPane.getMaxLevel() + 1, new MapState(context, null, new ArrayList<>(), FXCollections.observableArrayList()), false)));
        toolbarPane.accessAddExisting(button -> button.setOnAction(e -> makeFromExistingWrapper()));
        toolbarPane.accessClear(button -> button.setOnAction(e -> clearLevel()));
        toolbarPane.addButton(context.getString("LassoFile"), e -> consolePane.displayMessage("Multi-select tool enabled", ConsolePane.Level.NEUTRAL));
        toolbarPane.addButton(context.getString("PenFile"), e -> consolePane.displayMessage("Path drawing tool enabled", ConsolePane.Level.NEUTRAL));
        toolbarPane.addButton(context.getString("GrabFile"), e -> consolePane.displayMessage("Path dragging tool enabled", ConsolePane.Level.NEUTRAL));
        toolbarPane.addButton(context.getString("DeleteFile"), e -> consolePane.displayMessage("Path removal tool enabled", ConsolePane.Level.NEUTRAL));

        toolbarPane.addAction("File", context.getString("MenuItemUpload"), e -> map.formatBackground());
        toolbarPane.addAction("File", context.getString("MenuItemSave"), e -> context.getGame().saveState(context.getString("GameSaveName")));
        // TODO: implement loading an old game
        toolbarPane.addAction("File", context.getString("MenuItemOpen"), null);
        toolbarPane.getLevelChanger().valueProperty().addListener((obs, oldValue, newValue) -> changeToExistingLevel((int)((double) newValue)));

        pen = toolbarPane.getPen();
    }

    private void clearLevel() {
        map.clearMap();
        int levelIndex = (int)(double) toolbarPane.getLevelChanger().getValue();
        ILevelDefinition gameLevel = context.getState().getLevels().get(levelIndex);
        clearGameLevelContents(gameLevel);
        map.getStateMapping().put(levelIndex, new MapState(context, null, new ArrayList<>(), FXCollections.observableArrayList()));
        map.getCurrentState().accessSelectCount(countProperty -> establishSelectCountListener(countProperty));
    }

    private void clearGameLevelContents(ILevelDefinition gameLevel){
        for(String pathName: gameLevel.getPaths().keySet()){
            gameLevel.removePath(pathName);
        }
        for(String agent: gameLevel.getPlaceableAgents()){
            gameLevel.removePlaceableAgent(agent);
        }
        // TODO: is this needed?
        for(int i=0; i<gameLevel.getCurrentAgents().size(); i++){
            gameLevel.removeAgent(i);
        }
    }

    private void makeFromExistingWrapper() {
        if (toolbarPane.getExistingLevelValue() != -1) {
            makeLevel(toolbarPane.getMaxLevel() + 1, new MapState(map.getStateMapping().get(toolbarPane.getExistingLevelValue()), map), true);
        }
    }

    private void makeLevel(int newLevel, MapState state, boolean fromExisting) {
        String newLevelDisplay;
        if (fromExisting) {
            newLevelDisplay = "Level " + newLevel + " created from Level: " + toolbarPane.getExistingLevelValue();
        } else {
            newLevelDisplay = "Level " + newLevel + " created";
        }
        map.setLevel(newLevel);
        toolbarPane.setMaxLevel(newLevel);
        toolbarPane.addToExistingLevelCreator(newLevel);
        consolePane.displayMessage(newLevelDisplay, ConsolePane.Level.NEUTRAL);
        if (!map.getStateMapping().containsKey(newLevel)) {
            map.getStateMapping().put(newLevel, state);

            ILevelDefinition level = context.getGameFactory().createLevel();
            context.getState().addLevel(level);
            // TODO: add code to add Level contents


            map.getCurrentState().accessSelectCount(countProperty -> establishSelectCountListener(countProperty));
            MapState revertToState = map.getStateMapping().get(newLevel);
            revertToState.updateMap(map);
        }
        int currentSpinnerValue = (int)(double)toolbarPane.getLevelChanger().getValue();
        toolbarPane.updateSpinner(currentSpinnerValue, newLevel);
    }

    private void changeToExistingLevel(int newValue) {
        if (map.getStateMapping().containsKey(newValue)) {
            map.setLevel(newValue);
            MapState revertToState = map.getStateMapping().get(newValue);
            revertToState.updateMap(map);
        }
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
