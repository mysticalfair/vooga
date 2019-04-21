package Attribute;

import javafx.scene.image.Image;
import state.agent.IPlayerAgent;
import state.attribute.IPlayerAttribute;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AttributeView implements PropertyChangeListener {
    private String name;
    private int value;

    public AttributeView(IPlayerAttribute attribute){
        super();
        this.init(attribute);
        attribute.addPropertyChangeListener(this);
    }

    public void init(IPlayerAttribute attribute){

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
            System.out.println("Changed X: " + e.getNewValue());
        } else if(e.getPropertyName().equals("value")) {
            this.setValue((Integer) e.getNewValue());
            System.out.println("Changed Y: "+ e.getNewValue());
        }
    }
}
