package panes.attributes.agent.define;

import authoring.AvailableNameFields;
import authoring.Field;
import authoring.INameFieldsDefinition;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import panes.attributes.FormElement;
import panes.attributes.LabeledTextField;
import util.AuthoringContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class NameFieldsFormElement extends FormElement {

    protected HBox headerHBox;
    protected Label title;
    protected ChoiceBox<String> names;
    protected VBox parametersVBox;
    protected List<LabeledTextField> parameters;
    // TODO: Make this whole shebang work with more than only parameters that are strings

    protected List<? extends AvailableNameFields> nameFields;

    /**
     * Creates a new name field form element has a title, a dropdown, and a list of parameters.
     * Is front-end version of the game engine's AvailableNameFields class.
     *
     * @param context the context that makes relevant instances available
     */
    public NameFieldsFormElement(AuthoringContext context) {
        super(context);

        VBox overallVBox = new VBox();

        // Header
        headerHBox = new HBox();
        title = new Label();
        names = new ChoiceBox<>();
        headerHBox.getChildren().addAll(title, names);
        overallVBox.getChildren().add(headerHBox);

        // Parameters
        overallVBox.getChildren().add(new Label(getContext().getString("Parameters")));
        parametersVBox = new VBox();
        parameters = new ArrayList<>();
        overallVBox.getChildren().add(parametersVBox);

        getContentChildren().add(overallVBox);
    }

    protected void populateNames(List<? extends AvailableNameFields> nameFields) {
        this.nameFields = nameFields;
        this.nameFields.forEach((nameField) -> names.getItems().add(nameField.getName()));
        names.getSelectionModel().selectedItemProperty().addListener((observable, oldString, newString) -> {
            updateParameters();
        });
        names.getSelectionModel().selectFirst();
    }

    private void updateParameters() {
        parametersVBox.getChildren().clear();
        parameters.clear();

        String selectedName = names.getValue();
        AvailableNameFields selectedNameField = nameFields.stream().filter(p -> p.getName().equals(selectedName)).collect(Collectors.toList()).get(0);

        for (Field f : selectedNameField.getFields()) {
            LabeledTextField field = new LabeledTextField(getContext(), f.getName());
            field.setPromptText(f.getType());
            parameters.add(field);
            field.accessContainer(parametersVBox.getChildren()::add);
        }
    }

    public void addSelectedNameListener(ChangeListener<? super String> changeListener) {
        names.getSelectionModel().selectedItemProperty().addListener(changeListener);
        int index = names.getSelectionModel().getSelectedIndex();
        names.getSelectionModel().selectLast();
        names.getSelectionModel().selectFirst();
        names.getSelectionModel().select(index);
    }

    protected Map<String, Object> makeParamsMap() {
        Map<String, Object> paramsMap = new HashMap<>();
        for (LabeledTextField p : parameters) {
            switch (p.getPromptText()) {
                case "int":
                    paramsMap.put(p.getLabel(), Integer.parseInt(p.packageData()));
                    break;
                case "double":
                    paramsMap.put(p.getLabel(), Double.parseDouble(p.packageData()));
                    break;
                case "string":
                    paramsMap.put(p.getLabel(), p.packageData());
            }
        }
        return paramsMap;
    }

    public void loadFromExisting(INameFieldsDefinition definition) {
        names.getSelectionModel().select(definition.getName());
        definition.getParams().forEach((name, object) -> {
            parameters.forEach(labeledTextField -> {
                if (labeledTextField.getLabel().equals(name)) {
                    labeledTextField.loadFromExisting(object.toString());
                }
            });
        });
    }
}
