package state.Attribute;

/**
 * @Author:Luke_Truitt
 * Entire Attribute, used by Engine and Author
 */
public class AttributeComplete extends AttributePlayer implements IAttributeComplete {

    public AttributeComplete(String name, int value) {
        super(name, value);
    }
    public AttributeComplete(AttributePlayer attribute) {
        super(attribute.getName(), attribute.getValue());
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public IAttribute toImmutable() {
        return new AttributePlayer(this);
    }
}
