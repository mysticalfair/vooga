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
import panes.attributes.FormElement;
import util.AuthoringContext;

import java.util.List;


public class DefineAgentForm extends FormElement {

    private Button saveButton, cancelButton;
    private CommonAgentFieldsForm commonAgentFieldsForm;
    private AgentPropertiesForm agentPropertiesForm;
    private ActionDecisionForm actionDecisionForm;

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

    public void loadFromExisting(IAgentDefinition agent) {
        commonAgentFieldsForm.loadFromExisting(agent.getName(), agent.getWidth(), agent.getHeight(), agent.getImageURL());
        agentPropertiesForm.loadFromExisting(agent.getProperties());
        actionDecisionForm.loadFromExisting(agent.getActionDecisions());
    }

    public void setOnSave(EventHandler onSave) {
        saveButton.setOnAction(onSave);
    }

    public void setOnCancel(EventHandler onCancel) {
        cancelButton.setOnAction(onCancel);
    }

    /**
     * Packages the data from this form into an IAgentDefinition.
     * @return the IAgentDefinition containing the data from this form.
     */
    @Override
    public IAgentDefinition packageData() {
        if (commonAgentFieldsForm.getName().equals("")) {
            getContext().displayConsoleMessage(getContext().getString("AgentNeedsName"), ConsolePane.Level.ERROR);
            return null;
        }
        for (IAgentDefinition agent : getContext().getState().getDefinedAgents()) {
            if (agent.getName().equals(commonAgentFieldsForm.getName())) {
                getContext().displayConsoleMessage(String.format(getContext().getString("AgentAlreadyExists"), agent.getName()), ConsolePane.Level.ERROR);
                return null;
            }
        };
        if (commonAgentFieldsForm.getWidth() == getContext().getInt("ErrorInt")) {
            getContext().displayConsoleMessage(getContext().getString("WidthMustBeInt"), ConsolePane.Level.ERROR);
            return null;
        }
        if (commonAgentFieldsForm.getHeight() == getContext().getInt("ErrorInt")) {
            getContext().displayConsoleMessage(getContext().getString("HeightMustBeInt"), ConsolePane.Level.ERROR);
            return null;
        }
        if (commonAgentFieldsForm.getImageUrl().isBlank()) {
            getContext().displayConsoleMessage(getContext().getString("MalformedAgentImagePath"), ConsolePane.Level.ERROR);
            return null;
        }
        List<IPropertyDefinition> properties = agentPropertiesForm.packageData();
        if (properties.contains(null)) {
            return null;
        }
        List<IActionDecisionDefinition> actionDecisions = actionDecisionForm.packageData();
        if (actionDecisions.contains(null)) {
            return null;
        }
        return getContext().getGameFactory().createAgent(0, 0, commonAgentFieldsForm.getWidth(), commonAgentFieldsForm.getHeight(),
                0, commonAgentFieldsForm.getName(), commonAgentFieldsForm.getImageUrl(),
                actionDecisions, properties);
    }
}
