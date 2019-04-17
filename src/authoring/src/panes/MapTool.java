package panes;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public abstract class MapTool extends Tool{

    /**
     * These tools have an action in the MapPane on click
     *
     * @param otherMap
     * @param otherScene
     * @param fileName
     */
    public MapTool(MapPane otherMap, Scene otherScene, String fileName) {
        super(otherMap, otherScene, fileName);
    }

    public abstract void onMapClick(MouseEvent event);
}
