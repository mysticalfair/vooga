package panes;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.function.Consumer;

public class AgentPane extends AuthoringPane {

    public AgentPane() {
        super();
        VBox vbox = new VBox();
        vbox.getChildren().add(new Text("hi there!"));
        vbox.getChildren().add(new Button("hi back!"));
        vbox.getChildren().add(new Text("This text is housed within a VBox\nthat is housed within the\nAuthoringPane's content's children."));
        getContentChildren().add(vbox);
    }

    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void addButton(String label, EventHandler action) {

    }

}
