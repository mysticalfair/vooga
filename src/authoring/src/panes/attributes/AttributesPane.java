package panes.attributes;

import javafx.scene.control.ScrollPane;
import panes.AuthoringPane;
import panes.attributes.agent.define.DefineAgentForm;
import util.AuthoringContext;

public class AttributesPane extends AuthoringPane {

    private ScrollPane scrollPane;

    public AttributesPane(AuthoringContext context) {
        super(context);
        scrollPane = new ScrollPane();
        updateSize(getContext().getDouble("AttributesWidth"), getContext().getDouble("MiddleRowHeight"));
        //scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.getStylesheets().add(getContext().getString("AttributesStyle"));
        getContentChildren().add(scrollPane);
    }

    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void updateSize(double width, double height) {
        scrollPane.setPrefViewportWidth(width - getContext().getDouble("AttributesPadding"));
        scrollPane.setPrefViewportHeight(height - getContext().getDouble("AttributesPadding"));
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
