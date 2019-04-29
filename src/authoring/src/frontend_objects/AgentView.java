package frontend_objects;

import javafx.scene.image.ImageView;
import util.AuthoringContext;

<<<<<<< Updated upstream
=======
import java.util.AbstractMap;
import java.util.SimpleTimeZone;

>>>>>>> Stashed changes
public abstract class AgentView extends ImageView {

    /**
     * Extends frontend_objects.DraggableView for dragging the tower image into the map
     * @author Mary Stuart Elder and Eric Lin
     */

    private AuthoringContext context;

    public AgentView(AuthoringContext authoringContext, String url) {
        super(url);
        this.context = authoringContext;
        this.setFitWidth(getContext().getDouble("AgentSize"));
        this.setFitHeight(getContext().getDouble("AgentSize"));
    }

    protected AuthoringContext getContext() {
        return context;
    }

}
