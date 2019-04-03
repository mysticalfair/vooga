package panes;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.util.function.Consumer;

public abstract class AuthoringPane implements IAuthoringPane {

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
    }

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

}
