package panes;

import frontend_objects.AgentView;

import java.util.List;

public class MapState {

    private String backgroundURL;
    private List<AgentView> agents;

    public MapState(String backgroundURL, List<AgentView> agents) {
        this.backgroundURL = backgroundURL;
        this.agents = agents;
    }

    public void setBackgroundURL(String url) {
        backgroundURL = url;
    }

    public void addToAgents(AgentView agent) {
        agents.add(agent);
    }

    public void updateMap(MapPane map) {
        map.clearMap();
        map.setMapImage(map.getLevel(), backgroundURL);
        for (AgentView agent : agents) {
            map.getMapPane().getChildren().add(agent);
        }
    }


}
