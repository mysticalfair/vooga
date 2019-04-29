package panes.attributes.state;

import authoring.IObjectiveConditionDefinition;
import authoring.IObjectiveDefinition;
import authoring.IObjectiveOutcomeDefinition;
import panes.attributes.TitledSaveableFormElement;
import util.AuthoringContext;

public class ObjectiveForm extends TitledSaveableFormElement {

    private ObjectiveConditionFormElement condition;
    private ObjectiveOutcomeFormElement outcome;

    public ObjectiveForm(AuthoringContext context) {
        super(context);

        condition = new ObjectiveConditionFormElement(getContext());
        condition.accessContainer(container -> accessVBox(vBox -> vBox.getChildren().add(container)));

        outcome = new ObjectiveOutcomeFormElement(getContext());
        outcome.accessContainer(container -> accessVBox(vBox -> vBox.getChildren().add(container)));
    }

    public void loadFromExisting(IObjectiveDefinition objective) {
        condition.loadFromExisting(objective.getCondition());
        outcome.loadFromExisting(objective.getOutcome());
    }

    /**
     * Packages the data from this form into an IObjectiveDefinition
     * @return the IObjectiveDefinition containing the data from this form.
     */
    @Override
    public IObjectiveDefinition packageData() {
        IObjectiveConditionDefinition conditionDef = condition.packageData();
        IObjectiveOutcomeDefinition outcomeDef = outcome.packageData();
        if (conditionDef == null || outcomeDef == null) {
            return null;
        }
        return getContext().getGameFactory().createObjective(conditionDef, outcomeDef);
    }

}
