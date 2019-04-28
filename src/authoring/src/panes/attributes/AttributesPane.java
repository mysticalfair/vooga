package panes.attributes;

import authoring.IAgentDefinition;
import javafx.scene.control.ScrollPane;
import panes.AuthoringPane;
import panes.ConsolePane;
import panes.attributes.agent.define.DefineAgentForm;
import util.AuthoringContext;

public class AttributesPane extends AuthoringPane {

    private ScrollPane scrollPane;

    public AttributesPane(AuthoringContext context) {
        super(context);
        setStylesheet(getContext().getString("AttributesStyle"));
        
        scrollPane = new ScrollPane();
        updateSize(getContext().getDouble("AttributesWidth"), getContext().getDouble("MiddleRowHeight"));
        //scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        getContentChildren().add(scrollPane);
    }

    @Override
    public void updateSize(double width, double height) {
        scrollPane.setPrefViewportWidth(width - getContext().getDouble("AttributesPadding"));
        scrollPane.setPrefViewportHeight(height - getContext().getDouble("AttributesPadding"));
    }

    public double getWidth() {
        return scrollPane.getWidth();
    }

    public void createNewAgentForm() {
        scrollPane.setContent(null);
        DefineAgentForm defineAgentForm = new DefineAgentForm(getContext());
        defineAgentForm.accessContainer(scrollPane::setContent);
        defineAgentForm.setOnCancel(e -> scrollPane.setContent(null));
        defineAgentForm.setOnSave(e -> {
            IAgentDefinition a = defineAgentForm.getAgentDefinition();
            if (a == null) {
                return;
            }
            String testString = String.format("New agent created: %s\nx: %d y: %d w: %d h: %d\nimage: %s\n" +
                    "number of properties: %d\nnumber of action decisions: %d",
                    a.getName(),
                    (int) a.getX(), (int) a.getY(), a.getWidth(), a.getHeight(),
                    a.getImageURL(),
                    a.getProperties().size(),
                    a.getActionDecisions().size()
                    );
            getContext().displayConsoleMessage(testString, ConsolePane.Level.SUCCESS);
        });
    }

}
