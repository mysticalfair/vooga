package frontend_objects;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public abstract class DraggableView extends AuthorView {

    /**
     * Superclass for all frontend_objects.AuthorView objects intended to be dragged into the map
     * Contains a DraggableImage, which enables ImageViews to be placeable in the map (or draggable)
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

    private DraggableImage myDraggableImage;
    private String imageFile;
    private String style;
    private boolean draggable;

    public DraggableView(boolean drag){
        draggable = drag;
        myDraggableImage = new DraggableImage();
    }

    public DraggableView(DraggableView otherView, boolean drag){
        this(drag);
        imageFile = otherView.getImageName();
        style = otherView.getImageStyle();
        var xSize = otherView.getView().getFitWidth();
        var ySize = otherView.getView().getFitHeight();
        myDraggableImage.makeFormattedView(imageFile, style, xSize, ySize);
    }

    public void initializeViewPosition(MouseEvent event){
        myDraggableImage.initializeViewPosition(event);
    }

        protected String getImageName(){
        return imageFile;
    }

    protected String getImageStyle(){
        return style;
    }

    protected void formatView(String imageName, String styleName, double xSize, double ySize){
        imageFile = imageName;
        style = styleName;
        myDraggableImage.makeFormattedView(imageFile, style, xSize, ySize);
        if(draggable){
            myDraggableImage.setMouseActions(myDraggableImage.getView());
        }
    }

    /**
     * Called by other classes to get the visual representation of this class
     * @return ImageView
     */
    public ImageView getView(){
        return myDraggableImage.getView();
    }

    /**
     * Used by subclasses of frontend_objects.DraggableView to get the view's start x position
     * If a subclass has to return to the start position, this will allow it
     * @return double Start position X
     */
    public double getStartX(){
        return myDraggableImage.getStartX();
    }

    /**
     * Used by subclasses of frontend_objects.DraggableView to get the view's start y position
     * If a subclass has to return to the start position, this will allow it
     * @return double Start position Y
     */
    public double getStartY(){
        return myDraggableImage.getStartY();
    }

}