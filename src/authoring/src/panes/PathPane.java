package panes;

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

import java.util.List;

public class PathPane extends AuthoringPane {

    private ScrollPane scrollPane;
    private List<Path> pathOptions;
    private PathPenTool pen;
    private VBox fullBox;
    private VBox pathsBox;

    public PathPane(AuthoringContext context, MapPane otherMap, Scene otherScene, String fileName, ObservableList<Path> paths) {
        super(context);
        pathOptions = paths;
        //((ObservableList<Path>) pathOptions).addListener(e -> );
        pen = new PathPenTool(context, otherMap, otherScene, fileName, paths);
        initializePathDisplays();
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
    }

    private void initPathsBox(){
        for(Path p: pathOptions){
            pathsBox.getChildren().add(makeNewPathRow(p));
        }
    }

    private HBox makeNewPathRow(Path path){
        var row = new HBox();
        var id = path.getID();
        var pathLabel = new Label(getContext().getString("PathLabel") + id);
        pathLabel.setId(getContext().getString("PathLabelId"));

        var addButton = AuthoringUtil.createSquareImageButton(getContext().getString("PenFile"), getContext().getDouble("ButtonSize"), getContext().getDouble("ButtonImageSize"), e -> pen.enableToolWithPath(id));
        var deleteButton = AuthoringUtil.createSquareImageButton(getContext().getString("PathTrashFile"), getContext().getDouble("ButtonSize"), getContext().getDouble("ButtonImageSize"), e -> pen.removePathFromID(id));
        row.getChildren().addAll(pathLabel, addButton, deleteButton);
        return row;
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
