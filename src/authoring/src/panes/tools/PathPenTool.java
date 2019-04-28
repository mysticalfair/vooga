package panes.tools;

import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import panes.ConsolePane;
import panes.MapPane;
import panes.Path;
import util.AuthoringContext;


public class PathPenTool extends PathTool{

    private Path currentPath;

    public PathPenTool(AuthoringContext context, MapPane otherMap, Scene otherScene, String fileName, ObservableList<Path> paths){
        super(context, otherMap, otherScene, fileName, paths);
    }

    /*public void setNewPathList(List<Path> newPathList){
        for(Path path: pathOptions){
            removePath(path);
        }
        pathOptions = newPathList;
        for(Path path: pathOptions){
            addPath(path);
        }
    }*/

    public void enableToolWithPath(int pathID){
        selectPath(pathID);
        setupPen();
    }

    public void removePathFromID(int id){
        var pathToRemove = pathOptions.get(id);
        if(pathToRemove != null){
            removeVisualPath(pathToRemove);
            pathOptions.remove(pathToRemove);
        }
    }

    private void selectPath(int index){
        if(currentPath != null){
            currentPath.setStyleSelected(false);
        }
        String messageContent;
        ConsolePane.Level messageLevel;
        if(index == pathOptions.size()){
            currentPath = new Path(index);
            addPath(currentPath);
            messageContent = "PathCreatedMessage";
            messageLevel = ConsolePane.Level.NEUTRAL;
        }
        else if(index < 0 || index > pathOptions.size()){
            messageContent = "InvalidPathError";
            messageLevel = ConsolePane.Level.ERROR;
        }
        else{
            currentPath = pathOptions.get(index);
            messageContent = "PathSelectedMessage";
            messageLevel = ConsolePane.Level.NEUTRAL;
        }
        currentPath.setStyleSelected(true);
        getContext().displayConsoleMessage(getContext().getString(messageContent) + index, messageLevel);
    }

    private void setupPen(){
        var pen = new Image(getContext().getString("PenFile"));
        ImageCursor penCursor = new ImageCursor(pen, 0, 0);
        scene.setCursor(penCursor);
        setMouseActions();
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
        selectPath(pathOptions.size());
        setupPen();
    }

    @Override
    protected void disableTool() {
        scene.setCursor(Cursor.DEFAULT);
        removeMouseActions();
    }
}
