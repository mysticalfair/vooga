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
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import panes.attributes.AttributesPane;

public class AuthoringEnvironment extends Application {
    public static final String TITLE = "Electric Voogaloo!";
    public static final double DEFAULT_WIDTH = 1200;
    public static final double DEFAULT_HEIGHT = 650;
    public static final String MENU_ITEM_UPLOAD = "Upload Image To Background";
    public static final String MENU_ITEM_SAVE = "Save Game";
    public static final String MENU_ITEM_OPEN = "Open Game";
    public static final double TOOLBAR_HEIGHT = 70;
    public static final double CONSOLE_HEIGHT = DEFAULT_HEIGHT/5;
    public static final double MIDDLE_ROW_HEIGHT = DEFAULT_HEIGHT - TOOLBAR_HEIGHT - CONSOLE_HEIGHT;
    public static final double ATTRIBUTES_WIDTH = DEFAULT_WIDTH/4;
    public static final double AGENT_WIDTH = DEFAULT_WIDTH/7;
    public static final double MAP_WIDTH = DEFAULT_WIDTH - ATTRIBUTES_WIDTH - AGENT_WIDTH;

    private StackPane stackPane;
    private BorderPane borderPane;
    private ConsolePane consolePane;
    private AgentPane agentPane;
    private AttributesPane attributesPane;
    private ToolbarPane toolbarPane;
    private MapPane map;
    private Scene scene;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stackPane = new StackPane();
        borderPane = new BorderPane();
        stackPane.getChildren().add(borderPane);
        scene = new Scene(stackPane, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        initAllPanes();
        initStage(stage);
    }

    private void initAllPanes() {
        initMapPane();
        initAttributesPane();
        initConsolePane();
        initToolbarPane();
        initAgentPane();
    }

    private void initMapPane() {
        map = new MapPane();
        map.accessContainer(borderPane::setCenter);
    }

    private void initAgentPane() {
        agentPane = new AgentPane();
        agentPane.accessContainer(borderPane::setRight);
        agentPane.addButton("add-button.png", 25, 10, e -> System.out.println("handle press method goes here"));
        for (CloneableAgentView o : agentPane.getAgentList()) {
            o.setId("img");
            o.setOnMousePressed(e -> mousePressedOnClone(e, o));
        }
    }

    private void initAttributesPane() {
        attributesPane = new AttributesPane();
        attributesPane.accessContainer(borderPane::setLeft);
    }

    private void initConsolePane() {
        consolePane = new ConsolePane();
        consolePane.accessContainer(borderPane::setBottom);
        //consolePane.addButton("set background", e -> map.formatBackground());
    }

    private void initToolbarPane() {
        toolbarPane = new ToolbarPane(map, scene);
        toolbarPane.accessContainer(borderPane::setTop);
        toolbarPane.addButton(toolbarPane.LASSO_IMAGE, e -> consolePane.displayConsoleMessage("Multi-select tool enabled"));
        toolbarPane.addButton(toolbarPane.PEN_IMAGE, e -> consolePane.displayConsoleMessage("Path drawing tool enabled"));
        toolbarPane.addAction("File", MENU_ITEM_UPLOAD, e -> map.formatBackground());
        toolbarPane.addAction("File", MENU_ITEM_SAVE, null);
        toolbarPane.addAction("File", MENU_ITEM_OPEN, null);
    }

    private void mousePressedOnClone(MouseEvent e, CloneableAgentView agent) {
        if (e.getClickCount() == 2) {
            DraggableAgentView copy = new DraggableAgentView(agent);
            map.addAgent(copy);
            consolePane.displayConsoleMessage("Agent added to map. Agent count on map: " + map.getAgentCount());
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
        if (trashIntersect(draggableAgent)) {
            draggableAgent.setEffect(setLighting(Color.RED));
        } else if (outOfBounds(draggableAgent)) {
            draggableAgent.setEffect(setLighting(Color.WHITE));
        } else {
            draggableAgent.setEffect(null);
        }
    }

    private Lighting setLighting(Color color) {
        Lighting lighting = new Lighting();
        lighting.setDiffuseConstant(1.0);
        lighting.setSpecularConstant(0.0);
        lighting.setSpecularExponent(0.0);
        lighting.setSurfaceScale(0.0);
        lighting.setLight(new Light.Distant(45, 45, color));
        return lighting;
    }

    private void mouseReleased(DraggableAgentView draggableAgent) {
        if (trashIntersect(draggableAgent)) {
            draggableAgent.setImage(null);
            map.removeAgent(draggableAgent);
            consolePane.displayConsoleMessage("Agent discarded from map. Agent count on map: " + map.getAgentCount());
        } else if (outOfBounds(draggableAgent)) {
            draggableAgent.setEffect(null);
            draggableAgent.setTranslateX(draggableAgent.getStartX());
            draggableAgent.setTranslateY(draggableAgent.getStartY());
            consolePane.displayConsoleMessage("Agent out of bounds: returning to original location");
        }
    }

    private boolean outOfBoundsHorizontal(DraggableAgentView draggableAgent) {
        double xPos = draggableAgent.getTranslateX();
        double xPosRight = draggableAgent.getTranslateX() + draggableAgent.getFitWidth();
        boolean rightOutOfBounds = xPosRight > borderPane.getWidth() - attributesPane.getWidth() - agentPane.getVBoxContainer().getWidth();
        boolean leftOutOfBounds = xPos < 0;
        return leftOutOfBounds || rightOutOfBounds;
    }

    private boolean outOfBoundsVertical(DraggableAgentView draggableAgent) {
        double yPos = draggableAgent.getTranslateY();
        double yPosBot = draggableAgent.getTranslateY() + draggableAgent.getFitHeight();
        boolean topOutOfBounds = yPos < 0;
        boolean botOutOfBounds = yPosBot > borderPane.getHeight() - TOOLBAR_HEIGHT - CONSOLE_HEIGHT;
        return topOutOfBounds || botOutOfBounds;
    }

    private boolean outOfBounds(DraggableAgentView draggableAgent) {
        return outOfBoundsHorizontal(draggableAgent) || outOfBoundsVertical(draggableAgent);
    }

    private boolean trashIntersect(DraggableAgentView draggableAgentView) {
        double yPos = draggableAgentView.getTranslateY();
        double xPosRight = draggableAgentView.getTranslateX() + draggableAgentView.getFitWidth();
        boolean topOutOfBounds = yPos < 0;
        boolean rightOutOfBounds = xPosRight > borderPane.getWidth() - attributesPane.getWidth() - agentPane.getVBoxContainer().getWidth();
        return topOutOfBounds && rightOutOfBounds;
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
        stage.getScene().getStylesheets().add("Midpoint.css");
        stage.show();
    }
}
