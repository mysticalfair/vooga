package panes;

import javafx.beans.binding.ObjectExpression;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.function.Consumer;

public abstract class AuthoringPane implements IAuthoringPane, AccessibleContainer {

    static final double HANDLE_THICKNESS = 20;
    static final double HANDLE_ROUNDING_DIAMETER = 20;

    private Pane container;
    private Pane hvbox;
    private boolean vertical;
    private Pane handle;
    private Pane content;

    public AuthoringPane() {
        container = new Pane();
        handle = new Pane();
        content = new Pane();
        setVertical(true);
    }

    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void accessContainer(Consumer<Pane> accessMethod) {
        accessMethod.accept(container);
    }

    protected void setVertical(boolean vertical) {
        if (this.vertical == vertical) {
            return;
        }

        this.vertical = vertical;
        container.getChildren().clear();

        if (vertical) {
            hvbox = new VBox();
            handle.prefWidthProperty().bind(hvbox.prefWidthProperty());
        }
        else {
            hvbox = new HBox();
            handle.prefHeightProperty().bind(hvbox.prefHeightProperty());
        }

        hvbox.getChildren().addAll(handle, content);
        container.getChildren().add(hvbox);
    }

    protected ObservableList<Node> getContentChildren() {
        return content.getChildren();
    }

    protected Button createButton(String buttonImageName, double buttonSize, double buttonImageSize, EventHandler action){
        var button = new Button();
        var image = new ImageView(new Image(buttonImageName));
        image.setFitWidth(buttonSize);
        image.setFitHeight(buttonSize);
        button.setGraphic(image);
        button.setPrefSize(buttonImageSize, buttonImageSize);
        button.setOnAction(action);
        return button;
    }
}
