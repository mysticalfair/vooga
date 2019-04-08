package panes;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AttributesPane extends AuthoringPane {

    private VBox attributesVBox;

    public AttributesPane() {
        super();
        attributesVBox = new VBox();
        attributesVBox.getChildren().add(new Text("Testing text in attributes pane"));
        getContentChildren().add(attributesVBox);

    }

    @Override
    public void setStylesheet(String url) {

    }

    public VBox getVBoxContainer() { return attributesVBox; }

    //@Override
    //public void addButton(String label, EventHandler action) {
    //}

}
