package panes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AuthoringEnvironment extends Application {

    public static final String TITLE = "Vooginas!";
    public static final double DEFAULT_WIDTH = 1000;
    public static final double DEFAULT_HEIGHT = 600;
    public static final double DEFAULT_BACKGROUND_WIDTH = 200;
    public static final double DEFAULT_BACKGROUND_HEIGHT = 200;


    private StackPane stackPane;
    private BorderPane borderPane;
    private ConsolePane consolePane;
    private AgentPane agentPane;
    private MapPane mapPane;
    private Pane handlePane;
    private Canvas map;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stackPane = new StackPane();
        borderPane = new BorderPane();
        stackPane.getChildren().add(borderPane);
        handlePane = new Pane();
        map = new Canvas(250, 250);
        GraphicsContext gc = map.getGraphicsContext2D();
        gc.fillRect(75,75,250,250);
        gc.setFill(Color.BLACK);
        borderPane.setCenter(map);
        initAllPanes();
        initStage(stage);
    }

    private void initAllPanes() {
        initConsolePane();
        initAttributesPane();
        initAgentPane();
        initMapPane();
    }

    /*
    When any Agent is clicked, it should set off a procedure:
        Store initial location
        Remove DraggableImage from AgentPane, add to Handle pane (?)
     */
    /**
     * Accepts a list of roots from any pane the ImageView used to be located/needs to be removed from
     * Also accepts a list of roots from any pane the ImageView should be added to
     *
     */
    /*
    public void transferAgentView(AgentView agentView, List<ObservableList<Node>> fromGroups, Map<Point2D,ObservableList<Node>> toGroupsMap){
        for(ObservableList<Node> group: fromGroups){
            group.remove(agentView.getView());
        }
        for(Map.Entry<Point2D, ObservableList<Node>> entry: toGroupsMap.entrySet()){
            AgentView newView = new AgentView(agentView);
            newView.getView().setX(entry.getKey().getX());
            newView.getView().setY(entry.getKey().getY());
            entry.getValue().add(newView.getView());
        }
    }*/

    private void initAgentPane() {
        agentPane = new AgentPane();
        agentPane.accessContainer(node -> borderPane.setRight(node));
    }

    private void initMapPane() {
        mapPane = new MapPane();
        mapPane.accessContainer(node -> borderPane.setCenter(node));
    }

    private void initAttributesPane() {
        AttributesPane attributesPane = new AttributesPane();
        attributesPane.accessContainer(node -> borderPane.setLeft(node));
    }

    private void initConsolePane() {
        consolePane = new ConsolePane();
        consolePane.addButton("Set background", e -> mapPane.formatBackground());
        consolePane.accessContainer(node -> borderPane.setBottom(node));
    }

    private void initStage(Stage stage) {
        Scene mainScene = new Scene(stackPane, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(mainScene);
        stage.setMinWidth(DEFAULT_WIDTH);
        stage.setMinHeight(DEFAULT_HEIGHT);
        stage.getScene().getStylesheets().add("Blue.css");
        stage.show();
    }

}
