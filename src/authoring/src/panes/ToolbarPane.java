package panes;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.List;

public class ToolbarPane extends AuthoringPane{

    private MenuBar menuBar;
    private ToolBar toolBar;

    public static final double HEIGHT = 50;
    public static final double WIDTH = 1000;

    public ToolbarPane(){
        super();
        initBars();
    }

    private void initBars(){
        menuBar = initMenuBar();
        toolBar = initToolBar();
        var box = new VBox();
        box.getStylesheets().add("toolbar-pane.css");
        box.getChildren().addAll(menuBar, toolBar);
        box.setPrefSize(WIDTH, HEIGHT);
        getContentChildren().add(box);
    }

    private MenuBar initMenuBar(){
        var menu = new MenuBar();
        menu.setPrefSize(WIDTH, (1/3)*HEIGHT);
        //menu.getStyleClass().add("toolbar-pane.css");
        var file = new Menu("File");
        file.getItems().addAll(initFileMenu());
        var edit = new Menu("Edit");
        var view = new Menu("View");
        menu.getMenus().addAll(file, edit, view);
        return menu;
    }

    private List<MenuItem> initFileMenu(){
        var upload = new MenuItem("Upload Image");
        var save = new MenuItem("Save Game");
        var open = new MenuItem("Open Game");
        return List.of(upload, save, open);
    }

    private ToolBar initToolBar(){
        var toolbar = new ToolBar();
        toolbar.setPrefSize(WIDTH, (2/3)*HEIGHT);
        var lasso = new Button();
        var image = new ImageView(new Image("Lasso.png"));
        image.setFitWidth(10);
        image.setFitHeight(10);
        lasso.setGraphic(image);
        toolbar.getItems().addAll(lasso);
        return toolbar;
    }

    @Override
    public void setStylesheet(String url) {

    }
}
