package frontend_objects;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import panes.ConsolePane;
import panes.MapPane;
import util.AuthoringContext;

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
     * @author Mary Stuart Elder and Eric Lin
     */

    private double myStartSceneX, myStartSceneY;
    private double myStartXOffset, myStartYOffset;
    private boolean selected;

    public DraggableAgentView(AuthoringContext authoringContext, CloneableAgentView agent) {
        super(authoringContext, agent.getUrl());
        selected = false;
    }

    void setMouseActionsForDrag(MapPane map, ConsolePane console) {
        this.setOnMousePressed(mouseEvent -> mousePressed(mouseEvent));
        this.setOnMouseDragged(mouseEvent -> mouseDragged(mouseEvent, map));
        this.setOnMouseReleased(mouseEvent -> mouseReleased(map, console));
    }

    public void mousePressed(MouseEvent event) {
        myStartSceneX = event.getSceneX();
        myStartSceneY = event.getSceneY();
        myStartXOffset = getTranslateX();
        myStartYOffset = getTranslateY();
    }

    public void mouseDragged(MouseEvent event, MapPane map) {
        double offsetX = event.getSceneX() - myStartSceneX;
        double offsetY = event.getSceneY() - myStartSceneY;
        double newTranslateX = myStartXOffset + offsetX;
        double newTranslateY = myStartYOffset + offsetY;
        setTranslateX(newTranslateX);
        setTranslateY(newTranslateY);
        if (trashIntersect(map)) {
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
        lighting.setLight(new Light.Distant(getContext().getDouble("LightingConstant"), getContext().getDouble("LightingConstant"), color));
        return lighting;
    }

    public void mouseReleased(MapPane map, ConsolePane console) {
        if (trashIntersect(map)) {
            setImage(null);
            map.removeAgent(this);
            console.displayMessage(getContext().getString("AgentRemoved") + map.getAgentCount(), ConsolePane.Level.NEUTRAL);
        } else if (outOfBounds()) {
            setEffect(null);
            setTranslateX(myStartXOffset);
            setTranslateY(myStartYOffset);
            console.displayMessage(getContext().getString("AgentOutOfBounds"), ConsolePane.Level.NEUTRAL);
        }
    }

    private boolean outOfBoundsHorizontal() {
        double xPos = getTranslateX();
        double xPosRight = getTranslateX() + getFitWidth();
        boolean rightOutOfBounds = xPosRight > getContext().getDouble("InsetMapWidth");
        boolean leftOutOfBounds = xPos < 0;
        return leftOutOfBounds || rightOutOfBounds;
    }

    private boolean outOfBoundsVertical() {
        double yPos = getTranslateY();
        double yPosBot = getTranslateY() + getFitHeight();
        boolean topOutOfBounds = yPos < 0;
        boolean botOutOfBounds = yPosBot > getContext().getDouble("InsetMapHeight");
        return topOutOfBounds || botOutOfBounds;
    }

    private boolean outOfBounds() {
        return outOfBoundsHorizontal() || outOfBoundsVertical();
    }

    private boolean trashIntersect(MapPane map) {
        double yPos = getTranslateY();
        double xPosRight = getTranslateX() + getFitWidth();
        boolean topOutOfBounds = yPos < 0;
        boolean rightOutOfBounds =  xPosRight > getContext().getDouble("InsetMapWidth") + (map.getPaneWidth() - getContext().getDouble("InsetMapWidth"))/2;
        return topOutOfBounds && rightOutOfBounds;
    }

    private void setImageOpacity(){
        // Translucent if selected
        var opacity = 0.3;
        int selectAddition = selected ? 0 : 1;
        var select = 0.7*selectAddition + opacity;
        this.setStyle("-fx-opacity: " + select + ";");
    }

    public void setSelect(boolean select){
        selected = select;
        setImageOpacity();
    }

    public boolean getSelect() {
        return selected;
    }

}
