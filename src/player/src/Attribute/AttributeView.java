package Attribute;
/**
 * Attribute front-end class
 * @author Joanna Li
 */

import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.text.Text;
import state.attribute.IPlayerAttribute;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AttributeView extends Text implements PropertyChangeListener {
    private String name;
    private int value;
    //private LongProperty property = new SimpleLongProperty(0);

    public AttributeView(IPlayerAttribute attribute){
        super();
        this.init(attribute);
        attribute.addPropertyChangeListener(this);
        this.textProperty().bind(Bindings.createStringBinding(() -> (name+ ": " + this.value)));
    }

    public void init(IPlayerAttribute attribute){
        this.name = attribute.getName();
        this.value = attribute.getValue();
        attribute.addPropertyChangeListener(this);

    }

    private void setName(String name) {
        this.name = name;
    }

    private void setValue(int value) {
        this.value = value;
    }

    public void propertyChange(PropertyChangeEvent e) {
        if(e.getPropertyName().equals("name")) {
            this.setName((String) e.getNewValue());
        } else if(e.getPropertyName().equals("value")) {
            this.setValue((Integer) e.getNewValue());
        }
    }



}
