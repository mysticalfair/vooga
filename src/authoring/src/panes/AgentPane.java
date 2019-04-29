package panes;

import authoring.IAgentDefinition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import util.AuthoringContext;
import util.AuthoringUtil;

import java.util.function.BiConsumer;

public class AgentPane extends AuthoringPane {

    private VBox inventoryContainer; // overall VBox
    private HBox buttonPane; // button panes at top of VBox
    private ScrollPane scrollInventory; // scrollpane that contains inventory
    private FlowPane inventory; // the inventory itself inside the scrollpane
    private ImageView trash; // trash for deleting agents from map
    private TabPane tabPane = new TabPane();

    private BiConsumer<MouseEvent, IAgentDefinition> imageAction;
    private BiConsumer<ActionEvent, IAgentDefinition> editAction, copyAction, deleteAction;
    private BiConsumer<Boolean, IAgentDefinition> checkListener;

    public AgentPane(AuthoringContext context) {
        super(context);
        initElements();
        openTab("TEST");
        getContentChildren().add(tabPane);

    }

    private void openTab(String tabName){
        var tab = new Tab(tabName);
        tab.setContent(inventoryContainer);
        tabPane.getTabs().add(tab);
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
        tabPane.setPrefSize(getContext().getDouble("AgentPaneWidth"), getContext().getDouble("MiddleRowHeight") - getContext().getDouble("MiddleRowPadding"));
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
        //scrollInventory.setPrefViewportWidth(getContext().getDouble("AgentPaneWidth"));
        scrollInventory.setPrefViewportHeight(getContext().getDouble("MiddleRowHeight") - getContext().getDouble("MiddleRowPadding"));
        scrollInventory.getStyleClass().add(getContext().getString("ScrollPaneStyle"));
        inventoryContainer.getChildren().add(scrollInventory);
    }

    private void initInventory() {
        inventory = new FlowPane();
        inventory.setPrefWrapLength(getContext().getDouble("AgentSize") * 2);
        scrollInventory.setContent(inventory);
    }

    public void setOnImageClicked(BiConsumer<MouseEvent, IAgentDefinition> imageAction) {
        this.imageAction = imageAction;
    }

    public void setOnEdit(BiConsumer<ActionEvent, IAgentDefinition> editAction) {
        this.editAction = editAction;
    }

    public void setOnCopy(BiConsumer<ActionEvent, IAgentDefinition> copyAction) {
        this.copyAction = copyAction;
    }

    public void setOnDelete(BiConsumer<ActionEvent, IAgentDefinition> deleteAction) {
        this.deleteAction = deleteAction;
    }

    public void setOnCheckChanged(BiConsumer<Boolean, IAgentDefinition> checkListener) {
        this.checkListener = checkListener;
    }

    public void refreshAgentList(int level) {
        inventory.getChildren().clear();
        getContext().getState().getDefinedAgents().forEach(agent -> {
            AgentPaneElement newAgent = new AgentPaneElement(getContext(), agent);
            newAgent.accessContainer(inventory.getChildren()::add);
            newAgent.setOnImageClicked(imageAction);
            newAgent.setOnEdit(editAction);
            newAgent.setOnCopy(copyAction);
            newAgent.setOnDelete(deleteAction);
            newAgent.setOnCheckChanged(checkListener);

            // TODO: only set to enabled if in current level
        });
    }

    public void addButton(String buttonImageName, double buttonSize, EventHandler action){
        Button button = AuthoringUtil.createSquareImageButton(buttonImageName, buttonSize, action);
        buttonPane.getChildren().addAll(button);
    }

    @Override
    public void updateSize(double width, double height) {
        inventoryContainer.setPrefSize(width, height);
        //scrollInventory.setPrefViewportWidth(width);
        //scrollInventory.setPrefViewportHeight(height);
    }
}
