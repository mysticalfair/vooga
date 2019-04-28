package panes.attributes.agent.define;

import authoring.IConditionDefinition;
import panes.attributes.LabeledEditableFormList;
import util.AuthoringContext;

import java.util.ArrayList;
import java.util.List;

public class ConditionsForm extends LabeledEditableFormList {

    /**
     * Creates a new conditions labeled editable form list.
     *
     * @param context the context that makes relevant instances available
     */
    public ConditionsForm(AuthoringContext context) {
        super(context, context.getString("Conditions"));

        setOnAdd(e -> {
            ConditionFormElement element = new ConditionFormElement(context);
            add(element);
            element.setOnDelete(e2 -> remove(element));
        });
    }

    /**
     * Packages the data from this form into a list of IConditionDefinition.
     * @return the List<IConditionDefinition> containing the data from this form.
     */
    @Override
    public List<IConditionDefinition> packageData() {
        List<IConditionDefinition> conditions = new ArrayList<>();
        iterateElements(e -> conditions.add(((ConditionFormElement) e).packageData()));
        return conditions;
    }

}
