package panes;

import frontend_objects.AgentView;
import frontend_objects.DraggableAgentView;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.List;

public class MapPane extends AuthoringPane {

    private List<DraggableAgentView> agentList;
    private Pane mapPane;

    public MapPane(){
        super();
        initMapPane();
        getContentChildren().add(mapPane);
    }

    public void addAgent(DraggableAgentView agent){
        agentList.add(agent);
        mapPane.getChildren().add(agent);
    }

    public void removeAgent(DraggableAgentView agent) {
        agentList.remove(agent);
    }

    private void initMapPane(){
        mapPane = new StackPane();
        agentList = new ArrayList<>();
    }

    @Override
    public void setStylesheet(String url) {

    }
}
