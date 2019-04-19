package panes;

import javafx.event.EventHandler;

public interface IAuthoringPane {
    // TODO: Actually implement setStylesheet in things
    void setStylesheet(String url);
    void updateSize(double width, double height);
//    void addButton(String label, EventHandler action);
//    void addToParent(Consumer<Node> addMethod);
}
