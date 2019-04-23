package state.attribute;

import java.io.Serializable;

/**
 * @author Luke Truitt
 * Entire Attribute, used by Engine and Author
 */
public class Attribute implements IPlayerAttribute, Serializable {
    private int id;
    private String name;
    private int value;

    public Attribute(int id, String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public int getId() { return this.id; }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }
    public void setId(int id) {this.id = id;}
    public void setName(String name) {
        this.name = name;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
