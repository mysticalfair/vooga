package state.attribute;

/**
 * @Author:Luke_Truitt
 * Components of an attribute that only the back-end and author need
 */
public interface IAttribute extends IPlayerAttribute {

    void setId(int id);
    // Set the name of the Attribute
    void setName(String name);

    // Set it's value
    void setValue(int value);
}
