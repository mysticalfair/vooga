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

    public static final double SIZE = 10;
    public static final Color COLOR = Color.BLUE;

    private Circle visualPoint;
    private double locationX, locationY;
    private double dragStartX, dragStartY;
    private SimpleBooleanProperty dragMode;

    public PathPoint(double x, double y){
        locationX = x;
        locationY = y;

        dragMode = new SimpleBooleanProperty(false);
        dragMode.addListener(((observableValue, aBoolean, t1) -> initDragMethods()));
        visualPoint = new Circle(locationX, locationY, SIZE, COLOR);
    }

    public Shape getPoint(){
        return visualPoint;
    }

    private void initDragMethods(){
        EventHandler<MouseEvent> clickEvent = (dragMode.get()) ? e -> dragSetup(e) : null;
        EventHandler<MouseEvent> dragEvent = (dragMode.get()) ? e -> onDrag(e) : null;
        visualPoint.setOnMousePressed(clickEvent);
        visualPoint.setOnMouseDragged(dragEvent);
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
