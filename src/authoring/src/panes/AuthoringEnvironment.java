package panes;

import authoring.GameFactory;
import frontend_objects.CloneableAgentView;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
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
        scene = new Scene(stackPane, context.getDouble("DefaultWidth"), context.getDouble("DefaultHeight"));
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
        initAttributesPane();
        initConsolePane();
        initMapPane(1);
        initToolbarPane();
        initAgentPane();
    }

    private void initMapPane(int level) {
        map = new MapPane(context, consolePane);
        map.accessContainer(borderPane::setCenter);
        map.getStateMapping().put(level, new MapState(null, new ArrayList<>()));
        map.setLevel(level);
        map.getCurrentState().accessSelectCount(countProperty -> establishSelectCountListener(countProperty));
    }

    private void establishSelectCountListener(SimpleIntegerProperty selectCount) {
        selectCount.addListener((observable, oldValue, newValue) -> map.handleSelectionChange((int)newValue));
    }

    private void initAgentPane() {
        agentPane = new AgentPane(context);
        agentPane.accessContainer(borderPane::setRight);
        agentPane.addButton(context.getString("AddButtonImageFile"), context.getDouble("ButtonSize"), context.getDouble("ButtonImageSize"), e -> attributesPane.createNewAgentForm());
        for (CloneableAgentView o : agentPane.getAgentList()) {
            o.setId(context.getString("ButtonStyle"));
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

        toolbarPane.addAction("File", context.getString("MenuItemUpload"), e -> map.formatBackground());
        toolbarPane.addAction("File", context.getString("MenuItemSave"), e -> context.getGame().saveState(context.getString("GameSaveName")));
        // TODO: implement loading an old game
        toolbarPane.addAction("File", context.getString("MenuItemOpen"), null);
        toolbarPane.getLevelChanger().valueProperty().addListener((obs, oldValue, newValue) -> changeLevel((int)((double) newValue)));
    }

    private void changeLevel(int newValue) {
        map.setLevel(newValue);
        if (!map.getStateMapping().containsKey(newValue)) {
            map.getMapPane().getChildren().clear();
            map.getStateMapping().put(newValue, new MapState(null, new ArrayList<>()));
            map.getCurrentState().accessSelectCount(countProperty -> establishSelectCountListener(countProperty));
        } else {
            MapState revertToState = map.getStateMapping().get(newValue);
            revertToState.updateMap(map);
        }
    }

    private void updateDimensions(double width, double height){
        var middleWidth = width - context.getDouble("AttributesWidth") - context.getDouble("AgentWidth");
        var middleHeight = height - context.getDouble("ConsoleHeight") - context.getDouble("ToolbarPaneHeight") - context.getDouble("MiddleRowPadding");
        consolePane.updateSize(width, context.getDouble("ConsoleHeight"));
        toolbarPane.updateSize(width, context.getDouble("ToolbarPaneHeight"));
        map.updateSize(middleWidth, middleHeight);
        attributesPane.updateSize(context.getDouble("AttributesWidth"), middleHeight);
        agentPane.updateSize(context.getDouble("AgentWidth"), middleHeight);
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
