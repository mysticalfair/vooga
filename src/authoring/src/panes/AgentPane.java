package panes;

import frontend_objects.CloneableAgentView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class AgentPane extends AuthoringPane {

    private VBox vbox;
    private ScrollPane scrollInventory;
    private GridPane inventory;
    private List<CloneableAgentView> agentList;

    public AgentPane() {
        super();
        vbox = new VBox();
        vbox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        agentList = new ArrayList<>();
        initScrollPane();
        initInventory();
        vbox.getStylesheets().add("agent-pane.css");
        getContentChildren().add(vbox);
    }

    private void initScrollPane() {
        scrollInventory = new ScrollPane();
        scrollInventory.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollInventory.setMaxHeight(AuthoringEnvironment.DEFAULT_HEIGHT);
        scrollInventory.getStyleClass().add("scroll-pane");
        vbox.getChildren().add(scrollInventory);
    }

    private void initInventory() {
        inventory = new GridPane();
        inventory.setVgap(4);
        inventory.setHgap(4);
        int rowIterator = 0;
        int colIterator = 0;
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

    public VBox getVBoxContainer() {
        return vbox;
    }

    @Override
    public void setStylesheet(String url) {

    }
}
