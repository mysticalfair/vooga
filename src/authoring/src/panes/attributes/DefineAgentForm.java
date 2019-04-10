package panes.attributes;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import panes.AccessibleContainer;
import panes.AuthoringUtil;

import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;


public class DefineAgentForm implements AccessibleContainer {

    static final String DEFAULT_IMAGE_FILENAME = "default_image.jpg";
    static final double IMAGE_FIELD_SIZE = 100;
    private static final String[] IMAGE_EXTENSIONS = {"*.jpg", "*.gif", "*.jpeg", "*.bmp"};

    private ResourceBundle rb;
    private VBox vBox;
    private ChoiceBox<String> agentTypeField;
    private TextField nameField, widthField, heightField;
    private ImageView imageField;
    private Accordion accordion;

    public DefineAgentForm() {
        rb = ResourceBundle.getBundle("Authoring");
        vBox = new VBox();

        vBox.getChildren().add(initCommonFields());

        accordion = new Accordion();
        TitledPane t = new TitledPane();
        ActionDecisionForm adf = new ActionDecisionForm(rb);
        adf.accessContainer(t::setContent);
        t.textProperty().bind(adf.getTitleProperty());
        accordion.getPanes().addAll(t);
        vBox.getChildren().add(accordion);
    }

    @Override
    public void accessContainer(Consumer<Pane> accessMethod) {
        accessMethod.accept(vBox);
    }

    /**
     * Initialize fields common to all agents.
     * @return a node that contains those common fields
     */
    private GridPane initCommonFields() {
        GridPane gridPane = new GridPane();


        // Agent type
        Label typeLabel = new Label(rb.getString("Type"));
        agentTypeField = new ChoiceBox<>();
        agentTypeField.getItems().addAll("Davido", "Jamie", "Jorge", "Luke");
        gridPane.add(typeLabel, 0, 0);
        gridPane.add(agentTypeField, 1, 0, 3, 1);


        // Name
        Label nameLabel = new Label(rb.getString("Name"));
        nameLabel.setId("paddedLabel");
        nameField = new TextField();
        nameField.setPromptText(rb.getString("NamePrompt"));
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameField, 1, 1, 3, 1);


        // Image
        StackPane imageStackPane = new StackPane();
        imageField = new ImageView(DEFAULT_IMAGE_FILENAME);
        imageField.setFitWidth(IMAGE_FIELD_SIZE);
        imageField.setFitHeight(IMAGE_FIELD_SIZE);
        imageField.setPreserveRatio(true);
        imageField.setSmooth(true);
        imageField.setCache(true);

        AnchorPane imageLabelAnchorPane = new AnchorPane();
        Text chooseImageText = new Text(rb.getString("ChooseImage"));
        AnchorPane.setBottomAnchor(chooseImageText, 0.0);
        imageLabelAnchorPane.getChildren().add(chooseImageText);

        imageStackPane.getChildren().addAll(imageField, imageLabelAnchorPane);
        imageStackPane.setOnMouseClicked(e -> chooseAgentImage());
        gridPane.add(imageStackPane, 0, 2, 2, 2);


        // Width and height
        Label widthLabel = new Label(rb.getString("Width"));
        widthLabel.setId("paddedLabel");
        widthField = new TextField();
        widthField.setPromptText(rb.getString("WidthHeightPrompt"));
        Label heightLabel = new Label(rb.getString("Height"));
        heightLabel.setId("paddedLabel");
        heightField = new TextField();
        heightField.setPromptText(rb.getString("WidthHeightPrompt"));
        gridPane.add(widthLabel, 2, 2);
        gridPane.add(widthField, 3, 2);
        gridPane.add(heightLabel, 2, 3);
        gridPane.add(heightField, 3, 3);


        return gridPane;
    }

    private void chooseAgentImage() {
        AuthoringUtil.openFileChooser(
                rb.getString("ImageFile"), IMAGE_EXTENSIONS, false, null,
                file -> imageField.setImage(new Image(file.toURI().toString())),
                () -> System.err.println("Failed to load image for agent.")
        );
    }
}
