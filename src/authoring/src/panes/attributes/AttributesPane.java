package panes.attributes;

import javafx.scene.control.ScrollPane;
import panes.AuthoringEnvironment;
import panes.AuthoringPane;
import panes.attributes.agent.define.DefineAgentForm;
import util.AuthoringContext;

public class AttributesPane extends AuthoringPane {

    private ScrollPane scrollPane;

    public static final double WIDTH = AuthoringEnvironment.ATTRIBUTES_WIDTH;
    public static final double HEIGHT = AuthoringEnvironment.MIDDLE_ROW_HEIGHT;
    public static final double PADDING = 20;

    public AttributesPane(AuthoringContext context) {
        super(context);
        setStylesheet("attributes-pane.css");
        
        scrollPane = new ScrollPane();
        scrollPane.setPrefViewportWidth(WIDTH - PADDING);
        scrollPane.setPrefViewportHeight(HEIGHT - PADDING);
        //scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        //scrollPane.getStylesheets().add("attributes-pane.css");
        getContentChildren().add(scrollPane);
    }

    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void updateSize(double width, double height) {
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

    public void createNewAgentForm() {
        scrollPane.setContent(null);
        DefineAgentForm defineAgentForm = new DefineAgentForm(getContext());
        defineAgentForm.accessContainer(scrollPane::setContent);
        defineAgentForm.setOnCancel(e -> scrollPane.setContent(null));
        defineAgentForm.setOnSave(e -> defineAgentForm.getAgentDefinition());
    }

}
