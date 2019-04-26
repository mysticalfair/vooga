package panes.tools;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import panes.MapPane;
import panes.Path;
import util.AuthoringContext;

import java.util.List;

public class PathPenTool extends PathTool{

    private Path currentPath;

    public PathPenTool(AuthoringContext context, MapPane otherMap, Scene otherScene, String fileName, List<Path> paths){
        super(context, otherMap, otherScene, fileName, paths);
    }

    public void setMouseActions(){
        map.accessMap(node -> node.setOnMousePressed(e -> onMapClick(e)));
    }

    @Override
    public void onMapClick(MouseEvent event) {
        var circle = currentPath.addPoint(event.getX(), event.getY());
        map.spawnShape(circle);
        updatePathLines(currentPath);
    }

    @Override
    protected void enableTool() {
        var pen = new Image(getContext().getString("PenFile"));
        ImageCursor penCursor = new ImageCursor(pen, 0, 0);
        scene.setCursor(penCursor);
        currentPath = new Path();
        addPath(currentPath);
        setMouseActions();
    }

    @Override
    protected void disableTool() {
        scene.setCursor(Cursor.DEFAULT);
        removeMouseActions();
    }
}
