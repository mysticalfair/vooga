package panes;

import frontend_objects.CloneableAgentView;
import frontend_objects.DraggableAgentView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AuthoringEnvironment extends Application {

    static final String TITLE = "Vooginas!";
    static final double DEFAULT_WIDTH = 1000;
    static final double DEFAULT_HEIGHT = 600;

    private StackPane stackPane;
    private BorderPane borderPane;
    private ConsolePane consolePane;
    private AgentPane agentPane;
    private AttributesPane attributesPane;
    private MapPane map;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stackPane = new StackPane();
        borderPane = new BorderPane();
        stackPane.getChildren().add(borderPane);
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
        for (CloneableAgentView o : agentPane.getAgentList()) {
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
        consolePane.addButton("set background", e -> map.formatBackground());
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
        if (outOfBounds(view)) {
            view.setImage(null);
            map.removeAgent(view);
        }
    }

    private boolean outOfBounds(DraggableAgentView view) {
        double xPos = view.getTranslateX() + view.getFitWidth();
        double attributesWidth = attributesPane.getVBoxContainer().getWidth();
        double agentPanelWidth = agentPane.getContainerVBox().getWidth();
        boolean rightOutOfBounds = xPos > AuthoringEnvironment.DEFAULT_WIDTH - attributesWidth - agentPanelWidth;
        boolean leftOutOfBounds = xPos < 0;
        return leftOutOfBounds || rightOutOfBounds;
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