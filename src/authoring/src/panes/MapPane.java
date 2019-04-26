package panes;

import frontend_objects.AgentView;
import frontend_objects.DraggableAgentView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Shape;
import util.AuthoringContext;
import util.AuthoringUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MapPane extends AuthoringPane {

    public static final double DEFAULT_WIDTH = AuthoringEnvironment.MAP_WIDTH;
    public static final double DEFAULT_HEIGHT = AuthoringEnvironment.MIDDLE_ROW_HEIGHT;
    public static final double MAP_WIDTH = AuthoringEnvironment.MAP_WIDTH - 100;
    public static final double MAP_HEIGHT = AuthoringEnvironment.MIDDLE_ROW_HEIGHT - 100;

    public static final String[] IMAGE_EXTENSIONS = {"*.jpg", "*.gif", "*.jpeg", "*.bmp"};
    public static final String STYLE = "map-pane.css";
    public static final String STYLE_ID = "general";

    private Pane mapPane;
    private StackPane overallPane;
    private int level;
    private ConsolePane console;
    private Map<Integer, MapState> levelToState;
    private SimpleBooleanProperty selection = new SimpleBooleanProperty();

    public MapPane(AuthoringContext context, ConsolePane console) {
        super(context);
        this.console = console;
        selection.set(false);
        //selection.addListener((observable, oldValue, newValue) -> handleSelection(newValue));
        levelToState = new HashMap<>();
        initPanes();
        getContentChildren().add(overallPane);
    }

    public void accessMap(Consumer<Pane> accessMethod) {
        accessMethod.accept(mapPane);
    }

    public Map<Integer, MapState> getStateMapping() {
        return levelToState;
    }

    public void handleSelectionChange(int newValue) {
        MapState currentLevel = levelToState.get(level);
        if (newValue > 0) {
            for (DraggableAgentView agent : currentLevel.getAgents()) {
                if (agent.getSelect()) {
                    agent.setOnMousePressed(event -> clickMultiple(event));
                    agent.setOnMouseDragged(event -> dragMultiple(event));
                    agent.setOnMouseReleased(event -> releaseMultiple());
                }
            }
        } else {
            for (DraggableAgentView agent: currentLevel.getAgents()) {
                agent.setOnMousePressed(event -> agent.mousePressed(event));
                agent.setOnMouseDragged(event -> agent.mouseDragged(event, this));
                agent.setOnMouseReleased(event -> agent.mouseReleased(this, console));
            }
        }
    }

    private void clickMultiple(MouseEvent event) {
        MapState currentLevel = levelToState.get(level);
        for (DraggableAgentView agent : currentLevel.getAgents()) {
            if (agent.getSelect()) {
                agent.mousePressed(event);
            }
        }
    }

    private void dragMultiple(MouseEvent event) {
        MapState currentLevel = levelToState.get(level);
        for (DraggableAgentView agent : currentLevel.getAgents()) {
            if (agent.getSelect()) {
                agent.mouseDragged(event, this);
            }
        }
    }

    private void releaseMultiple() {
        MapState currentLevel = levelToState.get(level);
        for (DraggableAgentView agent: currentLevel.getAgents()) {
            if (agent.getSelect()) {
                agent.mouseReleased(this, console);
            }
        }
    }

    public MapState getCurrentState() {
        return levelToState.get(level);
    }

    private void initPanes() {
        overallPane = initOverallPane();
        mapPane = new Pane();
        mapPane.setMaxSize(MAP_WIDTH, MAP_HEIGHT);
        mapPane.setMinSize(MAP_WIDTH, MAP_HEIGHT);
        overallPane.getChildren().add(mapPane);
    }

    private StackPane initOverallPane(){
        var stack = new StackPane();
        stack.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        stack.getStylesheets().add(STYLE);
        stack.setId(STYLE_ID);
        stack.setAlignment(Pos.CENTER);
        return stack;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getPaneWidth() {
        return overallPane.getWidth();
    }

    /**
     *
     * @param agent
     */
    public void addAgent(int level, DraggableAgentView agent){
        MapState currentLevel = levelToState.get(level);
        currentLevel.addToAgents(agent);
        mapPane.getChildren().add(agent);
    }

    /**
     *
     * @param view
     */
    public void removeAgent(AgentView view) {
        MapState currentLevel = levelToState.get(level);
        currentLevel.removeAgent(view);
        System.out.println("Removed: new size is " + levelToState.get(level).getAgentCount());
    }

    /**
     *
     * @return
     */
    public int getAgentCount() {
        MapState currentLevel = levelToState.get(level);
        return currentLevel.getAgentCount();
    }

    /**
     * Called from Tools to add Tool image to display
     * @param sprite
     */
    public void spawnShape(Shape sprite){
        mapPane.getChildren().add(sprite);
    }

    /**
     * Called from Tools to remove Tool image from display
     * @param sprite
     */
    public void removeShape(Shape sprite){
        if(mapPane.getChildren().contains(sprite)){
            mapPane.getChildren().remove(sprite);
        }
    }

    /**
     *
     */
    public void formatBackground(){
        // TODO: replace System.err.println with Console display
        AuthoringUtil.openFileChooser(
                getContext().getString("ImageFile"), IMAGE_EXTENSIONS, false, null,
                file -> setMapImage(level, file.toURI().toString()),
                () -> getContext().displayConsoleMessage(getContext().getString("MapImageLoadError"), ConsolePane.Level.ERROR)
        );
    }

    /**
     * Assumes input from formatBackground method, which gives exclusively valid correct file names
     * @param fileName
     */
    public void setMapImage(int level, String fileName){
        levelToState.get(level).setBackgroundURL(fileName);
        mapPane.setStyle(
                "-fx-background-image: url(" +
                        fileName +
                        "); " +
                        "-fx-background-size: cover;"
        );
    }

    public void clearMap() {
        mapPane.getChildren().clear();
    }

    public Pane getMapPane() {
        return mapPane;
    }


    @Override
    public void setStylesheet(String url) {

    }

    /**
     *
     * @param width
     * @param height
     */
    @Override
    public void updateSize(double width, double height) {
        overallPane.setPrefSize(width, height);
    }
}
