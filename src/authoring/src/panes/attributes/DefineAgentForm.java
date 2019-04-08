package panes.attributes;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import panes.AuthoringUtil;

import java.util.ResourceBundle;
import java.util.function.Consumer;


public class DefineAgentForm {

    static final String DEFAULT_IMAGE_FILENAME = "default_image.jpg";
    static final double IMAGE_FIELD_SIZE = 100;
    private static final String[] IMAGE_EXTENSIONS = {"*.jpg", "*.gif", "*.jpeg", "*.bmp"};

    private ResourceBundle rb;
    private VBox vBox;
    private ChoiceBox<String> agentTypeField;
    private TextField nameField, widthField, heightField;
    private ImageView imageField;

    public DefineAgentForm() {
        rb = ResourceBundle.getBundle("Authoring");
        vBox = new VBox();

        vBox.getChildren().add(initCommonFields());
    }

    public void accessVBox(Consumer<Pane> accessMethod) {
        accessMethod.accept(vBox);
    }

    /**
     * Initialize fields common to all agents.
     * @return a node that contains those common fields
     */
    private GridPane initCommonFields() {
        GridPane gridPane = new GridPane();


        // Agent type
        Text typeLabel = new Text(rb.getString("Type"));
        agentTypeField = new ChoiceBox<>();
        agentTypeField.getItems().addAll("Davido", "Jamie", "Jorge", "Luke");
        gridPane.add(typeLabel, 0, 0);
        gridPane.add(agentTypeField, 1, 0, 3, 1);


        // Name
        Text nameLabel = new Text(rb.getString("Name"));
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
        Text widthLabel = new Text(rb.getString("Width"));
        widthField = new TextField();
        widthField.setPromptText(rb.getString("WidthHeightPrompt"));
        Text heightLabel = new Text(rb.getString("Height"));
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
