package panes.attributes;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import panes.*;

import java.util.HashMap;

public class AttributesPane extends AuthoringPane {

    private ScrollPane scrollPane;

    public static final double WIDTH = AuthoringEnvironment.DEFAULT_WIDTH/2;
    public static final double HEIGHT = AgentPane.HEIGHT;

    public AttributesPane() {
        super();
        var vbox = new VBox();
        scrollPane = new ScrollPane();
        scrollPane.setPrefViewportWidth(WIDTH);
        scrollPane.setPrefViewportHeight(HEIGHT);
        DefineAgentForm defineAgentForm = new DefineAgentForm();
        defineAgentForm.accessContainer(container -> scrollPane.setContent(container));

        scrollPane.getStylesheets().add("attributes-pane.css");
        vbox.getChildren().add(scrollPane);
        getContentChildren().add(vbox);
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
