package panes;

import frontend_objects.CloneableAgentView;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.AuthoringContext;
import util.AuthoringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AgentPane extends AuthoringPane {

    private VBox inventoryContainer;
    private HBox buttonPane;
    private ScrollPane scrollInventory;
    private GridPane inventory;
    private List<CloneableAgentView> agentList;
    private ImageView trash;

    public AgentPane(AuthoringContext context) {
        super(context);
        initElements();
        getContentChildren().add(inventoryContainer);
    }

    private void initElements() {
        initInventoryContainer();
        initButtonPane();
        initScrollPane();
        initInventory();
    }

    private void initInventoryContainer() {
        inventoryContainer = new VBox();
        inventoryContainer.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        inventoryContainer.setPrefSize(getContext().getDouble("AgentWidth"), getContext().getDouble("MiddleRowHeight") - getContext().getDouble("MiddleRowPadding"));
        inventoryContainer.getStylesheets().add(getContext().getString("AgentPaneStyle"));
    }

    private void initButtonPane() {
        buttonPane = new HBox();
        trash = new ImageView(new Image(getContext().getString("TrashImageFile")));
        trash.setPreserveRatio(true);
        trash.setFitWidth(35);
        trash.setFitHeight(35);
        buttonPane.getChildren().add(trash);
        inventoryContainer.getChildren().add(buttonPane);
    }

    private void initScrollPane() {
        scrollInventory = new ScrollPane();
        scrollInventory.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollInventory.setPrefViewportWidth(getContext().getDouble("AgentWidth"));
        scrollInventory.setPrefViewportHeight(getContext().getDouble("MiddleRowHeight") - getContext().getDouble("MiddleRowPadding"));
        scrollInventory.getStyleClass().add(getContext().getString("ScrollPaneStyle"));
        inventoryContainer.getChildren().add(scrollInventory);
    }

    private void initInventory() {
        inventory = new GridPane();
        inventory.setVgap(4);
        inventory.setHgap(4);
        int rowIterator = 0;
        int colIterator = 0;
        agentList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            CloneableAgentView newAgent = new CloneableAgentView(getContext(), getContext().getString("MonkeyImageFile"));
            inventory.add(newAgent, colIterator, rowIterator);
            agentList.add(newAgent);
            colIterator++;
            if (colIterator > 1) {
                colIterator = 0;
            }
            if ((i + 1) % 2 == 0) {
                rowIterator++;
            }
        }
        scrollInventory.setContent(inventory);
    }

    public List<CloneableAgentView> getAgentList() {
        return agentList;
    }

    public VBox getVBoxContainer() {
        return inventoryContainer;
    }

    public void addButton(String buttonImageName, double buttonSize, double buttonImageSize, EventHandler action){
        Button button = AuthoringUtil.createSquareImageButton(buttonImageName, buttonSize, buttonImageSize, action);
        buttonPane.getChildren().addAll(button);
    }


    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void updateSize(double width, double height) {
        inventoryContainer.setPrefSize(width, height);
        scrollInventory.setPrefViewportWidth(width);
        scrollInventory.setPrefViewportHeight(height);
    }
}
