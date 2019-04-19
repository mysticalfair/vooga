package panes.attributes;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import panes.AuthoringEnvironment;
import panes.AuthoringPane;

public class AttributesPane extends AuthoringPane {

    private ScrollPane scrollPane;
    private VBox vbox;

    public static final double WIDTH = AuthoringEnvironment.ATTRIBUTES_WIDTH;
    public static final double HEIGHT = AuthoringEnvironment.MIDDLE_ROW_HEIGHT;
    public static final double PADDING = 20;

    public AttributesPane() {
        super();
        vbox = new VBox();
        vbox.setPrefSize(WIDTH, HEIGHT);
        scrollPane = new ScrollPane();
        scrollPane.setPrefViewportWidth(WIDTH - PADDING);
        scrollPane.setPrefViewportHeight(HEIGHT - PADDING);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        DefineAgentForm defineAgentForm = new DefineAgentForm();
        defineAgentForm.accessContainer(scrollPane::setContent);

        scrollPane.getStylesheets().add("attributes-pane.css");
        vbox.getChildren().add(scrollPane);
        getContentChildren().add(scrollPane);
    }

    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void updateSize(double width, double height) {
        vbox.setPrefSize(width, height);
        scrollPane.setPrefViewportWidth(width - PADDING);
        scrollPane.setPrefViewportHeight(height - PADDING);
    }

//    @Override
//    public void addButton(String label, EventHandler action) {
//
//    }

    public double getWidth() {
        return scrollPane.getWidth();
    }

}
