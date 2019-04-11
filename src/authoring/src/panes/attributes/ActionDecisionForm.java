package panes.attributes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import panes.AccessibleContainer;

import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ActionDecisionForm implements AccessibleContainer {

    private ResourceBundle rb;
    private GridPane gridPane;
    private ChoiceBox<String> type;
    private SimpleStringProperty titleProperty;

    public ActionDecisionForm(ResourceBundle rb) {
        this.rb = rb;
        gridPane = new GridPane();
        gridPane.getStylesheets().add("attributes-pane.css");

        // Type
        titleProperty = new SimpleStringProperty();
        var typeLabel = new Label(rb.getString("ActionDecision"));
        //Text typeLabel = new Text(rb.getString("ActionDecision"));
        //typeLabel.setId("textTest");
        type = new ChoiceBox<>();
        type.getItems().addAll("Poop", "Defecate", "Utilize one's anus", "Dispense of fecal matter in a pleasurable way");
        type.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                titleProperty.set(String.format(rb.getString("ActionDecisionFormTitle"),
                        typeLabel.getText(), type.getItems().get((Integer) number2)));
            }
        });
        type.getSelectionModel().selectFirst();
        gridPane.add(typeLabel, 0, 0);
        gridPane.add(type, 1, 0, 3, 1);
    }

    @Override
    public void accessContainer(Consumer<Pane> accessMethod) {
        accessMethod.accept(gridPane);
    }

    public SimpleStringProperty getTitleProperty() {
        return titleProperty;
    }
}
