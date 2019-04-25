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

    public void setToolEnabled(boolean enable){
        toolEnabled = enable;
        if(toolEnabled){
            enableTool();
        }
        else{
            disableTool();
        }
    }

    public boolean getToolEnabled(){
        return toolEnabled;
    }

    protected abstract void enableTool();

    protected abstract void disableTool();
}
