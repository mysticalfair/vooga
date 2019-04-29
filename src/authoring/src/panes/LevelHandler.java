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


    private void clearGameLevelContents(ILevelDefinition gameLevel){
        for(String pathName: gameLevel.getPaths().keySet()){
            gameLevel.removePath(pathName);
        }
        for(String agent: gameLevel.getPlaceableAgents()){
            gameLevel.removePlaceableAgent(agent);
        }
        // TODO: is this needed?
        for(int i=0; i<gameLevel.getCurrentAgents().size(); i++){
            gameLevel.removeAgent(i);
        }
    }

    public void makeLevelFromExisting(int existingLevel, int newLevel) {
        console.displayMessage("Level " + newLevel + " created from Level: " + toolbarPane.getExistingLevelValue(), ConsolePane.Level.NEUTRAL);
        // Deal with making new level from existing level's "backend" state.
        ILevelDefinition oldLevel = context.getState().getLevels().get(existingLevel - 1);
        ILevelDefinition level = context.getGameFactory().createLevel();
        try {
            // Clone old level onto new level
            level = oldLevel.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("F");
        }
        context.getState().addLevel(level);
    }

    void makeLevel(int newLevel, MapState state, boolean fromExisting) {
        String newLevelDisplay;
        if (fromExisting) {
            newLevelDisplay = "Level " + newLevel + " created from Level: " + toolbarPane.getExistingLevelValue();
        } else {
            newLevelDisplay = "Level " + newLevel + " created";
        }
        map.setLevel(newLevel);
        toolbarPane.setMaxLevel(newLevel);
        toolbarPane.addToExistingLevelCreator(newLevel);
        console.displayMessage(newLevelDisplay, ConsolePane.Level.NEUTRAL);
        if (!map.getStateMapping().containsKey(newLevel)) {
            map.getStateMapping().put(newLevel, state);

            ILevelDefinition level = context.getGameFactory().createLevel();
            context.getState().addLevel(level);
            // TODO: add code to add Level contents


            map.getCurrentState().accessSelectCount(countProperty -> establishSelectCountListener(countProperty));

            // Front end sets itself to this new state.
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

