package panes.tools;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import panes.ConsolePane;
import panes.MapPane;
import panes.Path;
import util.AuthoringContext;

import java.util.List;

public class PathPenTool extends PathTool{

    private Path currentPath;

    public PathPenTool(AuthoringContext context, MapPane otherMap, Scene otherScene, String fileName, List<Path> paths){
        super(context, otherMap, otherScene, fileName, paths);
    }

    public void enableToolWithPath(int pathID){
        selectPath(pathID);
        setupPen();
    }

    public void removePathFromID(int id){
        var pathToRemove = pathOptions.get(id);
        if(pathToRemove != null){
            removePath(pathToRemove);
        }
    }

    private void selectPath(int index){
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
