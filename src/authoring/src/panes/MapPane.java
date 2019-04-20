package panes;

import frontend_objects.AgentView;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import util.AuthoringContext;
import util.AuthoringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private List<AgentView> agentList;
    private Pane mapPane;
    private StackPane overallPane;
    private int level;
    private Map<Integer, MapState> levelToState;

    public MapPane(AuthoringContext context) {
        super(context);
        agentList = new ArrayList<>();
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

    private void initPanes(){
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
    public void addAgent(int level, AgentView agent){
        levelToState.get(level).addToAgents(agent);
        //levelToState.put(level, levelToState.get(level));
        mapPane.getChildren().add(agent);
    }

    /**
     *
     * @param view
     */
    public void removeAgent(AgentView view) {
        agentList.remove(view);
        System.out.println("Removed: new size is " + agentList.size());
    }

    /**
     *
     * @return
     */
    public int getAgentCount() {
        return agentList.size();
    }

    /**
     *
     * @param lassoEllipse
     */
    public void selectAgents(Ellipse lassoEllipse){
        for(AgentView agent: agentList){
            agent.setSelect(lassoSelects(lassoEllipse, agent));
        }
    }

    private boolean lassoSelects(Ellipse lassoEllipse, AgentView agent){
        return lassoEllipse.intersects(agent.getBoundsInParent());
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
