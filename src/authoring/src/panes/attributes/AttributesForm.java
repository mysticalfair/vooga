package panes.attributes;

import authoring.GameFactory;
import javafx.scene.layout.Pane;
import panes.AccessibleContainer;
import util.AuthoringContext;

import java.util.ResourceBundle;
import java.util.function.Consumer;

public abstract class AttributesForm implements AccessibleContainer {

    protected AuthoringContext context;
    protected Pane pane;

    public AttributesForm(AuthoringContext context) {
        this.context = context;
        pane = new Pane();
        pane.getStylesheets().add("attributes-pane.css");
    }

    @Override
    public void accessContainer(Consumer<Pane> accessMethod) {
        accessMethod.accept(pane);
    }
}
