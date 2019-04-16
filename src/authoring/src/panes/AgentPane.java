package panes;

import frontend_objects.CloneableAgentView;
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
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class AgentPane extends AuthoringPane {

    private VBox inventoryContainer;
    private HBox buttonPane;
    private ScrollPane scrollInventory;
    private GridPane inventory;
    private List<CloneableAgentView> agentList;
    private ImageView trash;

    public static final double WIDTH = AuthoringEnvironment.AGENT_WIDTH;
    public static final double HEIGHT = AuthoringEnvironment.MIDDLE_ROW_HEIGHT;

    public AgentPane() {
        super();
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
        inventoryContainer.setPrefSize(WIDTH, HEIGHT);
        inventoryContainer.getStylesheets().add("agent-pane.css");
    }

    private void initButtonPane() {
        buttonPane = new HBox();
        trash = new ImageView(new Image("trash.png"));
        trash.setPreserveRatio(true);
        trash.setFitWidth(35);
        trash.setFitHeight(35);
        buttonPane.getChildren().add(trash);
        inventoryContainer.getChildren().add(buttonPane);
    }

    private void initScrollPane() {
        scrollInventory = new ScrollPane();
        scrollInventory.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollInventory.setPrefViewportWidth(WIDTH);
        scrollInventory.setPrefViewportHeight(HEIGHT);
        scrollInventory.getStyleClass().add("scroll-pane");
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
            CloneableAgentView newAgent = new CloneableAgentView("monkey.png");
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

    public ScrollPane getScrollInventory() {
        return scrollInventory;
    }

    public VBox getVBoxContainer() {
        return inventoryContainer;
    }

    public ImageView getTrash() {
        return trash;
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
