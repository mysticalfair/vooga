package panes;

import frontend_objects.AgentView;
import frontend_objects.CloneableAgentView;
import frontend_objects.DraggableAgentView;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class AgentPane extends AuthoringPane {

    private VBox vbox;
    private ScrollPane scrollInventory;
    private GridPane inventory;
    private List<DraggableAgentView> agentList;

    public AgentPane() {
        super();
        vbox = new VBox();
        vbox.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        agentList = new ArrayList<>();
        initScrollPane();
        initInventory();
        getContentChildren().add(vbox);
    }

    private void initScrollPane() {
        scrollInventory = new ScrollPane();
        scrollInventory.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollInventory.setMaxHeight(AuthoringEnvironment.DEFAULT_HEIGHT);
        vbox.getChildren().add(scrollInventory);
        //etContentChildren().add(scrollInventory);
    }

    private void initInventory() {
        inventory = new GridPane();
        inventory.setVgap(4);
        inventory.setHgap(4);
        int rowIterator = 0;
        int colIterator = 0;
        for (int i = 0; i < 20; i++) {
            DraggableAgentView testAgent = new DraggableAgentView("Tower.jpg", colIterator, rowIterator, inventory);
            inventory.add(testAgent, colIterator, rowIterator);
            agentList.add(testAgent);
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

    public List<DraggableAgentView> getAgentList() {
        return agentList;
    }

    public GridPane getInventory() {
        return inventory;
    }

    @Override
    public void setStylesheet(String url) {

    }

//    @Override
//    public void addButton(String label, EventHandler action) {
//        var button = new Button(label);
//        button.setOnAction(action);
//        vbox.getChildren().add(button);
//    }

}
