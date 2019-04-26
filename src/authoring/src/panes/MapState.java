package panes;

import frontend_objects.AgentView;
import frontend_objects.DraggableAgentView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Ellipse;

import java.util.List;
import java.util.function.Consumer;

public class MapState {

    private String backgroundURL;
    private List<DraggableAgentView> agents;
    private SimpleIntegerProperty selectCount = new SimpleIntegerProperty();

    public MapState(String backgroundURL, List<DraggableAgentView> agents) {
        this.backgroundURL = backgroundURL;
        this.agents = agents;
        selectCount.set(0);
    }

    public void setBackgroundURL(String url) {
        backgroundURL = url;
    }

    public void addToAgents(DraggableAgentView agent) {
        agents.add(agent);
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

    public void accessSelectCount(Consumer<SimpleIntegerProperty> accessListener) {accessListener.accept(selectCount);}

    public void selectAgents(Ellipse lassoEllipse){
        selectCount.set(0);
        for(DraggableAgentView agent: agents){
            agent.setSelect(lassoSelects(lassoEllipse, agent));
        }
        for (DraggableAgentView agent: agents) {
            if(agent.getSelect()) {
                selectCount.set(selectCount.get() + 1);
            }
        }
    }

    private boolean lassoSelects(Ellipse lassoEllipse, AgentView agent){
        return lassoEllipse.intersects(agent.getBoundsInParent());
    }

    public void updateMap(MapPane map) {
        map.clearMap();
        map.setMapImage(map.getLevel(), backgroundURL);
        for (AgentView agent : agents) {
            map.getMapPane().getChildren().add(agent);
        }
    }


}
