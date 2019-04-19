package panes.attributes.agent.define;

import authoring.GameFactory;
import authoring.IAgentDefinition;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import panes.AuthoringEnvironment;
import panes.ConsolePane;
import panes.attributes.AttributesForm;
import util.AuthoringContext;

import java.util.ResourceBundle;


public class DefineAgentForm extends AttributesForm {

    private CommonAgentFieldsForm commonAgentFieldsForm;
    private AgentPropertiesForm agentPropertiesForm;
    private ActionDecisionForm actionDecisionForm;
    private Button saveButton, cancelButton;

    public DefineAgentForm(AuthoringContext context) {
        super(context);
        init();
    }

    private void init() {
        VBox vBox = new VBox();

        saveButton = new Button(context.getString("Save"));
        cancelButton = new Button(context.getString("Cancel"));

        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(saveButton, cancelButton);
        vBox.getChildren().add(buttonsHBox);

        vBox.getChildren().add(new Label(""));

        commonAgentFieldsForm = new CommonAgentFieldsForm(context);
        commonAgentFieldsForm.accessContainer(vBox.getChildren()::add);

        vBox.getChildren().add(new Label(""));

        agentPropertiesForm = new AgentPropertiesForm(context);
        agentPropertiesForm.accessContainer(vBox.getChildren()::add);

        vBox.getChildren().add(new Label(""));

        actionDecisionForm = new ActionDecisionForm(context);
        actionDecisionForm.accessContainer(vBox.getChildren()::add);

        pane.getChildren().add(vBox);
    }

    public void setOnSave(EventHandler onSave) {
        saveButton.setOnAction(onSave);
    }

    public void setOnCancel(EventHandler onCancel) {
        cancelButton.setOnAction(onCancel);
    }

    public IAgentDefinition getAgentDefinition() {
        String name = commonAgentFieldsForm.getName();
        if (name.equals("")) {
            context.displayConsoleMessage(context.getString("AgentNeedsName"), ConsolePane.Level.ERROR);
        }
        return context.getGameFactory().createAgent(0, 0, commonAgentFieldsForm.getWidth(), commonAgentFieldsForm.getHeight(),
                commonAgentFieldsForm.getImageUrl(), actionDecisionForm.getActionDecisionDefinitions(),
                agentPropertiesForm.getPropertyDefinitions());
    }
}
