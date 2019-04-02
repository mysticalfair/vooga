package frontend_objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class DraggableImage{

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
    private ImageView myImageView;

    /**
     * Called by subclasses of frontend_objects.DraggableView to format myImageView
     * Subclass specific mouseReleased methods are used in setMouseActions
     * @param imageName String, name of the image file
     * @param imageStyle String, references a CSS style for the image to take
     * @param xSize double, horizontal size of image
     * @param ySize double, vertical size of image
     */
    public void makeFormattedView(String imageName, String imageStyle, double xSize, double ySize){
        var localImage = new Image(imageName);
        var view = new ImageView(localImage);
        view.setId(imageStyle);
        view.setFitWidth(xSize);
        view.setFitHeight(ySize);
        setMouseActions(view);
        myImageView = view;
    }

    /**
     * Called by other classes to get the visual representation of this class
     * @return ImageView
     */
    public ImageView getView(){
        return myImageView;
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

    /**
     * Method is called by subclasses to set up the mouse actions for their views
     * Superclass references the universal mousePressed and mouseDragged methods, since all views will move around when dragged
     * Mouse release will be different for each subclass, since different circumstances will impact where the view lands on release
     */
    private void setMouseActions(ImageView view){
        view.setOnMousePressed(mouseEvent -> mousePressed(mouseEvent));
        view.setOnMouseDragged(mouseEvent -> mouseDragged(mouseEvent));
    }

    /**
     * Initializes instance variables for drag and drop when mouse is pressed
     * @param event MouseEvent
     */
    private void mousePressed(MouseEvent event){
        myStartSceneX = event.getSceneX();
        myStartSceneY = event.getSceneY();
        myStartXOffset = ((ImageView)(event.getSource())).getTranslateX();
        myStartYOffset = ((ImageView)(event.getSource())).getTranslateY();
    }

    /**
     * As mouse is dragged, ImageView position is moved
     * @param event MouseEvent
     */
    private void mouseDragged(MouseEvent event){
        double offsetX = event.getSceneX() - myStartSceneX;
        double offsetY = event.getSceneY() - myStartSceneY;
        double newTranslateX = myStartXOffset + offsetX;
        double newTranslateY = myStartYOffset + offsetY;

        ((ImageView)(event.getSource())).setTranslateX(newTranslateX);
        ((ImageView)(event.getSource())).setTranslateY(newTranslateY);
    }
}