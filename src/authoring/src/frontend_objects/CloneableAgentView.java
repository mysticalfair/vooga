package frontend_objects;

import javafx.scene.input.MouseEvent;
import panes.ConsolePane;
import panes.MapPane;
import util.AuthoringContext;

public class CloneableAgentView extends AgentView {

    private DraggableAgentView draggableCopy;
    private String url;

    public CloneableAgentView(AuthoringContext authoringContext, String url) {
        super(authoringContext, url);
        this.url = url;
    }

    public void mousePressedOnClone(MouseEvent e, MapPane map, ConsolePane console) {
        if (e.getClickCount() == getContext().getInt("CloneClickCount")) {
            DraggableAgentView copy = new DraggableAgentView(getContext(), this);
            map.addAgent(map.getLevel(), copy);
            console.displayMessage(getContext().getString("AgentAdded") + map.getAgentCount(), ConsolePane.Level.NEUTRAL);
            copy.setMouseActionsForDrag(map, console);
        } else {
            // code to open up attributes pane.
        }
    }


    String getUrl() {
        return url;
    }



}
