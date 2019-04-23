package panes.tools;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import panes.MapPane;
import panes.Path;
import panes.PathPoint;

import java.util.List;

public class PathDragTool extends PathTool{

    /**
     * Takes in Paths from AuthoringEnvironment
     * Establishes triggers for Paths/points so they drag and update
     */

    public static final Image PEN = new Image(ToolbarPane.PEN_IMAGE);
    public static final ImageCursor PEN_CURSOR = new ImageCursor(PEN, PEN.getWidth() / 2, PEN.getWidth()/2);
    public static final double X_ADJUSTMENT = -10;
    public static final double Y_ADJUSTMENT = 10;

    private List<Path> pathOptions;

    private Path selectedPath = null;
    private PathPoint selectedPoint = null;
    // TODO: replace with list of linkedlists

    public PathDragTool(MapPane otherMap, Scene otherScene, String fileName, List<Path> paths){
        super(otherMap, otherScene, fileName, paths);
    }

    public void updatePaths(List<Path> paths){
        pathOptions = paths;
    }

    private void togglePathDraggable(){
        for(Path p: pathOptions){
            p.toggleDraggable();
        }
    }

    @Override
    public void onMapClick(MouseEvent event) {
        var selected = checkPointSelected(event);
        if(selected){
            selectedPoint.dragSetup(event);
            updatePathLines(selectedPath);
        }
    }

    private void onMapDrag(MouseEvent event){
        var selected = checkPointSelected(event);
        if(selected){
            selectedPoint.onDrag(event);
            updatePathLines(selectedPath);
        }
    }

    private boolean checkPointSelected(MouseEvent event){
        var select = false;
        for(Path path: pathOptions){
            for(Circle point: path.getPoints()){
                if(point.contains(new Point2D(event.getX(), event.getY()))){
                    selectedPoint = path.getPoint(point);
                    selectedPath = path;
                    select = true;
                    break;
                }
            }
        }
        return select;
    }

    public void setMouseActions(){
        map.accessMap(node -> node.setOnMousePressed(e -> onMapClick(e)));
        map.accessMap(node -> node.setOnMouseDragged(e -> onMapDrag(e)));
    }

    private void removeMouseActions(){
        map.accessMap(node -> node.setOnMousePressed(null));
        map.accessMap(node -> node.setOnMouseDragged(null));
        map.accessMap(node -> node.setOnMouseReleased(null));
    }

    @Override
    protected void enableTool() {
        //scene.setCursor(PEN_CURSOR);
        //togglePathDraggable();
        setMouseActions();
    }

    @Override
    protected void disableTool() {
        scene.setCursor(Cursor.DEFAULT);
        //togglePathDraggable();
        removeMouseActions();
    }
}
