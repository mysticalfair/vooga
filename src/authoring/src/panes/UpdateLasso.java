package panes;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * Resources used:
 *  https://stackoverflow.com/questions/40629841/circle-not-visible-as-i-drag-the-mouse-in-javafx
 */

public class UpdateLasso extends MapTool{

    private Ellipse ellipse;
    private double startingPosX, startingPosY;

    public UpdateLasso(MapPane otherMap, Scene otherScene, String fileName){
        super(otherMap, otherScene, fileName);
        initEllipse();
    }

    public void setMouseActions(){
        map.accessContainer(node -> node.setOnMouseClicked(e -> onMapClick(e)));
        map.accessContainer(node -> node.setOnMouseDragged(e -> mouseDragged(e)));
        map.accessContainer(node -> node.setOnMouseReleased(e -> mouseReleased(e)));
    }

    private void initEllipse(){
        ellipse = new Ellipse();
        ellipse.setStrokeWidth(1.0);
        ellipse.setFill(Color.TRANSPARENT);
        ellipse.setStroke(Color.BLACK);
        ellipse.getStrokeDashArray().addAll(3.0);
    }

    private void removeMouseActions(){
        map.accessContainer(node -> node.setOnMouseClicked(null));
        map.accessContainer(node -> node.setOnMouseDragged(null));
        map.accessContainer(node -> node.setOnMouseReleased(null));
    }

    private void mouseReleased(MouseEvent event){
        map.selectAgents(ellipse);
        map.getContentChildren().remove(ellipse);
        scene.setCursor(Cursor.HAND);
    }

    private void mouseDragged(MouseEvent event){
        map.getContentChildren().remove(ellipse);
        ellipse.setCenterX((event.getX() + startingPosX) / 2);
        ellipse.setCenterY((event.getY() + startingPosY) / 2);
        ellipse.setRadiusX(Math.abs((event.getX() - startingPosX) / 2));
        ellipse.setRadiusY(Math.abs((event.getY() - startingPosY) / 2));
        map.getContentChildren().add(ellipse);
        scene.setCursor(Cursor.CLOSED_HAND);
    }

    @Override
    public void onMapClick(MouseEvent event) {
        initEllipse();
        startingPosX = event.getX();
        startingPosY = event.getY();
        ellipse.setCenterX(startingPosX);
        ellipse.setCenterY(startingPosY);
        ellipse.setRadiusX(0);
        ellipse.setRadiusY(0);
        map.getContentChildren().add(ellipse);
        scene.setCursor(Cursor.HAND);
    }

    @Override
    protected void enableTool() {
        scene.setCursor(Cursor.HAND);
        setMouseActions();
    }

    @Override
    protected void disableTool() {
        scene.setCursor(Cursor.DEFAULT);
        removeMouseActions();
    }
}
