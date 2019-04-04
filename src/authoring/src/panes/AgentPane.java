package panes;

import frontend_objects.AgentView;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AgentPane extends AuthoringPane {

    private VBox vbox;
    private AgentView testAgent;

    public AgentPane() {
        super();
        vbox = new VBox();
        vbox.getChildren().add(new Text("hi there!"));
        vbox.getChildren().add(new Text("This text is housed within a VBox\nthat is housed within the\nAuthoringPane's content's children."));
        testAgent = new AgentView("Tower.jpg");
        vbox.getChildren().add(testAgent.getView());
        getContentChildren().add(vbox);
    }

    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void addButton(String label, EventHandler action) {
        var button = new Button(label);
        button.setOnAction(action);
        vbox.getChildren().add(button);
    }

}
