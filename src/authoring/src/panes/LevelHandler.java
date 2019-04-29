package panes;

import authoring.ILevelDefinition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import panes.tools.ToolbarPane;
import util.AuthoringContext;
import java.util.ArrayList;

public class LevelHandler {

    private AuthoringContext context;
    private MapPane map;
    private PathPane pathPane;
    private ConsolePane console;
    private ToolbarPane toolbarPane;
    private ObservableList<Path> currentPaths;

    public LevelHandler(AuthoringContext context, MapPane map, PathPane pathPane, ConsolePane console, ToolbarPane toolbarPane,  ObservableList<Path> currentPaths) {
        this.context = context;
        this.map = map;
        this.pathPane = pathPane;
        this.console = console;
        this.toolbarPane = toolbarPane;
        this.currentPaths = currentPaths;
        initPathListeners();
        // Establish select count listener for the first level
        map.getCurrentState().accessSelectCount(countProperty -> establishSelectCountListener(countProperty));

    }

    private void establishSelectCountListener(SimpleIntegerProperty selectCount) {
        selectCount.addListener((observable, oldValue, newValue) -> updateOnLevelSelection((int) newValue));
    }

    private void updateOnLevelSelection(int newValue){
        map.handleSelectionChange(newValue);
        currentPaths.removeListener((ListChangeListener<Path>) c -> onPathListChange(c));
        for(Path path: currentPaths){
            currentPaths.remove(path);
            pathPane.removePathRow(path);
        }
        for(Path path: map.getCurrentState().getPaths()){
            currentPaths.add(path);
            pathPane.addPathRow(path);
        }
        initPathListeners();
        //pathPane.setNewPathList(map.getCurrentState().getPaths());
    }


    private void initPathListeners(){
        currentPaths.addListener((ListChangeListener<Path>) c -> onPathListChange(c));
    }

    private void onPathListChange(ListChangeListener.Change<? extends Path> c){
        while (c.next()) {
            for (Path removed : c.getRemoved()) {
                pathPane.removePathRow(removed);
                map.getCurrentState().removePath(removed);
            }
            for (Path added : c.getAddedSubList()) {
                pathPane.addPathRow(added);
                map.getCurrentState().addToPaths(added);
            }
        }
    }

    void clearLevel() {
        map.clearMap();
        int levelIndex = (int)(double) toolbarPane.getLevelChanger().getValue();
        ILevelDefinition gameLevel = context.getState().getLevels().get(levelIndex-1);
        clearGameLevelContents(gameLevel);
        map.getStateMapping().put(levelIndex, new MapState(context, null, new ArrayList<>(), FXCollections.observableArrayList()));
        map.getCurrentState().accessSelectCount(countProperty -> establishSelectCountListener(countProperty));
        console.displayMessage("Level Cleared", ConsolePane.Level.NEUTRAL);
    }


    private void clearGameLevelContents(ILevelDefinition gameLevel) {
        gameLevel.setBackgroundImageURL(null);
        for(String pathName: gameLevel.getPaths().keySet()){
            gameLevel.removePath(pathName);
        }
        for(int i=0; i<gameLevel.getCurrentAgents().size(); i++){
            gameLevel.removeAgent(i);
        }
    }

    void makeLevel(int newLevel, boolean fromExisting) {
        String newLevelDisplay;
        ILevelDefinition level;
        MapState newState;
        if (fromExisting) {
            newLevelDisplay = "Level " + newLevel + " created from Level: " + toolbarPane.getExistingLevelValue();
            try {
                level = context.getState().getLevels().get(toolbarPane.getExistingLevelValue() - 1).clone();
                context.getState().addLevel(level);
                newState = new MapState(map.getStateMapping().get(toolbarPane.getExistingLevelValue()), map);
            } catch (CloneNotSupportedException e) {
                context.displayConsoleMessage(context.getString("CloneError"), ConsolePane.Level.ERROR);
                return;
            }
        } else {
            level = context.getGameFactory().createLevel();
            newState = new MapState(context, null, new ArrayList<>(), FXCollections.observableArrayList());
            newLevelDisplay = "Level " + newLevel + " created";
            context.getState().addLevel(level);
        }
        map.setLevel(newLevel);
        toolbarPane.setMaxLevel(newLevel);
        toolbarPane.addToExistingLevelCreator(newLevel);
        console.displayMessage(newLevelDisplay, ConsolePane.Level.NEUTRAL);
        if (!map.getStateMapping().containsKey(newLevel)) {
            map.getStateMapping().put(newLevel, newState);
            map.getCurrentState().accessSelectCount(countProperty -> establishSelectCountListener(countProperty));
            MapState revertToState = map.getStateMapping().get(newLevel);
            revertToState.updateMap(map);
        }
        int currentSpinnerValue = (int)(double)toolbarPane.getLevelChanger().getValue();
        toolbarPane.updateSpinner(currentSpinnerValue, newLevel);
    }

    void changeToExistingLevel(int newValue) {
        if (map.getStateMapping().containsKey(newValue)) {
            map.setLevel(newValue);
            MapState revertToState = map.getStateMapping().get(newValue);
            revertToState.updateMap(map);
        }
    }

}

