package panes;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.function.Consumer;

public class DefineAgentBox {

    private VBox vBox;

    public DefineAgentBox() {
        vBox = new VBox();
    }

    public void accessVBox(Consumer<Pane> accessMethod) {
        accessMethod.accept(vBox);
    }
}
