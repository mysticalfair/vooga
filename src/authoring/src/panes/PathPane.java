package panes;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import panes.tools.PathPenTool;
import util.AuthoringContext;
import util.AuthoringUtil;

import java.util.HashMap;

public class PathPane extends AuthoringPane {

    private ScrollPane scrollPane;
    private ObservableList<Path> pathOptions;
    private PathPenTool pen;
    private VBox fullBox;
    private VBox pathsBox;
    private HashMap<Path, HBox> pathDisplayMap;

    public PathPane(AuthoringContext context, MapPane otherMap, Scene otherScene, ObservableList<Path> paths) {
        super(context);
        pathDisplayMap = new HashMap<>();
        pathOptions = paths;
        pathOptions.addListener((ListChangeListener<Path>) c -> onPathListChange(c));
        var penToolFile = getContext().getString("PenFile");
        pen = new PathPenTool(context, otherMap, otherScene, penToolFile, paths);
        initializePathDisplays();
    }

    // Code adapted from https://docs.oracle.com/javase/8/javafx/api/javafx/collections/ListChangeListener.Change.html
    private void onPathListChange(ListChangeListener.Change<? extends Path> c){
        System.out.println("Path list has changed");
        while (c.next()) {
            for (Path removed : c.getRemoved()) {
                removePathRow(removed);
            }
            for (Path added : c.getAddedSubList()) {
                System.out.println("Path has been added");
                addPathRow(added);
            }
        }
    }

    private void initializePathDisplays(){
        fullBox = buildFullBox();

        pathsBox = new VBox();
        scrollPane = buildScrollPane(pathsBox);
        pathsBox.setFillWidth(true);
        initPathsBox();

        var title = new Text(getContext().getString("PathsTitle"));
        title.setId(getContext().getString("PathsTitleId"));
        fullBox.getChildren().addAll(title, scrollPane);
        getContentChildren().add(fullBox);
    }

    private void initPathsBox(){
        for(Path p: pathOptions){
            addPathRow(p);
        }
    }

    private void removePathRow(Path path){
        var row = pathDisplayMap.get(path);
        pathsBox.getChildren().remove(row);
    }

    private void addPathRow(Path path){
        var row = new HBox();
        var id = path.getID();
        var pathLabel = new Label(getContext().getString("PathLabel") + id);
        pathLabel.setId(getContext().getString("PathLabelId"));

        var addButton = AuthoringUtil.createSquareImageButton(getContext().getString("PenFile"), getContext().getDouble("ButtonSize"), getContext().getDouble("ButtonImageSize"), e -> pen.enableToolWithPath(id));
        var deleteButton = AuthoringUtil.createSquareImageButton(getContext().getString("PathTrashFile"), getContext().getDouble("ButtonSize"), getContext().getDouble("ButtonImageSize"), e -> pen.removePathFromID(id));
        row.getChildren().addAll(pathLabel, addButton, deleteButton);
        pathDisplayMap.put(path, row);
        pathsBox.getChildren().add(row);
    }

    // TODO: sets bounds for dragging points to be the Map

    private VBox buildFullBox(){
        var box = new VBox();
        box.getStylesheets().add(getContext().getString("PathPaneStyle"));
        box.setId(getContext().getString("PathGeneralId"));
        box.setPrefSize(getContext().getDouble("PathPaneWidth"), getContext().getDouble("ConsoleHeight"));
        return box;
    }

    private ScrollPane buildScrollPane(VBox content){
        var pane = new ScrollPane();
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        pane.setContent(content);
        return pane;
    }

    @Override
    public void updateSize(double width, double height) {
        fullBox.setPrefSize(width, height);
    }
}
