package frontend_objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import panes.AgentPane;

public class DraggableAgentView extends AgentView {

    /**
     * Superclass for all frontend_objects.AuthorView objects intended to be dragged into the map
     * Enables ImageViews to be placeable in the map (or draggable)
     * Subclasses must implement their own version of mouseReleased
     *  Ex. if a class wants the object to return to the initial position when the imageview is dropped:
     *      @Override
     *     public void mouseReleased(MouseEvent event){
     *         ((ImageView)(event.getSource())).setTranslateX(getStartX());
     *         ((ImageView)(event.getSource())).setTranslateY(getStartY());
     *     }
     * Resource consulted for draggable images: http://java-buddy.blogspot.com/2013/07/javafx-drag-and-move-something.html
     * @author Mary Stuart Elder
     */

    private double myStartSceneX, myStartSceneY;
    private double myStartXOffset, myStartYOffset;

    public DraggableAgentView(CloneableAgentView agent) {
        super(agent.getUrl());
    }

    /**
     * Used by subclasses of frontend_objects.DraggableView to get the view's start x position
     * If a subclass has to return to the start position, this will allow it
     * @return double Start position X
     */
    public double getStartX(){
        return myStartXOffset;
    }

    /**
     * Used by subclasses of frontend_objects.DraggableView to get the view's start y position
     * If a subclass has to return to the start position, this will allow it
     * @return double Start position Y
     */
    public double getStartY(){
        return myStartYOffset;
    }

    public void setMyStartXOffset(double x) {
        myStartXOffset = x;
    }

    public void setMyStartYOffset(double y) {
        myStartYOffset = y;
    }

    public double getMyStartSceneX() { return myStartSceneX; }

    public double getMyStartSceneY() { return myStartSceneY; }

    public void setMyStartSceneX(double x) {
        myStartSceneX = x;
    }

    public void setMyStartSceneY(double y) {
        myStartSceneY = y;
    }
}
