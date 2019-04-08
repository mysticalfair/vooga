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

    private String url;
    private int gridCol, gridRow;
    private double myStartSceneX, myStartSceneY;
    private double myStartXOffset, myStartYOffset;
    private ImageView myImageView;
    private GridPane inventory;


    public DraggableAgentView(String url, int gridCol, int gridRow, GridPane inventory) {
        super(url);
        this.url = url;
        this.inventory = inventory;
        this.gridCol = gridCol;
        this.gridRow = gridRow;
        setMouseActions(this);
    }

    /**
     * Called by subclasses of frontend_objects.DraggableView to format myImageView
     * Subclass specific mouseReleased methods are used in setMouseActions
     * @param imageName String, name of the image file
     * @param imageStyle String, references a CSS style for the image to take
     * @param xSize double, horizontal size of image
     * @param ySize double, vertical size of image
     */
    /*
    public void makeFormattedView(String imageName, String imageStyle, double xSize, double ySize){
        var localImage = new Image(imageName);
        var view = new ImageView(localImage);
        view.setId(imageStyle);
        view.setFitWidth(xSize);
        view.setFitHeight(ySize);
        setMouseActions(view);
        myImageView = view;
    }*/

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

    public int getCol() {
        return gridCol;
    }

    public int getRow() {
        return gridRow;
    }

    /**
     * Method is called by subclasses to set up the mouse actions for their views
     * Superclass references the universal mousePressed and mouseDragged methods, since all views will move around when dragged
     * Mouse release will be different for each subclass, since different circumstances will impact where the view lands on release
     */
    public void setMouseActions(ImageView view){
        view.setOnMousePressed(mouseEvent -> mousePressed(mouseEvent));
        view.setOnMouseDragged(mouseEvent -> mouseDragged(mouseEvent));
        view.setOnMouseReleased(mouseEvent -> mouseReleased());
    }

    /**
     * Initializes instance variables for drag and drop when mouse is pressed
     * @param event MouseEvent
     */
    private void mousePressed(MouseEvent event) {
        inventory.add(cloneDraggableView(), gridCol, gridRow);
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

    private void mouseReleased() {
        System.out.println("Translate: " + this.getTranslateX() + " " + this.getTranslateY());
    }

    public DraggableAgentView cloneDraggableView() {
        return new DraggableAgentView(url, gridCol, gridRow, inventory);
    }
}
