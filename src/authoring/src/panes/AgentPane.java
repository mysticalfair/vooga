package panes;

import frontend_objects.CloneableAgentView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class AgentPane extends AuthoringPane {

    private VBox inventoryContainer;
    private HBox handlePane;
    private ScrollPane scrollInventory;
    private GridPane inventory;
    private List<CloneableAgentView> agentList;

    public AgentPane() {
        super();
        initElements();
        getContentChildren().add(inventoryContainer);
    }

    private void initElements() {
        initInventoryContainer();
        initHandlePane();
        initScrollPane();
        initInventory();
    }

    private void initInventoryContainer() {
        inventoryContainer = new VBox();
        inventoryContainer.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        inventoryContainer.getStylesheets().add("agent-pane.css");
    }

    private void initHandlePane() {
        handlePane = new HBox();
        Text test = new Text("handle pane here");
        test.setFill(Color.WHITE);
        handlePane.getChildren().add(test);
        inventoryContainer.getChildren().add(handlePane);
    }

    private void initScrollPane() {
        scrollInventory = new ScrollPane();
        scrollInventory.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        //scrollInventory.setMaxHeight(AuthoringEnvironment.DEFAULT_HEIGHT - 25);
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

    @Override
    public void setStylesheet(String url) {

    }
}
