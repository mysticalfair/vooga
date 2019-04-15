package frontend_objects;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;

public class AgentView extends ImageView {

    /**
     * Extends frontend_objects.DraggableView for dragging the tower image into the map
     * @author Mary Stuart Elder and Eric Lin
     */

    //public static final String STYLE = "img";
    public static final int SIZE = 60;

    private SimpleBooleanProperty selected = new SimpleBooleanProperty();

    public AgentView(){
        super();
        this.setFitWidth(SIZE);
        this.setFitHeight(SIZE);
        selected.set(false);
       //formatView(imageName, STYLE, SIZE, SIZE);
    }

    public AgentView(String url) {
        super(url);
        this.setFitWidth(SIZE);
        this.setFitHeight(SIZE);
    }

    private void setImageOpacity(){
        // Translucent if selected
        var opacity = 0.3;
        int selectAddition = selected.get() ? 0 : 1;
        var select = 0.7*selectAddition + opacity;
        this.setStyle("-fx-opacity: " + select + ";");
    }

    public void setSelect(boolean select){
        selected.set(select);
        setImageOpacity();
    }
}
