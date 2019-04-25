package panes.tools;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import panes.AuthoringEnvironment;
import panes.AuthoringPane;
import panes.Path;
import util.AuthoringContext;
import util.AuthoringUtil;
import panes.MapPane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ToolbarPane extends AuthoringPane {

    /**
     * Want it so the public method called from AuthoringEnvironment gives an EventHandler for Button to Toolbar, then Toolbar sets the button action to this eventHandler AND the toggle method of the tool
     * The AuthoringEnvironment is responsible for giving the Map and Scene to the ToolbarPane when needed for Tools
     */

    private MenuBar menuBar;
    private ToolBar toolBar;
    private Spinner levelChanger;
    private Map<String, Menu> menuMap;
    private VBox box;
    private Map<String, Tool> toolImageMap;
    private List<Path> pathOptions;

    private MapPane map;
    private Scene scene;

    public static final double HEIGHT = AuthoringEnvironment.TOOLBAR_HEIGHT;
    public static final double WIDTH = AuthoringEnvironment.DEFAULT_WIDTH;
    public static final double BUTTON_IMAGE_SIZE = 10;
    public static final double BUTTON_SIZE = 20;
    public static final double TOOLBAR_PADDING = 25;
    public static final double TOOLBAR_HEIGHT = BUTTON_SIZE + TOOLBAR_PADDING;
    public static final double INITIAL_LEVEL = 1;
    public static final double MAX_LEVEL = Integer.MAX_VALUE;
    public static final String STYLE = "toolbar-pane.css";
    public static final String LASSO_IMAGE = "Lasso.png";
    public static final String PEN_IMAGE = "Pen.png";
    public static final String GRAB_IMAGE = "GrabIcon.png";
    public static final String DELETE_IMAGE = "DeletePen.png";
    public static final List<String> MENU_OPTIONS = List.of("File", "Edit", "View");

    public ToolbarPane(AuthoringContext context, MapPane authorMap, Scene authorScene, List<Path> paths){
        super(context);
        map = authorMap;
        scene = authorScene;
        menuMap = new HashMap<>();
        toolImageMap = new HashMap<>();
        pathOptions = paths;
        initBars();
        initLevelChanger();
    }

    private void initBars(){
        menuBar = initMenuBar();
        toolBar = initToolBar();
        toolBar.setId("toolbar");
        box = new VBox();
        box.getStylesheets().add(STYLE);
        box.getChildren().addAll(menuBar, toolBar);
        box.setPrefSize(WIDTH, HEIGHT);
        getContentChildren().add(box);
    }


    private MenuBar initMenuBar(){
        var menu = new MenuBar();
        for(String option: MENU_OPTIONS){
            var menuOption = new Menu(option);
            menu.getMenus().add(menuOption);
            menuMap.put(option, menuOption);
        }
        return menu;
    }

    private ToolBar initToolBar(){
        var toolbar = new ToolBar();
        toolbar.setPrefSize(WIDTH, TOOLBAR_HEIGHT);

        var lasso = new LassoTool(map, scene, LASSO_IMAGE);
        var pen = new PathPenTool(map, scene, PEN_IMAGE, pathOptions);
        var dragger = new PathDragTool(map, scene, GRAB_IMAGE, pathOptions);
        var remover = new PathDeleteTool(map, scene, DELETE_IMAGE, pathOptions);

        toolImageMap.put(LASSO_IMAGE, lasso);
        toolImageMap.put(PEN_IMAGE, pen);
        toolImageMap.put(GRAB_IMAGE, dragger);
        toolImageMap.put(DELETE_IMAGE, remover);
        return toolbar;
    }

    private void initLevelChanger() {
        levelChanger = new Spinner(INITIAL_LEVEL, MAX_LEVEL, INITIAL_LEVEL);
        levelChanger.setPrefHeight(5);
//        levelChanger.setMinHeight(15);

        final Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        toolBar.getItems().addAll(levelChanger, spacer);
    }

    public Spinner getLevelChanger() {
        return levelChanger;
    }

    /**
     * Called from the AuthoringEnvironment class to add actions to the MenuBar
     * @param menuName String representing the key of the menu item in the map
     * @param label String representing the text in the menu
     * @param action Action on clicking the item in the menu
     */
    public void addAction(String menuName, String label, EventHandler action){
        var menu = menuMap.get(menuName);
        var menuItem = new MenuItem(label);
        menuItem.setOnAction(action);
        menu.getItems().add(menuItem);
    }

    public void addButton(String buttonImageName, double buttonSize, double buttonImageSize, EventHandler action){
        Button button = AuthoringUtil.createSquareImageButton(buttonImageName, buttonSize, buttonImageSize, action);
        EventHandler handler = e -> toolImageMap.get(buttonImageName).toggleToolEnabled();
        button.addEventHandler(ActionEvent.ACTION, handler);
        toolBar.getItems().addAll(button);
    }

    public void addButton(String buttonImageName, EventHandler action) {
        addButton(buttonImageName, BUTTON_SIZE, BUTTON_IMAGE_SIZE, action);
    }

    public double getHeight() {
        return this.menuBar.getHeight() + this.toolBar.getHeight();
    }

    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void updateSize(double width, double height) {
        box.setPrefSize(width, height);
    }
}
