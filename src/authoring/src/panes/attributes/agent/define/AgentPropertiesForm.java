package panes.attributes.agent.define;

import authoring.GameFactory;
import authoring.IPropertyDefinition;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import panes.AuthoringUtil;
import panes.attributes.AttributesForm;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AgentPropertiesForm extends AttributesForm {

    private static final String ADD_BUTTON_IMAGE_FILE = "add-button.png";

    private VBox propertiesVBox;
    private List<AgentPropertyLine> propertyLines;

    public AgentPropertiesForm(ResourceBundle rb, GameFactory gameFactory) {
        super(rb, gameFactory);

        GridPane gridPane = new GridPane();
        gridPane.add(new Label(rb.getString("Properties")), 0, 0, 3, 1);
        gridPane.add(AuthoringUtil.createSquareImageButton(
                ADD_BUTTON_IMAGE_FILE, 25, 10,
                e -> {
                    AgentPropertyLine p = new AgentPropertyLine(rb, gameFactory, null);
                    propertyLines.add(p);
                    p.accessContainer(propertiesVBox.getChildren()::add);
                    p.setOnDelete(e2 -> {
                        propertyLines.remove(p);
                        p.accessContainer(propertiesVBox.getChildren()::remove);
                    });
                }),
                4, 0);
        propertiesVBox = new VBox();
        gridPane.add(propertiesVBox, 0, 1, 4, 1);
        propertyLines = new ArrayList<>();

        pane.getChildren().add(gridPane);
    }

    public List<IPropertyDefinition> getPropertyDefinitions() {
        List<IPropertyDefinition> properties = new ArrayList<>();
        for (AgentPropertyLine p : propertyLines) {
            properties.add(p.makeProperty());
        }
        return properties;
    }

}
