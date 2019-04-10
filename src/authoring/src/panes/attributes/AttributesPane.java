package panes.attributes;

import javafx.scene.control.ScrollPane;
import panes.AuthoringEnvironment;
import panes.AuthoringPane;
import panes.ConsolePane;
import panes.ToolbarPane;

import java.util.HashMap;

public class AttributesPane extends AuthoringPane {

    private ScrollPane scrollPane;

    public static final double WIDTH = AuthoringEnvironment.DEFAULT_WIDTH/4;
    public static final double HEIGHT = AuthoringEnvironment.DEFAULT_HEIGHT - ConsolePane.HEIGHT - ToolbarPane.HEIGHT;

    public AttributesPane() {
        super();
        scrollPane = new ScrollPane();
        scrollPane.setPrefViewportWidth(WIDTH);
        scrollPane.setPrefViewportHeight(HEIGHT);
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
