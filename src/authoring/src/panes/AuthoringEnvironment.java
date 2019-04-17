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
    public static final double TOOLBAR_HEIGHT = DEFAULT_HEIGHT/7;
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
        map.accessContainer(node -> borderPane.setCenter(node));
        map.accessContainer(borderPane::setCenter);
    }

    private void initAgentPane() {
        agentPane = new AgentPane();
        agentPane.accessContainer(borderPane::setRight);
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
        toolbarPane = new ToolbarPane();
        toolbarPane.accessContainer(borderPane::setTop);
        var lasso = new LassoTool(map);
        var pen = new PathPenTool(map);
        toolbarPane.addButton(toolbarPane.LASSO_IMAGE, 25, 10, e -> selectToolAction(lasso, scene));
        toolbarPane.addButton(toolbarPane.PEN_IMAGE, 25, 10, e -> pen.togglePenSelect(scene));
        toolbarPane.addAction("File", MENU_ITEM_UPLOAD, e -> map.formatBackground());
        toolbarPane.addAction("File", MENU_ITEM_SAVE, null);
        toolbarPane.addAction("File", MENU_ITEM_OPEN, null);
    }

    private void selectToolAction(LassoTool lasso, Scene thisScene){
        consolePane.displayConsoleMessage("Multi-select tool enabled");
        lasso.setMouseActions(thisScene);
    }

    private void mousePressedOnClone(MouseEvent e, CloneableAgentView agent) {
        if (e.getClickCount() == 2) {
            DraggableAgentView copy = new DraggableAgentView(agent);
            map.addAgent(copy);
            consolePane.displayConsoleMessage("Agent added");
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
        lighting.setLight(new Light.Distant(45, 45, Color.WHITE));
        return lighting;
    }

    private void mouseReleased(DraggableAgentView draggableAgent) {
        System.out.println(draggableAgent.getTranslateX() + " " + draggableAgent.getTranslateY());
        if (outOfBounds(draggableAgent)) {
            //draggableAgent.setImage(null);
            //map.removeAgent(draggableAgent);
            draggableAgent.setEffect(null);
            draggableAgent.setTranslateX(draggableAgent.getStartX());
            draggableAgent.setTranslateY(draggableAgent.getStartY());
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
        Scene mainScene = new Scene(stackPane, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        scene = mainScene;
        stage.setTitle(TITLE);
        stage.setScene(mainScene);
        stage.setMinWidth(DEFAULT_WIDTH);
        stage.setMinHeight(DEFAULT_HEIGHT);
        mainScene.widthProperty().addListener((observable, oldvalue, newvalue) -> updateDimensions((double) newvalue, mainScene.getHeight()));
        mainScene.heightProperty().addListener((observable, oldvalue, newvalue) -> updateDimensions(mainScene.getWidth (), (double) newvalue));
        stage.getScene().getStylesheets().add("Midpoint.css");
        stage.show();
    }
}
