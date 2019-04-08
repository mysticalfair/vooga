package panes;

import frontend_objects.AgentView;
import frontend_objects.CloneableAgentView;
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
    private AttributesPane attributesPane;
    private Pane handlePane;
    private MapPane map;
    //private Canvas map;
    private List<CloneableAgentView> agentInventory;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stackPane = new StackPane();
        borderPane = new BorderPane();
        stackPane.getChildren().add(borderPane);
        handlePane = new Pane();
        //map = new Canvas(250, 250);
        //GraphicsContext gc = map.getGraphicsContext2D();
        //gc.fillRect(0,0,400,400);
        //gc.setFill(Color.BLACK);
        //borderPane.setCenter(map);
        initAllPanes();
        initStage(stage);
    }

    private void initAllPanes() {
        initMapPane();
        initConsolePane();
        initAttributesPane();
        initAgentPane();
    }

    private void initMapPane() {
        map = new MapPane();
        map.accessContainer(node -> borderPane.setCenter(node));
    }

    private void initAgentPane() {
        agentPane = new AgentPane();
        agentPane.accessContainer(node -> borderPane.setRight(node));
        agentInventory = agentPane.getAgentList();
        for (CloneableAgentView o : agentInventory) {
            o.setOnMousePressed(e -> mousePressedOnClone(o));
        }
    }

    private void initAttributesPane() {
        attributesPane = new AttributesPane();
        attributesPane.accessContainer(node -> borderPane.setLeft(node));
    }

    private void initConsolePane() {
        consolePane = new ConsolePane();
        consolePane.accessContainer(node -> borderPane.setBottom(node));
    }

    private void mousePressedOnClone(CloneableAgentView agent) {
        DraggableAgentView copy = new DraggableAgentView(agent);
        map.addAgent(copy);
        setMouseActions(copy);

    }

    private void setMouseActions(DraggableAgentView view){
        view.setOnMousePressed(mouseEvent -> mousePressed(mouseEvent, view));
        view.setOnMouseDragged(mouseEvent -> mouseDragged(mouseEvent, view));
        view.setOnMouseReleased(mouseEvent -> mouseReleased(view));
    }

    private void mousePressed(MouseEvent event, DraggableAgentView view) {
        view.setMyStartSceneX(event.getSceneX());
        view.setMyStartSceneY(event.getSceneY());
        view.setMyStartXOffset(((DraggableAgentView)(event.getSource())).getTranslateX());
        view.setMyStartYOffset(((DraggableAgentView)(event.getSource())).getTranslateY());
    }

    private void mouseDragged(MouseEvent event, DraggableAgentView view) {
        double offsetX = event.getSceneX() - view.getMyStartSceneX();
        double offsetY = event.getSceneY() - view.getMyStartSceneY();
        double newTranslateX = view.getStartX() + offsetX;
        double newTranslateY = view.getStartY() + offsetY;
        ((DraggableAgentView)(event.getSource())).setTranslateX(newTranslateX);
        ((DraggableAgentView)(event.getSource())).setTranslateY(newTranslateY);
    }

    private void mouseReleased(DraggableAgentView view) {
        System.out.println(view.getTranslateX() + " " + view.getTranslateY());
        if (view.getTranslateX() < 0 || rightOutOfBounds(view) ) {
            view.setImage(null);
            map.removeAgent(view);
        }
    }

    private boolean rightOutOfBounds(DraggableAgentView view) {
        double xPos = view.getTranslateX() + view.getFitWidth();
        double attributesWidth = attributesPane.getVBoxContainer().getWidth();
        double agentPanelWidth = agentPane.getContainerVBox().getWidth();
        return (xPos > AuthoringEnvironment.DEFAULT_WIDTH - attributesWidth - agentPanelWidth);
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