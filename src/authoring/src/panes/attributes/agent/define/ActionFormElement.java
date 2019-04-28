package panes.attributes.agent.define;

import authoring.IActionDefinition;
import panes.ConsolePane;
import panes.attributes.LabeledTextField;
import util.AuthoringContext;

import java.util.HashMap;
import java.util.Map;

public class ActionFormElement extends NameFieldsFormElement {

    public ActionFormElement(AuthoringContext context) {
        super(context);

        title.setText(getContext().getString("Action"));
        populateNames(getContext().getGameFactory().getAvailableActions());
    }

    @Override
    public IActionDefinition packageData() {
        Map<String, Object> paramsMap = new HashMap<>();
        for (LabeledTextField p : parameters) {
            paramsMap.put(p.getLabel(), p.getPromptText());
        }
        try {
            return getContext().getGameFactory().createAction(names.getValue(), paramsMap);
        } catch (Exception e) {
            getContext().displayConsoleMessage(getContext().getString("ErrorCreatingAction"), ConsolePane.Level.ERROR);
            return null;
        }
    }
}
