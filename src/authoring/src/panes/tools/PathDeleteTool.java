package panes.tools;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import panes.MapPane;

public class PathDeleteTool extends MapTool {

    public PathDeleteTool(MapPane otherMap, Scene otherScene, String fileName){
        super(otherMap, otherScene, fileName);
    }

    @Override
    public void onMapClick(MouseEvent event) {

    }

    @Override
    protected void enableTool() {

    }

    @Override
    protected void disableTool() {

    }
}
