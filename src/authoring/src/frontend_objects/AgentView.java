package frontend_objects;

import javafx.scene.image.ImageView;

public class AgentView extends ImageView {

    /**
     * Extends frontend_objects.DraggableView for dragging the tower image into the map
     * @author Mary Stuart Elder and Eric Lin
     */

    //public static final String STYLE = "img";
    public static final int SIZE = 60;


    public AgentView(){
        super();
        this.setFitWidth(SIZE);
        this.setFitHeight(SIZE);
       //formatView(imageName, STYLE, SIZE, SIZE);
    }

    public AgentView(String url) {
        super(url);
        this.setFitWidth(SIZE);
        this.setFitHeight(SIZE);
    }
}
