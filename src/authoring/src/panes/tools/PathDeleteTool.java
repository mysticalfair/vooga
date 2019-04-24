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

    public static final Image PEN = new Image(ToolbarPane.PEN_IMAGE);
    public static final ImageCursor PEN_CURSOR = new ImageCursor(PEN, PEN.getWidth() / 2, PEN.getWidth()/2);

    public PathDeleteTool(MapPane otherMap, Scene otherScene, String fileName, List<Path> paths){
        super(otherMap, otherScene, fileName, paths);
    }

    public void setMouseActions(){
        map.accessMap(node -> node.setOnMousePressed(e -> onMapClick(e)));
    }

    @Override
    public void onMapClick(MouseEvent event) {
        var selected = checkPointSelected(event);
        if(selected){
            selectedPath.removePoint(selectedPoint);
            map.removeShape(selectedPoint.getPoint());
            updatePathLines(selectedPath);
        }
    }

    @Override
    protected void enableTool() {
        setMouseActions();
    }

    @Override
    protected void disableTool() {
        scene.setCursor(Cursor.DEFAULT);
        removeMouseActions();
    }
}
