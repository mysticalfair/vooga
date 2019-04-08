package panes;

import frontend_objects.AgentView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class MapPane extends AuthoringPane {

    public static final double DEFAULT_X = 700.0;
    public static final double DEFAULT_Y = 700.0;

    private ArrayList<AgentView> agentList;
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

    private void initMapPane(){
        mapPane = new Pane();
        mapPane.setPrefSize(DEFAULT_X, DEFAULT_Y);
    }

    public void formatBackground(){
        Image image = new ImageSelector().getUserImage();
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        mapPane.setBackground(new Background(backgroundImage));
    }

    @Override
    public void setStylesheet(String url) {

    }
}
