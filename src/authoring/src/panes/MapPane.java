package panes;

import frontend_objects.AgentView;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class MapPane extends AuthoringPane {

    private ArrayList<AgentView> agentList;
    private Pane mapPane;

    public MapPane(){
        super();
        initMapPane();
        getContentChildren().add(mapPane);
    }

    public void addAgent(AgentView agent){
        agentList.add(agent);
        getContentChildren().add(agent.getView());
    }

    private void initMapPane(){
        mapPane = new Pane();
    }

    @Override
    public void setStylesheet(String url) {

    }
}
