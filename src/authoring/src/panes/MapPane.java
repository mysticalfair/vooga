package panes;

import frontend_objects.AgentView;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Ellipse;

import java.util.ArrayList;
import java.util.List;

public class MapPane extends AuthoringPane {

    public static final double DEFAULT_WIDTH = AuthoringEnvironment.MAP_WIDTH;
    public static final double DEFAULT_HEIGHT = AuthoringEnvironment.MIDDLE_ROW_HEIGHT;

    private List<AgentView> agentList;
    private Pane mapPane;

    public MapPane(){
        super();
        initMapPane();
        getContentChildren().add(mapPane);
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

    public void selectAgents(Ellipse lassoEllipse){
        for(AgentView agent: agentList){
            agent.setSelect(lassoSelects(lassoEllipse, agent));
        }
    }

    private boolean lassoSelects(Ellipse lassoEllipse, AgentView agent){
        return lassoEllipse.intersects(agent.getBoundsInParent());
    }

    private void initMapPane(){
        mapPane = new Pane();
        agentList = new ArrayList<>();
        mapPane.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void formatBackground(){
        Image image = new ImageSelector().getUserImage();
        if(image == null){
            return;
        }
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        mapPane.setBackground(new Background(backgroundImage));
    }

    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void updateSize(double width, double height) {
        mapPane.setPrefSize(width, height);
    }
}
