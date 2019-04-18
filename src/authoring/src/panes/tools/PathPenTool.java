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

    public static final Image PEN = new Image(ToolbarPane.PEN_IMAGE);
    public static final ImageCursor PEN_CURSOR = new ImageCursor(PEN, PEN.getWidth() / 2, PEN.getWidth()/2);
    public static final double X_ADJUSTMENT = -10;
    public static final double Y_ADJUSTMENT = 10;

    private Circle previousCircle = null;
    private Circle currentCircle = null;
    // TODO: replace with list of linkedlists

    public PathPenTool(MapPane otherMap, Scene otherScene, String fileName){
        super(otherMap, otherScene, fileName);
    }

    public void setMouseActions(){
        map.accessMap(node -> node.setOnMouseClicked(e -> onMapClick(e)));
    }

    private void removeMouseActions(){
        map.accessMap(node -> node.setOnMouseClicked(null));
        map.accessMap(node -> node.setOnMouseDragged(null));
        map.accessMap(node -> node.setOnMouseReleased(null));
    }

    @Override
    public void onMapClick(MouseEvent event) {
        if(!toolEnabled){
            return;
        }
        currentCircle = new Circle(event.getX() + X_ADJUSTMENT, event.getY() + Y_ADJUSTMENT, 3, Color.BLUE);
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
        scene.setCursor(PEN_CURSOR);
        setMouseActions();
    }

    @Override
    protected void disableTool() {
        scene.setCursor(Cursor.DEFAULT);
        removeMouseActions();
    }
}
