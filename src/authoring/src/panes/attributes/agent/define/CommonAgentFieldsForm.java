package panes.attributes.agent.define;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import panes.attributes.AttributesForm;
import util.AuthoringContext;
import util.AuthoringUtil;

public class CommonAgentFieldsForm extends AttributesForm {

    static final String DEFAULT_IMAGE_FILENAME = "default_image.jpg";
    static final double IMAGE_FIELD_SIZE = 100;

    private ChoiceBox<String> agentTypeField;
    private TextField nameField, widthField, heightField;
    private ImageView imageField;
    private String imageUrl;

    public CommonAgentFieldsForm(AuthoringContext context) {
        super(context);
        initCommonFields();
    }

    public String getName() {
        return nameField.getText();
    }

    public int getWidth() {
        return getIntFromTextField(widthField, "WidthMustBeInt");
    }

    public int getHeight() {
        return getIntFromTextField(heightField, "HeightMustBeInt");
    }

    public String getImageUrl() {
        return imageUrl;
    }

    private int getIntFromTextField(TextField textField, String errorMessageKey) {
        try {
            return Integer.parseInt(textField.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, getContext().getString(errorMessageKey));
            alert.showAndWait();
        }
        return -1;
    }

    /**
     * Initialize fields common to all agents.
     * @return a node that contains those common fields
     */
    private void initCommonFields() {
        GridPane gridPane = new GridPane();

        // Agent type
        Label typeLabel = new Label(getContext().getString("Type"));
        agentTypeField = new ChoiceBox<>();
        agentTypeField.getItems().addAll(getContext().getString("Custom"));
        gridPane.add(typeLabel, 0, 0);
        gridPane.add(agentTypeField, 1, 0, 3, 1);


        // Name
        Label nameLabel = new Label(getContext().getString("Name"));
        nameLabel.setId("paddedLabel");
        nameField = new TextField();
        nameField.setPromptText(getContext().getString("NamePrompt"));
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
        Text chooseImageText = new Text(getContext().getString("ChooseImage"));
        AnchorPane.setBottomAnchor(chooseImageText, 0.0);
        imageLabelAnchorPane.getChildren().add(chooseImageText);

        imageStackPane.getChildren().addAll(imageField, imageLabelAnchorPane);
        imageStackPane.setOnMouseClicked(e -> chooseAgentImage());
        gridPane.add(imageStackPane, 0, 2, 2, 2);


        // Width and height
        Label widthLabel = new Label(getContext().getString("Width"));
        widthLabel.setId("paddedLabel");
        widthField = new TextField();
        widthField.setPromptText(getContext().getString("WidthHeightPrompt"));
        Label heightLabel = new Label(getContext().getString("Height"));
        heightLabel.setId("paddedLabel");
        heightField = new TextField();
        heightField.setPromptText(getContext().getString("WidthHeightPrompt"));
        gridPane.add(widthLabel, 2, 2);
        gridPane.add(widthField, 3, 2);
        gridPane.add(heightLabel, 2, 3);
        gridPane.add(heightField, 3, 3);

        getPane().getChildren().add(gridPane);
    }

    private void chooseAgentImage() {
        AuthoringUtil.openFileChooser(
                getContext().getString("ImageFile"), AuthoringUtil.IMAGE_EXTENSIONS, false, null,
                file -> {
                    imageUrl = file.toURI().toString();
                    imageField.setImage(new Image(imageUrl));
                },
                () -> System.err.println("Failed to load image for agent.")
        );
    }
}
