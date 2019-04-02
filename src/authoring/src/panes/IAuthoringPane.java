package panes;

import javafx.event.EventHandler;
import javafx.scene.Node;

import java.util.function.Consumer;

public interface IAuthoringPane {
    void setStylesheet(String url);
    void addButton(String label, EventHandler action);
//    void addToParent(Consumer<Node> addMethod);
}
