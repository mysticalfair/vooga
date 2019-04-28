package panes.attributes;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import util.AuthoringContext;

public abstract class LabeledEditableFormList extends FormList {

    private HBox header;

    /**
     * Creates a new editable form list. It defaults to undeletable by not adding the delete button to a visible node.
     * It only differs from FormList in that it puts a header with the name and an add button.
     *
     * @param context the context that makes relevant instances available
     * @param title the title for the list
     * //@param formElementClass the class of the form element of which this list
     */
    public LabeledEditableFormList(AuthoringContext context, String title) {//, Class formElementClass) throws ClassCastException {
        super(context);

        /*if (!formElementClass.isAssignableFrom(FormElement.class)) {
            throw new ClassCastException("Incorrect class type passed into LabeledEditableFormlist. Requires class of type FormElement.");
        }*/

        header = new HBox();
        header.getChildren().add(new Label(title));
        accessAddButton(header.getChildren()::add);
        accessVBox(vBox -> vBox.getChildren().add(0, header));
    }

    /**
     * Set the title of this list.
     * @param title the title as a string
     */
    public void setTitle(String title) {
        ((Label) header.getChildren().get(0)).setText(title);
    }

    /**
     * Get the title of this list.
     * @return the title as a string
     */
    public String getTitle() {
        return ((Label) header.getChildren().get(0)).getText();
    }
}
