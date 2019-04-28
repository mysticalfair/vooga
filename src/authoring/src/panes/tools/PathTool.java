package panes.tools;

import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import panes.MapPane;
import panes.Path;
import util.AuthoringContext;

import java.util.List;

public abstract class PathTool extends MapTool{

    protected List<Path> pathOptions;

    /**
     * These tools have an action in the MapPane on click
     *
     * @param otherMap
     * @param otherScene
     * @param fileName
     */
    public PathTool(AuthoringContext context, MapPane otherMap, Scene otherScene, String fileName, List<Path> paths) {
        super(context, otherMap, otherScene, fileName);
        pathOptions = paths;
    }

    protected void addPath(Path path){
        if(!pathOptions.contains(path)){
            pathOptions.add(path);
        }
    }

    protected void removePath(Path path){
        if(pathOptions.contains(path)){
            pathOptions.remove(path);
            for(Line l: path.getLines()){
                map.removeShape(l);
            }
            for(Circle c: path.getPoints()){
                map.removeShape(c);
            }
        }
    }

    protected void updatePathLines(Path path){
        for(Line l: path.getLines()){
            map.removeShape(l);
        }
        for(Line l: path.updateLines()){
            map.spawnShape(l);
        }
    }

    protected void removeMouseActions(){
        map.accessMap(node -> node.setOnMousePressed(null));
        map.accessMap(node -> node.setOnMouseDragged(null));
        map.accessMap(node -> node.setOnMouseReleased(null));
    }

    protected Path getPath(int pathIndex){
        return pathOptions.get(pathIndex);
    }
}
