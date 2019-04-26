package panes.attributes;

import javafx.scene.layout.Pane;
import panes.AccessibleContainer;
import util.AuthoringContext;

import java.util.function.Consumer;

public abstract class AttributesForm implements AccessibleContainer {

    private AuthoringContext context;
    private Pane pane;

    public AttributesForm(AuthoringContext context) {
        this.context = context;
        pane = new Pane();
        pane.getStylesheets().add(getContext().getString("AttributesStyle"));
    }

    @Override
    public void accessContainer(Consumer<Pane> accessMethod) {
        accessMethod.accept(pane);
    }

    protected AuthoringContext getContext() {
        return context;
    }

    protected Pane getPane() {
        return pane;
    }
}
