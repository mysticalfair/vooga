package panes.attributes.state;

import authoring.INameFieldsDefinition;
import authoring.IObjectiveConditionDefinition;
import authoring.IObjectiveDefinition;
import authoring.IObjectiveOutcomeDefinition;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import panes.attributes.FormElement;
import util.AuthoringContext;

public class ObjectiveForm extends FormElement {

    private Button saveButton, cancelButton;
    private ObjectiveConditionFormElement condition;
    private ObjectiveOutcomeFormElement outcome;

    public ObjectiveForm(AuthoringContext context) {
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

        condition = new ObjectiveConditionFormElement(getContext());
        condition.accessContainer(vBox.getChildren()::add);

        outcome = new ObjectiveOutcomeFormElement(getContext());
        outcome.accessContainer(vBox.getChildren()::add);

        getContentChildren().add(vBox);
    }

    public void loadFromExisting(IObjectiveDefinition objective) {
        condition.loadFromExisting(objective.getCondition());
        outcome.loadFromExisting(objective.getOutcome());
    }

    /**
     * Packages the data from this form into an array of objective condition and outcome.
     * @return the array containing the data from this form.
     */
    @Override
    public INameFieldsDefinition[] packageData() {
        IObjectiveConditionDefinition conditionDef = condition.packageData();
        IObjectiveOutcomeDefinition outcomeDef = outcome.packageData();
        if (conditionDef == null || outcomeDef == null) {
            return null;
        }
        return new INameFieldsDefinition[]{conditionDef, outcomeDef};
    }

}
