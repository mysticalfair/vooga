package panes;

import authoring.IAgentDefinition;
import frontend_objects.AgentView;
import frontend_objects.DraggableAgentView;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.AuthoringContext;
import util.AuthoringUtil;

/**
 * @author Samuel Rabinowitz
 */
public class AgentPaneElement extends AuthoringPane {

    private IAgentDefinition agent;

    private VBox overallVBox;
    private ImageView image;
    private Label name;

    private HBox controlsHBox;
    private Button editButton;
    private Button deleteButton;
    private CheckBox checkBox;

    public AgentPaneElement(AuthoringContext authoringContext, IAgentDefinition agent) {
        super(authoringContext);
        this.agent = agent;
        init();
    }

    private void init() {
        overallVBox = new VBox();
        getContentChildren().add(overallVBox);

        image = new ImageView(agent.getImageURL());
        image.setFitWidth(getContext().getDouble("AgentSize"));
        image.setFitHeight(image.getFitWidth());
        image.setId(getContext().getString("ButtonStyle"));
        overallVBox.getChildren().add(image);

        name = new Label(agent.getName());
        overallVBox.getChildren().add(name);

        // Controls
        controlsHBox = new HBox();
        overallVBox.getChildren().add(controlsHBox);

        editButton = AuthoringUtil.createSquareImageButton(
                getContext().getString("EditPenImageFile"),
                getContext().getDouble("AgentEditButtonSize"),
                null);
        controlsHBox.getChildren().add(editButton);

        deleteButton = AuthoringUtil.createSquareImageButton(
                getContext().getString("TrashImageFile"),
                getContext().getDouble("AgentEditButtonSize"),
                null);
        controlsHBox.getChildren().add(deleteButton);

        checkBox = new CheckBox();
        checkBox.setAllowIndeterminate(false);
        checkBox.setPrefWidth(18);
        checkBox.setPrefHeight(18);
        controlsHBox.getChildren().add(checkBox);
    }

    public void mousePressedOnClone(MouseEvent e, MapPane map) {
        if (e.getClickCount() == getContext().getInt("CloneClickCount")) {
            DraggableAgentView copy = new DraggableAgentView(getContext(), agent.getImageURL());
            map.addAgent(map.getLevel(), copy);
            getContext().displayConsoleMessage(getContext().getString("AgentAdded") + map.getAgentCount(), ConsolePane.Level.NEUTRAL);
            copy.setMouseActionsForDrag(map);
        } else {
            // code to open up attributes pane.
        }
    }

}
