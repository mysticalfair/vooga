package Attribute;


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
        this.name = attribute.getName();
        this.value = attribute.getValue();

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
            System.out.println("Changed name: " + e.getNewValue());
        } else if(e.getPropertyName().equals("value")) {
            this.setValue((Integer) e.getNewValue());
            System.out.println("Changed value: "+ e.getNewValue());
        }
    }
}
