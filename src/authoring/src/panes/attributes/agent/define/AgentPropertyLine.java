package panes.attributes.agent.define;

import authoring.IPropertyDefinition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import panes.attributes.AttributesForm;
import util.AuthoringContext;
import util.AuthoringUtil;

import java.util.List;

public class AgentPropertyLine extends AttributesForm {

    private static final String DELETE_BUTTON_IMAGE_FILE = "trash.png";
    private static final List<String> DEFAULT_TYPES = List.of("Integer", "Double", "String");

    private TextField nameField;
    private ChoiceBox<String> typeBox;
    private TextField valueField;
    private Button deleteButton;

    public AgentPropertyLine(AuthoringContext context, EventHandler onDelete) {
        this(context, "", DEFAULT_TYPES, "", onDelete);
    }

    public AgentPropertyLine(AuthoringContext context, String name, List<String> types, String value, EventHandler onDelete) {
        super(context);
        init(name, types, value, onDelete);
    }

    public void setOnDelete(EventHandler onDelete) {
        deleteButton.setOnAction(onDelete);
    }

    public IPropertyDefinition makeProperty() {
        String name = nameField.getText();
        String type = typeBox.getSelectionModel().getSelectedItem();
        if (type == null) {
            return null;
        }
        if (type.equals("Integer")) {
            try {
                int value = Integer.parseInt(valueField.getText());
                return getContext().getGameFactory().createProperty(name, value);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, String.format(getContext().getString("PropertyMustBeOfType"), name, type));
                alert.showAndWait();
                return null;
            }
        }
        else if (type.equals("Double")) {
            try {
                double value = Double.parseDouble(valueField.getText());
                return getContext().getGameFactory().createProperty(name, value);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, String.format(getContext().getString("PropertyMustBeOfType"), name, type));
                alert.showAndWait();
                return null;
            }
        }
        else if (type.equals("String")) {
            return getContext().getGameFactory().createProperty(name, valueField.getText());
        }
        return null;
    }

    private void init(String name, List<String> types, String value, EventHandler onDelete) {
        HBox propertyHBox = new HBox();

        nameField = new TextField(name);
        nameField.setPromptText(getContext().getString("Name"));
        propertyHBox.getChildren().add(nameField);

        typeBox = new ChoiceBox<>();
        typeBox.getItems().addAll(types);
        propertyHBox.getChildren().add(typeBox);

        valueField = new TextField(value);
        valueField.setPromptText(getContext().getString("Value"));
        propertyHBox.getChildren().add(valueField);

        deleteButton = AuthoringUtil.createSquareImageButton(
                DELETE_BUTTON_IMAGE_FILE, 25, 10, onDelete);
        propertyHBox.getChildren().add(deleteButton);

        getPane().getChildren().add(propertyHBox);
    }
}
