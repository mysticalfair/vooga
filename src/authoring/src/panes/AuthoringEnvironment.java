package panes;

import frontend_objects.AgentView;
import frontend_objects.DraggableAgentView;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class AuthoringEnvironment extends Application {

    static final String TITLE = "Vooginas!";
    static final double DEFAULT_WIDTH = 1000;
    static final double DEFAULT_HEIGHT = 600;

    private StackPane stackPane;
    private BorderPane borderPane;
    private ConsolePane consolePane;
    private AgentPane agentPane;
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

    private void initAttributesPane() {
        AttributesPane attributesPane = new AttributesPane();
        attributesPane.accessContainer(node -> borderPane.setLeft(node));
    }

    private void initConsolePane() {
        consolePane = new ConsolePane();
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