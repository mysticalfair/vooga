package panes;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class PathPoint {

    /**
     * Eventhandler is passed in for removal because
     */

    private Circle visualPoint;
    private double locationX, locationY;
    private double dragStartX, dragStartY;
    private SimpleBooleanProperty dragMode;
    private SimpleBooleanProperty removeMode;
    private EventHandler removeMethod;

    public static final double SIZE = 10;
    public static final Color COLOR = Color.BLUE;

    public PathPoint(double x, double y){
        locationX = x;
        locationY = y;

        dragMode = new SimpleBooleanProperty(false);
        dragMode.addListener(((observableValue, aBoolean, t1) -> initDragMethods()));
        removeMode = new SimpleBooleanProperty(false);
        removeMode.addListener(((observableValue, aBoolean, t1) -> initRemoveMethods()));
        visualPoint = new Circle(locationX, locationY, SIZE, COLOR);
    }

    public void setRemoveMethod(EventHandler remover){
        removeMethod = remover;
    }

    public Shape getPoint(){
        return visualPoint;
    }

    public void toggleDragMode() {
        dragMode.set(!dragMode.get());
        removeMode.set(false);
    }

    public void toggleRemoveMode() {
        removeMode.set(!removeMode.get());
        dragMode.set(false);
    }

    private void initDragMethods(){
        EventHandler<MouseEvent> clickEvent = (dragMode.get()) ? e -> dragSetup(e) : null;
        EventHandler<MouseEvent> dragEvent = (dragMode.get()) ? e -> onDrag(e) : null;
        visualPoint.setOnMousePressed(clickEvent);
        visualPoint.setOnMouseDragged(dragEvent);
    }

    private void initRemoveMethods(){
        EventHandler<MouseEvent> clickEvent = (removeMode.get()) ? removeMethod : null;
        visualPoint.setOnMouseClicked(clickEvent);
        visualPoint.setOnMouseDragged(null);
    }

    public void dragSetup(MouseEvent event){
        dragStartX = event.getSceneX();
        dragStartY = event.getSceneY();
    }

    public void onDrag(MouseEvent event){
        double offsetX = event.getSceneX() - dragStartX;
        double offsetY = event.getSceneY() - dragStartY;

        visualPoint.setCenterX(visualPoint.getCenterX() + offsetX);
        visualPoint.setCenterY(visualPoint.getCenterY() + offsetY);
    }

}
