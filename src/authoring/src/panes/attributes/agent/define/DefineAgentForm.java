package panes.attributes.agent.define;

import authoring.GameFactory;
import authoring.IAgentDefinition;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import panes.AuthoringEnvironment;
import panes.CML;
import panes.attributes.AttributesForm;

import java.util.ResourceBundle;


public class DefineAgentForm extends AttributesForm {

    private CommonAgentFieldsForm commonAgentFieldsForm;
    private AgentPropertiesForm agentPropertiesForm;
    private ActionDecisionForm actionDecisionForm;
    private Button saveButton, cancelButton;

    public DefineAgentForm(ResourceBundle rb, GameFactory gameFactory) {
        super(rb, gameFactory);
        init();
    }

    private void init() {
        VBox vBox = new VBox();

        saveButton = new Button(rb.getString("Save"));
        cancelButton = new Button(rb.getString("Cancel"));

        HBox buttonsHBox = new HBox();
        buttonsHBox.getChildren().addAll(saveButton, cancelButton);
        vBox.getChildren().add(buttonsHBox);

        vBox.getChildren().add(new Label(""));

        commonAgentFieldsForm = new CommonAgentFieldsForm(rb, gameFactory);
        commonAgentFieldsForm.accessContainer(vBox.getChildren()::add);

        vBox.getChildren().add(new Label(""));

        agentPropertiesForm = new AgentPropertiesForm(rb, gameFactory);
        agentPropertiesForm.accessContainer(vBox.getChildren()::add);

        vBox.getChildren().add(new Label(""));

        actionDecisionForm = new ActionDecisionForm(rb, gameFactory);
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
            AuthoringEnvironment.consoleMessage.accept(rb.getString("AgentNeedsName"), CML.ERROR);
        }
        return gameFactory.createAgent(0, 0, commonAgentFieldsForm.getWidth(), commonAgentFieldsForm.getHeight(),
                commonAgentFieldsForm.getImageUrl(), actionDecisionForm.getActionDecisionDefinitions(),
                agentPropertiesForm.getPropertyDefinitions());
    }
}
