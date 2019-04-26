package frontend_objects;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;

public class AgentView extends ImageView {

    /**
     * Extends frontend_objects.DraggableView for dragging the tower image into the map
     * @author Mary Stuart Elder and Eric Lin
     */

    public static final int SIZE = 60;
    public static final double TRANSLUCENT = 0.3;

    private SimpleBooleanProperty selected = new SimpleBooleanProperty();

    public AgentView(){
        super();
        this.setFitWidth(SIZE);
        this.setFitHeight(SIZE);
        selected.set(false);
    }

    public AgentView(String url) {
        super(url);
        this.setFitWidth(SIZE);
        this.setFitHeight(SIZE);
    }

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
}
