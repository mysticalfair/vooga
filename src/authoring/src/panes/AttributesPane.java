package panes;

import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AttributesPane extends AuthoringPane {

    public AttributesPane(AuthoringEnvironment author) {
        super(author);
        VBox attributesVBox = new VBox();
        attributesVBox.getChildren().add(new Text("Testing text in attributes pane"));
        getContentChildren().add(attributesVBox);

    }

    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void addButton(String label, EventHandler action) {

    }

}
