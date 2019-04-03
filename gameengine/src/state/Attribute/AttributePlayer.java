package state.Attribute;

/**
 * @Author:Luke_Truitt
 * This is the version of an attribute that faces player
 */
public class AttributePlayer extends Attribute {

    public AttributePlayer(String name, int value) {
        this.name = name;
        this.value = value;
    }
    public AttributePlayer(AttributeComplete attribute) {
        this.name = attribute.getName();
        this.value = attribute.getValue();
    }
    // Immutable version of Name
    public String getName() {
        return this.name + "";
    }

    // Immutable version of Value
    public int getValue() {
        int value = this.value;
        return value;
    }

}
