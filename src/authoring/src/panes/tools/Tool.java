package panes.tools;

import javafx.scene.Scene;
import panes.MapPane;
import util.AuthoringContext;

public abstract class Tool {

    protected String imageFile;
    protected boolean toolEnabled;
    protected MapPane map;
    protected Scene scene;
    private AuthoringContext context;

    public Tool(AuthoringContext authoringContext, MapPane otherMap, Scene otherScene, String fileName){
        map = otherMap;
        scene = otherScene;
        toolEnabled = false;
        imageFile = fileName;
        context = authoringContext;
    }

    protected AuthoringContext getContext() {
        return context;
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
