package panes;

import frontend_objects.AgentView;
import frontend_objects.DraggableAgentView;
import java.util.List;

public class MapState {

    private String backgroundURL;
    private List<DraggableAgentView> agents;
    private boolean selectionOccurred;
    private MapPane map;

    public MapState(String backgroundURL, List<DraggableAgentView> agents, MapPane map) {
        this.backgroundURL = backgroundURL;
        this.agents = agents;
        this.map = map;
        selectionOccurred = false;
    }

    public void setBackgroundURL(String url) {
        backgroundURL = url;
    }

    public void addToAgents(DraggableAgentView agent) {
        agents.add(agent);
    }

    public void updateSelectionOccurred(boolean selectionSuccess) {
        selectionOccurred = selectionSuccess;
        map.setSelection(selectionSuccess);
    }

    List<DraggableAgentView> getAgents() {
        return agents;
    }

    int getAgentCount() {
        return agents.size();
    }

    void removeAgent(AgentView agent) {
        agents.remove(agent);
    }

    boolean getSelection() {
        return selectionOccurred;
    }

    public void updateMap(MapPane map) {
        map.clearMap();
        map.setMapImage(map.getLevel(), backgroundURL);
        for (AgentView agent : agents) {
            map.getMapPane().getChildren().add(agent);
        }
    }


}
