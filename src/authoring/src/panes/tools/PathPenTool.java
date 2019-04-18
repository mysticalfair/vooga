package panes.tools;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import panes.MapPane;

public class PathPenTool extends MapTool{

    private Circle previousCircle = null;
    private Circle currentCircle = null;
    // TODO: replace with list of linkedlists

    public PathPenTool(MapPane otherMap, Scene otherScene, String fileName){
        super(otherMap, otherScene, fileName);
    }

    public void setMouseActions(){
        map.accessContainer(node -> node.setOnMouseClicked(e -> onMapClick(e)));
    }

    private void removeMouseActions(){
        map.accessContainer(node -> node.setOnMouseClicked(null));
        map.accessContainer(node -> node.setOnMouseDragged(null));
        map.accessContainer(node -> node.setOnMouseReleased(null));
    }

    @Override
    public void onMapClick(MouseEvent event) {
        if(!toolEnabled){
            return;
        }
        currentCircle = new Circle(event.getX(), event.getY(), 3, Color.BLUE);
        map.spawnShape((Shape) currentCircle);
        if(previousCircle != null) {
            Line l = new Line(currentCircle.getCenterX(), currentCircle.getCenterY(), previousCircle.getCenterX(), previousCircle.getCenterY());
            l.getStrokeDashArray().addAll(2d, 4d);
            l.setStroke(Color.BLUE);
            map.spawnShape((Shape) l);
        }
        previousCircle = currentCircle;
    }

    @Override
    protected void enableTool() {
        Image image = new Image(ToolbarPane.PEN_IMAGE);
        scene.setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() /2));
        setMouseActions();
    }

    @Override
    protected void disableTool() {
        scene.setCursor(Cursor.DEFAULT);
        removeMouseActions();
    }
}
