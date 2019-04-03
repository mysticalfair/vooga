package state.Attribute;

import state.Attribute.IAttribute;

/**
 * @Author:Luke_Truitt
 * Components of an attribute that only the back-end and author need
 */
public interface IAttributeComplete extends IAttribute {

    // Set the name of the Attribute
    void setName(String name);

    // Set it's value
    void setValue(int value);

    // Convert to Player version
    IAttribute toImmutable();
}
