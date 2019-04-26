package panes.attributes.agent.define;

import authoring.IActionDecisionDefinition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Accordion;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import panes.attributes.AttributesForm;
import util.AuthoringContext;
import util.AuthoringUtil;

import java.util.List;

public class ActionDecisionForm extends AttributesForm {

    private static final String ADD_BUTTON_IMAGE_FILE = "add-button.png";
    private static final String DELETE_BUTTON_IMAGE_FILE = "trash.png";

    private Accordion accordion;

    public ActionDecisionForm(AuthoringContext context) {
        super(context);

        GridPane gridPane = new GridPane();
        gridPane.add(new Label(context.getString("ActionDecisions")), 0, 0, 3, 1);
        gridPane.add(AuthoringUtil.createSquareImageButton(
                ADD_BUTTON_IMAGE_FILE, 25, 10, e -> addActionDecision()),
                4, 0);
        accordion = new Accordion();
        gridPane.add(accordion, 0, 1, 4, 1);

        getPane().getChildren().add(gridPane);
    }

    public List<IActionDecisionDefinition> getActionDecisionDefinitions() {
        return null;
    }

    private void addActionDecision() {
        TitledPane actionDecisionTitledPane = new TitledPane();

        // Add trash button to label of TitledPane
        // Used https://stackoverflow.com/questions/49085827/javafx-titledpane-with-button
        // and http://javawiki.sowas.com/doku.php?id=javafx:titledpane-with-checkbox
        HBox titleHBox = new HBox();
        Label titleLabel = new Label();
        titleLabel.textProperty().bind(actionDecisionTitledPane.textProperty());
        var deleteButton = AuthoringUtil.createSquareImageButton(
                DELETE_BUTTON_IMAGE_FILE, 25, 10,
                e -> accordion.getPanes().remove(actionDecisionTitledPane));
        titleHBox.getChildren().addAll(titleLabel, deleteButton);
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

        accordion.getPanes().add(actionDecisionTitledPane);
    }

}
