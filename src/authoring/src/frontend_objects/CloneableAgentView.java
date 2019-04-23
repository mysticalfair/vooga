package frontend_objects;

import javafx.scene.input.MouseEvent;
import panes.ConsolePane;
import panes.MapPane;

public class CloneableAgentView extends AgentView {

    private DraggableAgentView draggableCopy;
    private String url;

    public CloneableAgentView(String url) {
        super(url);
        this.url = url;
    }

    public void mousePressedOnClone(MouseEvent e, MapPane map, ConsolePane console) {
        if (e.getClickCount() == 2) {
            DraggableAgentView copy = new DraggableAgentView(this);
            map.addAgent(map.getLevel(), copy);
            console.displayMessage("Agent added to map. Agent count on map: " + map.getAgentCount(), ConsolePane.Level.NEUTRAL);
            copy.setMouseActionsForDrag(map, console);
        } else {
            // code to open up attributes pane.
        }
    }


    String getUrl() {
        return url;
    }



}
