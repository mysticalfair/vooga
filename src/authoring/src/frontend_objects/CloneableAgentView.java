package frontend_objects;

import javafx.scene.input.MouseEvent;
import panes.ConsolePane;
import panes.MapPane;
import util.AuthoringContext;

public class CloneableAgentView extends AgentView {

    private String name;
    private String url;

    public CloneableAgentView(AuthoringContext authoringContext, String name, String url) {
        super(authoringContext, url);
        this.name = name;
        this.url = url;
        setId(getContext().getString("ButtonStyle"));
    }

    public void mousePressedOnClone(MouseEvent e, MapPane map) {
        if (e.getClickCount() == getContext().getInt("CloneClickCount")) {
            DraggableAgentView copy = new DraggableAgentView(getContext(), this);
            map.addAgent(map.getLevel(), copy);
            getContext().displayConsoleMessage(getContext().getString("AgentAdded") + map.getAgentCount(), ConsolePane.Level.NEUTRAL);
            copy.setMouseActionsForDrag(map);
        } else {
            // code to open up attributes pane.
        }
    }


    String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

}
