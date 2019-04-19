package frontend_objects;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import panes.AgentPane;
import panes.MapPane;

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

    public void setMouseActionsForDrag() {
        this.setOnMousePressed(mouseEvent -> mousePressed(mouseEvent));
        this.setOnMouseDragged(mouseEvent -> mouseDragged(mouseEvent));
        this.setOnMouseReleased(mouseEvent -> mouseReleased());
    }

    private void mousePressed(MouseEvent event) {
        setMyStartSceneX(event.getSceneX());
        setMyStartSceneY(event.getSceneY());
        setMyStartXOffset(((DraggableAgentView)(event.getSource())).getTranslateX());
        setMyStartYOffset(((DraggableAgentView)(event.getSource())).getTranslateY());
    }

    private void mouseDragged(MouseEvent event) {
        double offsetX = event.getSceneX() - myStartSceneX;
        double offsetY = event.getSceneY() - myStartSceneY;
        double newTranslateX = myStartXOffset + offsetX;
        double newTranslateY = myStartYOffset + offsetY;
        ((DraggableAgentView)(event.getSource())).setTranslateX(newTranslateX);
        ((DraggableAgentView)(event.getSource())).setTranslateY(newTranslateY);
        if (trashIntersect()) {
            setEffect(setLighting(Color.RED));
        } else if (outOfBounds()) {
            setEffect(setLighting(Color.WHITE));
        } else {
            setEffect(null);
        }
    }

    private Lighting setLighting(Color color) {
        Lighting lighting = new Lighting();
        lighting.setDiffuseConstant(1.0);
        lighting.setSpecularConstant(0.0);
        lighting.setSpecularExponent(0.0);
        lighting.setSurfaceScale(0.0);
        lighting.setLight(new Light.Distant(45, 45, color));
        return lighting;
    }

    private void mouseReleased() {
        if (trashIntersect()) {
            setImage(null);
            map.removeAgent(this);
            consolePane.displayConsoleMessage("Agent discarded from map. Agent count on map: " + map.getAgentCount());
        } else if (outOfBounds()) {
            setEffect(null);
            setTranslateX(myStartXOffset);
            setTranslateY(myStartYOffset);
            consolePane.displayConsoleMessage("Agent out of bounds: returning to original location");
        }
    }

    private boolean outOfBoundsHorizontal() {
        double xPos = getTranslateX();
        double xPosRight = getTranslateX() + getFitWidth();
        boolean rightOutOfBounds = xPosRight > MapPane.MAP_WIDTH;
        boolean leftOutOfBounds = xPos < 0;
        return leftOutOfBounds || rightOutOfBounds;
    }

    private boolean outOfBoundsVertical() {
        double yPos = getTranslateY();
        double yPosBot = getTranslateY() + getFitHeight();
        boolean topOutOfBounds = yPos < 0;
        boolean botOutOfBounds = yPosBot > MapPane.MAP_HEIGHT;
        return topOutOfBounds || botOutOfBounds;
    }

    private boolean outOfBounds() {
        return outOfBoundsHorizontal() || outOfBoundsVertical();
    }

    private boolean trashIntersect() {
        double yPos = getTranslateY();
        double xPosRight = getTranslateX() + getFitWidth();
        boolean topOutOfBounds = yPos < 0;
        boolean rightOutOfBounds = xPosRight > borderPane.getWidth() - attributesPane.getWidth() - agentPane.getVBoxContainer().getWidth();
        return topOutOfBounds && rightOutOfBounds;
    }

}
