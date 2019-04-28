package panes;

import frontend_objects.CloneableAgentView;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import util.AuthoringContext;
import util.AuthoringUtil;

import java.util.ArrayList;
import java.util.List;

public class AgentPane extends AuthoringPane {

    private VBox inventoryContainer; // overall VBox
    private HBox buttonPane; // button panes at top of VBox
    private ScrollPane scrollInventory; // scrollpane that contains inventory
    private FlowPane inventory; // the inventory itself inside the scrollpane
    private ImageView trash; // trash for deleting agents from map

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
        inventoryContainer.setPrefSize(getContext().getDouble("AgentPaneWidth"), getContext().getDouble("MiddleRowHeight") - getContext().getDouble("MiddleRowPadding"));
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
        scrollInventory.setPrefViewportWidth(getContext().getDouble("AgentPaneWidth"));
        scrollInventory.setPrefViewportHeight(getContext().getDouble("MiddleRowHeight") - getContext().getDouble("MiddleRowPadding"));
        scrollInventory.getStyleClass().add(getContext().getString("ScrollPaneStyle"));
        inventoryContainer.getChildren().add(scrollInventory);
    }

    private void initInventory() {
        inventory = new FlowPane();
        inventory.setVgap(8);
        inventory.setHgap(4);
        inventory.setPrefWrapLength(getContext().getDouble("AgentPaneWidth")); // preferred width = 300
        scrollInventory.setContent(inventory);


        /*inventory = new GridPane();
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
        }*/
    }

    public void refreshAgentList(int level, MapPane map) {
        inventory.getChildren().clear();
        getContext().getState().getDefinedAgents().forEach(agent -> {
            CloneableAgentView newAgent = new CloneableAgentView(getContext(), agent.getName(), agent.getImageURL());
            newAgent.setOnMousePressed(e -> newAgent.mousePressedOnClone(e, map));
            inventory.getChildren().add(newAgent);
            // TODO: add delete and edit buttons, checkbox to enable, and only set to enabled if in current level
        });
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
