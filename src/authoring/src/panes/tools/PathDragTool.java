package panes.tools;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import panes.MapPane;
import panes.Path;

import java.util.List;

public class PathDragTool extends PathModifyTool{

    /**
     * Takes in Paths from AuthoringEnvironment
     * Establishes triggers for Paths/points so they drag and update
     */

    public static final Image GRAB = new Image(ToolbarPane.GRAB_IMAGE);
    public static final ImageCursor GRAB_CURSOR = new ImageCursor(GRAB, GRAB.getWidth() / 2, GRAB.getWidth()/2);

    public PathDragTool(MapPane otherMap, Scene otherScene, String fileName, List<Path> paths){
        super(otherMap, otherScene, fileName, paths);
    }

    @Override
    public void onMapClick(MouseEvent event) {
        var selected = checkPointSelected(event.getX(), event.getY());
        if(selected){
            selectedPoint.dragSetup(event);
            updatePathLines(selectedPath);
        }
    }

    private void onMapDrag(MouseEvent event){
        var selected = checkPointSelected(event.getX(), event.getY());
        if(selected){
            selectedPoint.onDrag(event);
            updatePathLines(selectedPath);
        }
    }

    public void setMouseActions(){
        map.accessMap(node -> node.setOnMousePressed(e -> onMapClick(e)));
        map.accessMap(node -> node.setOnMouseDragged(e -> onMapDrag(e)));
    }

    @Override
    protected void enableTool() {
        scene.setCursor(GRAB_CURSOR);
        setMouseActions();
    }

    @Override
    protected void disableTool() {
        scene.setCursor(Cursor.DEFAULT);
        removeMouseActions();
    }
}
