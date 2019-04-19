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
            map.addAgent(copy);
            console.displayConsoleMessage("Agent added to map. Agent count on map: " + map.getAgentCount());
            //setMouseActionsForDrag(copy);
        } else {
            // code to open up attributes pane.
        }
    }


    String getUrl() {
        return url;
    }



}
