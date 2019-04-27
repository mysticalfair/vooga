package frontend_objects;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;
import util.AuthoringContext;

import java.util.AbstractMap;
import java.util.SimpleTimeZone;

public class AgentView extends ImageView {

    /**
     * Extends frontend_objects.DraggableView for dragging the tower image into the map
     * @author Mary Stuart Elder and Eric Lin
     */

    private SimpleBooleanProperty selected = new SimpleBooleanProperty();
    private AuthoringContext context;

    public AgentView(AuthoringContext authoringContext){
        super();
        this.setFitWidth(getContext().getDouble("AgentSize"));
        this.setFitHeight(getContext().getDouble("AgentSize"));
        selected.set(false);
        this.context = authoringContext;
        selected.addListener((observable, oldValue, newValue) -> {
        });
    }

    public AgentView(AuthoringContext authoringContext, String url) {
        super(url);
        this.context = authoringContext;
        this.setFitWidth(getContext().getDouble("AgentSize"));
        this.setFitHeight(getContext().getDouble("AgentSize"));
    }

    protected AuthoringContext getContext() {
        return context;
    }
/*
    private void setImageOpacity(){
        // Translucent if selected
        var opacity = TRANSLUCENT;
        int selectAddition = selected.get() ? 0 : 1;
        var select = (1-TRANSLUCENT)*selectAddition + opacity;
        this.setStyle("-fx-opacity: " + select + ";");
    }

    public void setSelect(boolean select){
        selected.set(select);
        setImageOpacity();
    }
    */

}
