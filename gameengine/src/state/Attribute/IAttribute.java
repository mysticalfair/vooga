package state.Attribute;

/**
 * @Author:Luke_Truitt
 * These are things like Health, Money, Settings, etc.
 */
public interface IAttribute {

    // Returns the specified name of the attribute (ie "Money")
    String getName();

    // Returns the value of the attribute (ie 34)
    int getValue();
}
