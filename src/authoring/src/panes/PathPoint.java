package panes;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PathPoint {

    /**
     * Eventhandler is passed in for removal because
     */

    private Circle visualPoint;
    private double locationX, locationY;
    private double dragStartX, dragStartY;
    private double dragOffsetX, dragOffsetY;
    private SimpleBooleanProperty dragMode;
    private SimpleBooleanProperty removeMode;

    public static final String DRAG_METHOD = "initDragMethods";
    public static final String REMOVE_METHOD = "initRemoveMethods";
    public static final String DEFAULT_METHOD = "initDefaultMethods";

    private EventHandler removeMethod;

    public static final double SIZE = 3;
    public static final Color COLOR = Color.BLUE;

    public PathPoint(double x, double y/*, EventHandler remover*/){
        locationX = x;
        locationY = y;

        dragMode = new SimpleBooleanProperty(false);
        removeMode = new SimpleBooleanProperty(false);

        //removeMethod = remover;

        visualPoint = new Circle(locationX, locationY, SIZE, COLOR);
    }

    public Shape getPoint(){
        return visualPoint;
    }

    public void setDragMode(boolean drag){
        dragMode.set(drag);
        removeMode.set(false);
//        decideInitMethods(drag, initDragMethods(), initDefaultMethods());
        checkDragRemove();
    }

    public void setRemoveMode(boolean remove){
        removeMode.set(remove);
        dragMode.set(false);
        checkDragRemove();
    }
    private void decideInitMethods(boolean decider, String methodIfTrue, String methodIfFalse) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method trueMethod = getClass().getMethod(methodIfTrue, null);
        Method falseMethod = getClass().getMethod(methodIfFalse, null);

        if(decider){
            trueMethod.invoke(this, null);
        }
        else{
            falseMethod.invoke(this, null);
        }
    }

    private void checkDragRemove(){
        if(dragMode.get()){
            removeMode.set(false);
            initDragMethods();
        }
        else if(removeMode.get()){
            dragMode.set(false);
            initRemoveMethods();
        }
        else{
            initDefaultMethods();
        }
    }

    private void initDragMethods(){
        visualPoint.setOnMouseClicked(e -> dragSetup(e));
        visualPoint.setOnMouseDragged(e -> onDrag(e));
    }

    private void initRemoveMethods(){
        visualPoint.setOnMouseClicked(removeMethod);
        visualPoint.setOnMouseDragged(null);
    }

    private void initDefaultMethods(){
        visualPoint.setOnMouseClicked(null);
        visualPoint.setOnMouseDragged(null);
    }

    private void dragSetup(MouseEvent event){
        dragStartX = event.getSceneX();
        dragStartY = event.getSceneY();
        dragOffsetX = ((Circle)(event.getSource())).getTranslateX();
        dragOffsetY = ((Circle)(event.getSource())).getTranslateY();
    }

    private void onDrag(MouseEvent event){
        double offsetX = event.getSceneX() - dragStartX;
        double offsetY = event.getSceneY() - dragStartY;
        double newTranslateX = dragOffsetX + offsetX;
        double newTranslateY = dragOffsetY + offsetY;

        ((Circle)(event.getSource())).setTranslateX(newTranslateX);
        ((Circle)(event.getSource())).setTranslateY(newTranslateY);
    }

}
