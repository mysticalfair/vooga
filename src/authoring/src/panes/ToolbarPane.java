package panes;

import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolbarPane extends AuthoringPane{

    private MenuBar menuBar;
    private ToolBar toolBar;
    private Map<String, Menu> menuMap;
    private VBox box;

    public static final double HEIGHT = AuthoringEnvironment.TOOLBAR_HEIGHT;
    public static final double WIDTH = AuthoringEnvironment.DEFAULT_WIDTH;
    public static final double BUTTON_IMAGE_WIDTH = 10;
    public static final double BUTTON_IMAGE_HEIGHT = 10;
    public static final double BUTTON_WIDTH = 20;
    public static final double BUTTON_HEIGHT = 20;
    public static final double TOOLBAR_PADDING = 25;
    public static final double TOOLBAR_HEIGHT = BUTTON_HEIGHT + TOOLBAR_PADDING;

    public static final String STYLE = "toolbar-pane.css";
    public static final String LASSO_IMAGE = "Lasso.png";
    public static final List<String> MENU_OPTIONS = List.of("File", "Edit", "View");

    public ToolbarPane(){
        super();
        menuMap = new HashMap<>();
        initBars();
    }

    private void initBars(){
        menuBar = initMenuBar();
        toolBar = initToolBar();
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
        /*var lasso = new Button();
        var image = new ImageView(new Image(LASSO_IMAGE));
        image.setFitWidth(BUTTON_IMAGE_WIDTH);
        image.setFitHeight(BUTTON_IMAGE_HEIGHT);
        lasso.setGraphic(image);
        lasso.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        toolbar.getItems().addAll(lasso);*/
        return toolbar;
    }

    /**
     * Called from the AuthoringEnvironment class to add actions to the MenuBar
     * @param menuName String representing the key of the menu item in the map
     * @param label String representing the text in the menu
     * @param action Action on clicking the item in the menu
     */
    public void addAction(String menuName, String label, EventHandler action){
        var menu = menuMap.get(menuName);
        var menutItem = new MenuItem(label);
        menutItem.setOnAction(action);
        menu.getItems().add(menutItem);
    }

    public void addButton(String buttonImageName, double buttonSize, double buttonImageSize, EventHandler action){
        var button = new Button();
        var image = new ImageView(new Image(buttonImageName));
        image.setFitWidth(buttonSize);
        image.setFitHeight(buttonSize);
        button.setGraphic(image);
        button.setPrefSize(buttonImageSize, buttonImageSize);
        button.setOnAction(action);
        toolBar.getItems().addAll(button);
    }

    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void updateSize(double width, double height) {
        box.setPrefSize(width, height);
    }
}
