package state.attribute;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * @Author:Luke_Truitt
 * Entire Attribute, used by Engine and Author
 */
public class Attribute implements IAttribute, Serializable {
    private int id;
    private String name;
    private int value;
    private PropertyChangeSupport pcs;

    public Attribute(int id, String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
        pcs = new PropertyChangeSupport(this);
    }

    public int getId() { return this.id; }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void setId(int id) {
        var oldId = this.id;
        this.id = id;
        pcs.firePropertyChange("id", oldId, id);
    }

    public void setName(String name) {
        var oldName = this.name;
        this.name = name;
        pcs.firePropertyChange("name", oldName, name);
    }

    public void setValue(int value) {
        var oldValue = this.value;
        this.value = value;
        pcs.firePropertyChange("value", oldValue, value);
    }
}
