package panes.tools;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import panes.MapPane;
import panes.Path;

import java.util.List;

public class PathPenTool extends PathTool{

    public static final Image PEN = new Image(ToolbarPane.PEN_IMAGE);
    public static final ImageCursor PEN_CURSOR = new ImageCursor(PEN, PEN.getWidth() / 2, PEN.getWidth()/2);
    public static final double X_ADJUSTMENT = -10;
    public static final double Y_ADJUSTMENT = -10;

    private Path currentPath;

    public PathPenTool(MapPane otherMap, Scene otherScene, String fileName, List<Path> paths){
        super(otherMap, otherScene, fileName, paths);
    }

    public void setMouseActions(){
        map.accessMap(node -> node.setOnMousePressed(e -> onMapClick(e)));
    }

    @Override
    public void onMapClick(MouseEvent event) {
        var circle = currentPath.addPoint(event.getX() + X_ADJUSTMENT, event.getY() + Y_ADJUSTMENT);
        map.spawnShape(circle);
        updatePathLines(currentPath);
    }

    @Override
    protected void enableTool() {
        scene.setCursor(PEN_CURSOR);
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
