package panes.attributes.state;

import javafx.scene.control.Button;
import panes.attributes.FormElement;
import util.AuthoringContext;

public class ObjectiveForm extends FormElement {

    private Button saveButton, cancelButton;

    public ObjectiveForm(AuthoringContext context) {
        super(context);
        init();
    }

    private void init() {

    }

    /**
     * Packages the data from this form into _______.
     * @return the _______ containing the data from this form.
     */
    @Override
    public Object packageData() {
        return null;
    }

}
