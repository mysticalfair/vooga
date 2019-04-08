package frontend_objects;

import javafx.scene.image.ImageView;

public class AgentView extends ImageView {

    /**
     * Extends frontend_objects.DraggableView for dragging the tower image into the map
     * @author Mary Stuart Elder and Eric Lin
     */

    //public static final String STYLE = "img";
    public static final int SIZE = 100;

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

    /*
    protected void makeFormattedView(String imageName, String imageStyle, double xSize, double ySize){
        Image localImage = new Image(imageName);
        ImageView view = new ImageView(localImage);
        view.setId(imageStyle);
        view.setFitWidth(xSize);
        view.setFitHeight(ySize);
        //setMouseActions(view);
        //myImageView = view;
    }
    */

    /*
    public AgentView(AgentView otherAgent){
        super(otherAgent, true);
    }
    */

    /*
    Need the DraggableView classes to have methods that allow the View to be dragged anywhere
    In the case of Agents:
        If you've released the mouse and an agent can be placed, a copy of the Agent should be stored in both the original location and the final location

    Attributes of an AgentView
        Characteristics of an Agent
            Size
            Speed?
            Health
            Whatever
        Image associated with it
        Location

     */
}
