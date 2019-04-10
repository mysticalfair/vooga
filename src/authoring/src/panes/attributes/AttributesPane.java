package panes.attributes;

import javafx.scene.control.ScrollPane;
import panes.AuthoringPane;

public class AttributesPane extends AuthoringPane {

    private ScrollPane scrollPane;

    public AttributesPane() {
        super();
        scrollPane = new ScrollPane();
        DefineAgentForm defineAgentForm = new DefineAgentForm();
        defineAgentForm.accessContainer(container -> scrollPane.setContent(container));
        getContentChildren().add(scrollPane);
        scrollPane.getStylesheets().add("attributes-pane.css");

    }

    @Override
    public void setStylesheet(String url) {

    }

//    @Override
//    public void addButton(String label, EventHandler action) {
//
//    }

    public double getWidth() {
        return scrollPane.getWidth();
    }

}
