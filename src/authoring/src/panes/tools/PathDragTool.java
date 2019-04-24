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

    public static final Image PEN = new Image(ToolbarPane.PEN_IMAGE);
    public static final ImageCursor PEN_CURSOR = new ImageCursor(PEN, PEN.getWidth() / 2, PEN.getWidth()/2);

    public PathDragTool(MapPane otherMap, Scene otherScene, String fileName, List<Path> paths){
        super(otherMap, otherScene, fileName, paths);
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

    public void setMouseActions(){
        map.accessMap(node -> node.setOnMousePressed(e -> onMapClick(e)));
        map.accessMap(node -> node.setOnMouseDragged(e -> onMapDrag(e)));
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
