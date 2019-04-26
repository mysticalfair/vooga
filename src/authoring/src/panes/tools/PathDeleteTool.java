package panes.tools;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import panes.MapPane;
import panes.Path;

import java.util.List;

public class PathDeleteTool extends PathModifyTool{

    public static final Image DELETE = new Image(ToolbarPane.DELETE_IMAGE);
    public static final ImageCursor DELETE_CURSOR = new ImageCursor(DELETE, DELETE.getWidth() / 2, DELETE.getWidth()/2);

    public static final double X_ADJUSTMENT = -10;
    public static final double Y_ADJUSTMENT = -10;

    public PathDeleteTool(MapPane otherMap, Scene otherScene, String fileName, List<Path> paths){
        super(otherMap, otherScene, fileName, paths);
    }

    public void setMouseActions(){
        map.accessMap(node -> node.setOnMousePressed(e -> onMapClick(e)));
    }

    @Override
    public void onMapClick(MouseEvent event) {
        var selected = checkPointSelected(event.getX() + X_ADJUSTMENT, event.getY() + Y_ADJUSTMENT);
        if(selected){
            selectedPath.removePoint(selectedPoint);
            map.removeShape(selectedPoint.getPoint());
            updatePathLines(selectedPath);
        }
    }

    @Override
    protected void enableTool() {
        scene.setCursor(DELETE_CURSOR);
        setMouseActions();
    }

    @Override
    protected void disableTool() {
        scene.setCursor(Cursor.DEFAULT);
        removeMouseActions();
    }
}
