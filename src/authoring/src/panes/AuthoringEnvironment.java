package panes;

import frontend_objects.CloneableAgentView;
import frontend_objects.DraggableAgentView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
            o.setOnMousePressed(e -> mousePressedOnClone(e, o));
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

    private void mousePressedOnClone(MouseEvent e, CloneableAgentView agent) {
        if (e.getClickCount() == 2) {
            DraggableAgentView copy = new DraggableAgentView(agent);
            map.addAgent(copy);
            setMouseActionsForDrag(copy);
        } else {
            // code to open up attributes pane.
        }
    }

    private void setMouseActionsForDrag(DraggableAgentView draggableAgent){
        draggableAgent.setOnMousePressed(mouseEvent -> mousePressed(mouseEvent, draggableAgent));
        draggableAgent.setOnMouseDragged(mouseEvent -> mouseDragged(mouseEvent, draggableAgent));
        draggableAgent.setOnMouseReleased(mouseEvent -> mouseReleased(draggableAgent));
    }

    private void mousePressed(MouseEvent event, DraggableAgentView draggableAgent) {
        draggableAgent.setMyStartSceneX(event.getSceneX());
        draggableAgent.setMyStartSceneY(event.getSceneY());
        draggableAgent.setMyStartXOffset(((DraggableAgentView)(event.getSource())).getTranslateX());
        draggableAgent.setMyStartYOffset(((DraggableAgentView)(event.getSource())).getTranslateY());
    }

    private void mouseDragged(MouseEvent event, DraggableAgentView draggableAgent) {
        double offsetX = event.getSceneX() - draggableAgent.getMyStartSceneX();
        double offsetY = event.getSceneY() - draggableAgent.getMyStartSceneY();
        double newTranslateX = draggableAgent.getStartX() + offsetX;
        double newTranslateY = draggableAgent.getStartY() + offsetY;
        ((DraggableAgentView)(event.getSource())).setTranslateX(newTranslateX);
        ((DraggableAgentView)(event.getSource())).setTranslateY(newTranslateY);
        if (outOfBounds(draggableAgent)) {
            draggableAgent.setEffect(setLighting());
        } else {
            draggableAgent.setEffect(null);
        }
    }

    private Lighting setLighting() {
        Lighting lighting = new Lighting();
        lighting.setDiffuseConstant(1.0);
        lighting.setSpecularConstant(0.0);
        lighting.setSpecularExponent(0.0);
        lighting.setSurfaceScale(0.0);
        lighting.setLight(new Light.Distant(45, 45, Color.RED));
        return lighting;
    }

    private void mouseReleased(DraggableAgentView draggableAgent) {
        System.out.println(draggableAgent.getTranslateX() + " " + draggableAgent.getTranslateY());
        if (outOfBounds(draggableAgent)) {
            draggableAgent.setImage(null);
            map.removeAgent(draggableAgent);
        }
    }

    private boolean outOfBounds(DraggableAgentView draggableAgent) {
        double xPos = draggableAgent.getTranslateX();
        double xPosRight = draggableAgent.getTranslateX() + draggableAgent.getFitWidth();
        double attributesWidth = attributesPane.getVBoxContainer().getWidth();
        double agentPanelWidth = agentPane.getVBoxContainer().getWidth();
        boolean rightOutOfBounds = xPosRight > borderPane.getWidth() - attributesWidth - agentPanelWidth;
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