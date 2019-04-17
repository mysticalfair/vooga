package panes.tools;

import javafx.scene.Scene;
import panes.MapPane;

public abstract class Tool {

    protected String imageFile;
    protected boolean toolEnabled;
    protected MapPane map;
    protected Scene scene;

    public Tool(MapPane otherMap, Scene otherScene, String fileName){
        map = otherMap;
        scene = otherScene;
        toolEnabled = false;
        imageFile = fileName;
    }

    public void toggleToolEnabled(){
        toolEnabled = !toolEnabled;
        if(toolEnabled){
            enableTool();
        }
        else{
            disableTool();
        }
    }

    protected abstract void enableTool();

    protected abstract void disableTool();
}
