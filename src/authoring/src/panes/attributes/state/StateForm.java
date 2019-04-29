package panes.attributes.state;

import authoring.IObjectiveDefinition;
import panes.attributes.TitledSaveableFormElement;
import panes.attributes.state.objective.ObjectivesForm;
import util.AuthoringContext;

import java.util.List;

public class StateForm extends TitledSaveableFormElement {

    private ObjectivesForm objectivesForm;

    public StateForm(AuthoringContext context) {
        super(context);
        init();
    }

    private void init() {
        objectivesForm = new ObjectivesForm(getContext());
        objectivesForm.accessContainer(container -> accessVBox(vBox -> vBox.getChildren().add(container)));
    }

    public void loadFromExisting(List<? extends IObjectiveDefinition> objectives) {
        objectivesForm.loadFromExisting(objectives);
    }

    /**
     * Packages the data from this form into a ______
     * @return the ____ containing the data from this form.
     */
    @Override
    public List<IObjectiveDefinition> packageData() {
        return objectivesForm.packageData();
    }

}
