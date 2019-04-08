package frontend_objects;

import javafx.scene.image.ImageView;
import panes.HoverPane;

public class CloneableAgentView extends AgentView {

    private DraggableAgentView draggableCopy;
    private String url;

    public CloneableAgentView(String url) {
        super(url);
        this.url = url;
        //this.hoverLayer = hoverLayer;
        //this.setOnMousePressed(e -> createDraggable(this));
    }

    /*
    private void createDraggable(ImageView agent) {
        draggableCopy = new DraggableAgentView(url);
        hoverLayer.getChildren().add(draggableCopy);
    }
    */

    public DraggableAgentView getDraggableCopy() {
        return draggableCopy;
    }

    public String getUrl() {
        return url;
    }

}
