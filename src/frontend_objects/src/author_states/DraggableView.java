package author_states;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public abstract class DraggableView extends AuthorView {

    /**
     * Superclass for all AuthorView objects intended to be dragged into the map
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

    private double startSceneX, startSceneY;
    private double startXOffset, startYOffset;

    /**
     * Method is called by subclasses to set up the mouse actions for their views
     * Superclass references the universal mousePressed and mouseDragged methods, since all views will move around when dragged
     * Mouse release will be different for each subclass, since different circumstances will impact where the view lands on release
     * @param view ImageView object for the method to call upon
     */
    public void setMouseActions(ImageView view){
        view.setOnMousePressed(mouseEvent -> mousePressed(mouseEvent));
        view.setOnMouseDragged(mouseEvent -> mouseDragged(mouseEvent));
        view.setOnMouseReleased(mouseEvent -> mouseReleased(mouseEvent));
    }

    /**
     * Only mouseReleased is an abstract method because all DraggableViews will be clicked and moved, but each individual subclass behaves differently on release
     * @param event MouseEvent
     */
    public abstract void mouseReleased(MouseEvent event);

    /**
     * Used by subclasses of DraggableView to get the view's start x position
     * If a subclass has to return to the start position, this will allow it
     * @return double Start position X
     */
    public double getStartX(){
        return startXOffset;
    }

    /**
     * Used by subclasses of DraggableView to get the view's start y position
     * If a subclass has to return to the start position, this will allow it
     * @return double Start position Y
     */
    public double getStartY(){
        return startYOffset;
    }

    /**
     * Initializes instance variables for drag and drop when mouse is pressed
     * @param event MouseEvent
     */
    private void mousePressed(MouseEvent event){
        startSceneX = event.getSceneX();
        startSceneY = event.getSceneY();
        startXOffset = ((ImageView)(event.getSource())).getTranslateX();
        startYOffset = ((ImageView)(event.getSource())).getTranslateY();
    }

    /**
     * As mouse is dragged, ImageView position is moved
     * @param event MouseEvent
     */
    private void mouseDragged(MouseEvent event){
        double offsetX = event.getSceneX() - startSceneX;
        double offsetY = event.getSceneY() - startSceneY;
        double newTranslateX = startXOffset + offsetX;
        double newTranslateY = startYOffset + offsetY;

        ((ImageView)(event.getSource())).setTranslateX(newTranslateX);
        ((ImageView)(event.getSource())).setTranslateY(newTranslateY);
    }
}