package panes;

import frontend_objects.AgentView;
import frontend_objects.DraggableAgentView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.scene.shape.Ellipse;
import util.AuthoringContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MapState {

    private String backgroundURL;
    private List<DraggableAgentView> agents;
    private List<Path> paths;
    private SimpleIntegerProperty selectCount = new SimpleIntegerProperty();
    private AuthoringContext context;

    public MapState(AuthoringContext context, String backgroundURL, List<DraggableAgentView> agents, List<Path> paths) {
        this.context = context;
        this.backgroundURL = backgroundURL;
        this.agents = agents;
        selectCount.set(0);
        this.paths = paths;
    }

    public MapState(MapState other, MapPane map, ConsolePane console) {
        this.context = other.context;
        this.backgroundURL = other.backgroundURL;
        this.agents = new ArrayList<>();
        for (DraggableAgentView agent : other.agents) {
            this.agents.add(new DraggableAgentView(context, agent));
        }
        for (DraggableAgentView agent : this.agents) {
            agent.setMouseActionsForDrag(map, console);
        }
        for (int i = 0; i < this.agents.size(); i++) {
            this.agents.get(i).setMouseActionsForDrag(map, console);
            this.agents.get(i).setTranslateX(other.agents.get(i).getTranslateX());
            this.agents.get(i).setTranslateY(other.agents.get(i).getTranslateY());
        }
        this.paths = new ArrayList<>();
        for(Path path: other.paths){
            this.paths.add(path);
        }
        selectCount.set(0);
    }

    public void addToPaths(Path path){
        paths.add(path);
    }

    public void removePath(Path path){
        if(paths.contains(path)){
            paths.remove(path);
        }
    }

    public List<Path> getPaths(){
        return paths;
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

    public void removeAgent(AgentView agent) {
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
        for(Path path: paths){
            map.getMapPane().getChildren().addAll(path.getLines());
            map.getMapPane().getChildren().addAll(path.getPoints());
        }
    }


}