package panes;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.function.Consumer;

public abstract class AuthoringPane implements IAuthoringPane {

    protected Node node;

    public void accessNode(Consumer<Node> accessMethod) {
        accessMethod.accept(node);
    }

}
