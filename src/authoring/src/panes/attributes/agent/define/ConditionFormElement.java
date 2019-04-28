package panes.attributes.agent.define;

import authoring.IConditionDefinition;
import panes.ConsolePane;
import panes.attributes.LabeledTextField;
import util.AuthoringContext;

import java.util.HashMap;
import java.util.Map;

public class ConditionFormElement extends NameFieldsFormElement {

    public ConditionFormElement(AuthoringContext context) {
        super(context);

        title.setText(getContext().getString("Condition"));
        populateNames(getContext().getGameFactory().getAvailableActions());
        accessDeleteButton(headerHBox.getChildren()::add);
    }

    @Override
    public IConditionDefinition packageData() {
        Map<String, Object> paramsMap = new HashMap<>();
        for (LabeledTextField p : parameters) {
            paramsMap.put(p.getLabel(), p.getPromptText());
        }
        try {
            return getContext().getGameFactory().createCondition(names.getValue(), paramsMap);
        } catch (Exception e) {
            getContext().displayConsoleMessage(getContext().getString("ErrorCreatingCondition"), ConsolePane.Level.ERROR);
            return null;
        }
    }

}