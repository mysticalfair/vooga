package panes;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AttributesPane extends AuthoringPane {

    private ScrollPane scrollPane;

    public AttributesPane() {
        super();
        DefineAgentBox defineAgentBox = new DefineAgentBox();
        defineAgentBox.accessVBox(vBox -> scrollPane.setContent(vBox));
        getContentChildren().add(scrollPane);

    }

    @Override
    public void setStylesheet(String url) {

    }

//    @Override
//    public void addButton(String label, EventHandler action) {
//
//    }

}
