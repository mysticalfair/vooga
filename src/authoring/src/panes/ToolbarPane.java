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

    public static final double HEIGHT = 50;
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
        var box = new VBox();
        box.getStylesheets().add(STYLE);
        box.getChildren().addAll(menuBar, toolBar);
        box.setPrefSize(AuthoringEnvironment.DEFAULT_WIDTH, HEIGHT);
        getContentChildren().add(box);
    }

    private MenuBar initMenuBar(){
        var menu = new MenuBar();
        menu.setPrefSize(AuthoringEnvironment.DEFAULT_WIDTH, (1/3)*HEIGHT);
        for(String option: MENU_OPTIONS){
            var menuOption = new Menu(option);
            menu.getMenus().add(menuOption);
            menuMap.put(option, menuOption);
        }
        /*var file = new Menu("File");
        file.getItems().addAll(initFileMenu());
        var edit = new Menu("Edit");
        var view = new Menu("View");
        menuMap.put()
        menu.getMenus().addAll(file, edit, view);*/
        return menu;
    }

    /*private List<MenuItem> initFileMenu(){
        var upload = new MenuItem("Upload Image");
        var save = new MenuItem("Save Game");
        var open = new MenuItem("Open Game");
        return List.of(upload, save, open);
    }*/

    private ToolBar initToolBar(){
        var toolbar = new ToolBar();
        toolbar.setPrefSize(AuthoringEnvironment.DEFAULT_WIDTH, (2/3)*HEIGHT);
        var lasso = new Button();
        var image = new ImageView(new Image(LASSO_IMAGE));
        image.setFitWidth(10);
        image.setFitHeight(10);
        lasso.setGraphic(image);
        toolbar.getItems().addAll(lasso);
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

    @Override
    public void setStylesheet(String url) {

    }
}
