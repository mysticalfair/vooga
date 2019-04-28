package panes;

import frontend_objects.AgentView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import panes.attributes.AttributesPane;

import java.util.ArrayList;
import java.util.List;

public class MapPane extends AuthoringPane {

    public static final double DEFAULT_WIDTH = AuthoringEnvironment.DEFAULT_WIDTH - AttributesPane.WIDTH - AgentPane.WIDTH;
    public static final double DEFAULT_HEIGHT = AuthoringEnvironment.DEFAULT_HEIGHT - ConsolePane.HEIGHT - ToolbarPane.HEIGHT;

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
    }

    public void removeAgent(AgentView view) {
        agentList.remove(view);
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
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        mapPane.setBackground(new Background(backgroundImage));
    }

    @Override
    public void setStylesheet(String url) {

    }
}
