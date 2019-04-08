package frontend_objects;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AgentViewTest{

    /**
     * Extends frontend_objects.DraggableView for dragging the tower image into the map
     * @author Mary Stuart Elder
     */

    public static final String STYLE = "img";
    public static final int SIZE = 100;


    private DraggableImage myImage;

    public AgentViewTest(String imageName){
        myImage = new DraggableImage();
        //myImage.makeFormattedView(imageName, STYLE, SIZE, SIZE);
    }
    public ImageView getView(){
        return myImage.getView();
    }

}
