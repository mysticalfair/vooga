package frontend_objects;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AgentView extends DraggableView {

    /**
     * Extends frontend_objects.DraggableView for dragging the tower image into the map
     * @author Mary Stuart Elder
     */

    public static final String STYLE = "img";
    public static final int SIZE = 100;

    public AgentView(String imageName){
        makeFormattedView(imageName, STYLE, SIZE, SIZE);
    }

    @Override
    public void mouseReleased(MouseEvent event){
        if( event.getSceneX() > 300 ){
            ((ImageView)(event.getSource())).setTranslateX(getStartX());
            ((ImageView)(event.getSource())).setTranslateY(getStartY());
        }
    }
}
