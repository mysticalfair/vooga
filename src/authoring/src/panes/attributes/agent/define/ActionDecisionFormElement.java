package panes.attributes.agent.define;

import authoring.IActionDecisionDefinition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import panes.attributes.FormElement;
import util.AuthoringContext;

public class ActionDecisionFormElement extends FormElement {

    public ActionDecisionFormElement(AuthoringContext context) {
        super(context);
        init();
    }

    @Override
    public IActionDecisionDefinition packageData() {
        return null;
    }

    private void init() {
        TitledPane actionDecisionTitledPane = new TitledPane();

        // Add trash button to label of TitledPane
        // Used https://stackoverflow.com/questions/49085827/javafx-titledpane-with-button
        // and http://javawiki.sowas.com/doku.php?id=javafx:titledpane-with-checkbox
        HBox titleHBox = new HBox();
        Label titleLabel = new Label();
        titleLabel.textProperty().bind(actionDecisionTitledPane.textProperty());
        titleHBox.getChildren().add(titleLabel);
        accessDeleteButton(titleHBox.getChildren()::add);
        actionDecisionTitledPane.setGraphic(titleHBox);

        VBox actionDecisionVBox = new VBox();
        actionDecisionTitledPane.setContent(actionDecisionVBox);

        // Action
        HBox actionHBox = new HBox();
        Label actionLabel = new Label(getContext().getString("Action"));
        ChoiceBox<String> actionChoiceBox = new ChoiceBox<>();
        //actionChoiceBox.getItems().addAll("MoveForward", "AttackWithInterval", "MoveTowards", "FollowPath");//"Poop", "Defecate", "Utilize one's anus", "Dispense of fecal matter in a pleasurable way");
        actionChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                actionDecisionTitledPane.setText(String.format(getContext().getString("ActionDecisionFormTitle"),
                        actionLabel.getText(), actionChoiceBox.getItems().get((Integer) number2)));
            }
        });
        actionChoiceBox.getSelectionModel().selectFirst();
        actionHBox.getChildren().addAll(actionLabel, actionChoiceBox);
        actionDecisionVBox.getChildren().add(actionHBox);

        getContentChildren().add(actionDecisionTitledPane);
    }

}
