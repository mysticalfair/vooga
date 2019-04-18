package panes.attributes;

import authoring.GameFactory;
import javafx.scene.layout.Pane;
import panes.AccessibleContainer;

import java.util.ResourceBundle;
import java.util.function.Consumer;

public abstract class AttributesForm implements AccessibleContainer {

    protected ResourceBundle rb;
    protected GameFactory gameFactory;
    protected Pane pane;

    public AttributesForm(ResourceBundle rb, GameFactory gameFactory) {
        this.rb = rb;
        this.gameFactory = gameFactory;
        pane = new Pane();
        pane.getStylesheets().add("attributes-pane.css");
    }

    @Override
    public void accessContainer(Consumer<Pane> accessMethod) {
        accessMethod.accept(pane);
    }
}
