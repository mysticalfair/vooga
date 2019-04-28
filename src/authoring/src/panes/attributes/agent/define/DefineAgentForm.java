package panes.attributes.agent.define;

import authoring.IActionDecisionDefinition;
import authoring.IAgentDefinition;
import authoring.IPropertyDefinition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import panes.AuthoringPane;
import panes.ConsolePane;
import util.AuthoringContext;

import java.util.List;


public class DefineAgentForm extends AuthoringPane {

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

        saveButton = new Button(getContext().getString("Save"));
        cancelButton = new Button(getContext().getString("Cancel"));

        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(saveButton, cancelButton);
        vBox.getChildren().add(buttonsHBox);

        vBox.getChildren().add(new Label(""));

        commonAgentFieldsForm = new CommonAgentFieldsForm(getContext());
        commonAgentFieldsForm.accessContainer(vBox.getChildren()::add);

        vBox.getChildren().add(new Label(""));

        agentPropertiesForm = new AgentPropertiesForm(getContext());
        agentPropertiesForm.accessContainer(vBox.getChildren()::add);

        vBox.getChildren().add(new Label(""));

        actionDecisionForm = new ActionDecisionForm(getContext());
        actionDecisionForm.accessContainer(vBox.getChildren()::add);

        getContentChildren().add(vBox);
    }

    public void setOnSave(EventHandler onSave) {
        saveButton.setOnAction(onSave);
    }

    public void setOnCancel(EventHandler onCancel) {
        cancelButton.setOnAction(onCancel);
    }

    public IAgentDefinition getAgentDefinition() {
        if (commonAgentFieldsForm.getName().equals("")) {
            getContext().displayConsoleMessage(getContext().getString("AgentNeedsName"), ConsolePane.Level.ERROR);
            printAgentNotCreated();
            return null;
        }
        if (commonAgentFieldsForm.getWidth() == getContext().getInt("ErrorInt")) {
            getContext().displayConsoleMessage(getContext().getString("WidthMustBeInt"), ConsolePane.Level.ERROR);
            printAgentNotCreated();
            return null;
        }
        if (commonAgentFieldsForm.getHeight() == getContext().getInt("ErrorInt")) {
            getContext().displayConsoleMessage(getContext().getString("HeightMustBeInt"), ConsolePane.Level.ERROR);
            printAgentNotCreated();
            return null;
        }
        if (commonAgentFieldsForm.getImageUrl().isBlank()) {
            getContext().displayConsoleMessage(getContext().getString("MalformedAgentImagePath"), ConsolePane.Level.ERROR);
            printAgentNotCreated();
            return null;
        }
        List<IPropertyDefinition> properties = agentPropertiesForm.packageData();
        if (properties.contains(null)) {
            printAgentNotCreated();
            return null;
        }
        List<IActionDecisionDefinition> actionDecisions = actionDecisionForm.packageData();
        if (actionDecisions.contains(null)) {
            printAgentNotCreated();
            return null;
        }
        return getContext().getGameFactory().createAgent(0, 0, commonAgentFieldsForm.getWidth(), commonAgentFieldsForm.getHeight(),
                0, commonAgentFieldsForm.getName(), commonAgentFieldsForm.getImageUrl(),
                actionDecisions, properties);
    }

    private void printAgentNotCreated() {
        getContext().displayConsoleMessage(getContext().getString("AgentNotCreated"), ConsolePane.Level.ERROR);
    }
}
