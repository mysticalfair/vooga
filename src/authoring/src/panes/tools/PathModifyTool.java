package panes.tools;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import panes.MapPane;
import panes.Path;
import panes.PathPoint;

import java.util.List;

public abstract class PathModifyTool extends PathTool {

    protected Path selectedPath;
    protected PathPoint selectedPoint;

    /**
     * These tools have an action in the MapPane on click
     *
     * @param otherMap
     * @param otherScene
     * @param fileName
     * @param paths
     */
    public PathModifyTool(MapPane otherMap, Scene otherScene, String fileName, List<Path> paths) {
        super(otherMap, otherScene, fileName, paths);
    }

    public void updatePaths(List<Path> paths){
        pathOptions = paths;
    }

    protected boolean checkPointSelected(MouseEvent event){
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
}
