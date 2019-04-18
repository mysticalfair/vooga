package panes;

import frontend_objects.AgentView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class MapPane extends AuthoringPane {

    public static final double DEFAULT_WIDTH = AuthoringEnvironment.MAP_WIDTH;
    public static final double DEFAULT_HEIGHT = AuthoringEnvironment.MIDDLE_ROW_HEIGHT;
    private static final String[] IMAGE_EXTENSIONS = {"*.jpg", "*.gif", "*.jpeg", "*.bmp"};
    private static final String IMAGE_FILE = "Image File";
    private static final String MAP_IMAGE_ERROR = "Failed to load background for map.";
    private static final String STYLE = "map-pane.css";
    private static final String STYLE_ID = "general";


    private List<AgentView> agentList;
    private Pane mapPane;

    public MapPane(){
        super();
        initMapPane();
        getContentChildren().add(mapPane);
        mapPane.getStylesheets().add(STYLE);
        mapPane.setId(STYLE_ID);
    }

    public void addAgent(AgentView agent){
        agentList.add(agent);
        getContentChildren().add(agent);
        System.out.println("Added: new size is " + agentList.size());
    }

    public void removeAgent(AgentView view) {
        agentList.remove(view);
        System.out.println("Removed: new size is " + agentList.size());
    }

    public int getAgentCount() {
        return agentList.size();
    }

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
        getContentChildren().add(sprite);
    }

    /**
     * Called from Tools to remove Tool image from display
     * @param sprite
     */
    public void removeShape(Shape sprite){
        getContentChildren().remove(sprite);
    }

    private void initMapPane(){
        mapPane = new Pane();
        agentList = new ArrayList<>();
        mapPane.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void formatBackground(){
        // TODO: replace System.err.println with Console display
        AuthoringUtil.openFileChooser(
                IMAGE_FILE, IMAGE_EXTENSIONS, false, null,
                file -> setMapImage(file.toURI().toString()),
                () -> System.err.println(MAP_IMAGE_ERROR)
        );
    }

    /**
     * Assumes input from formatBackground method, which gives exclusively valid correct file names
     * @param fileName
     */
    private void setMapImage(String fileName){
        var mapImage = new Image(fileName);
        //BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        //BackgroundImage backgroundImage = new BackgroundImage(mapImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        mapPane.setStyle(
                "-fx-background-image: url(" +
                        fileName +
                        "); " +
                        "-fx-background-size: cover;"
        );
        //mapPane.setBackground(new Background(backgroundImage));
    }

    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void updateSize(double width, double height) {
        mapPane.setPrefSize(width, height);
    }
}
