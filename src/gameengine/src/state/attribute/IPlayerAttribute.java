package state.attribute;

import java.beans.PropertyChangeListener;

/**
 * @Author:Luke_Truitt
 * These are things like Health, Money, Settings, etc.
 */
public interface IPlayerAttribute {

    // Get the Attribute Id
    int getId();
    // Returns the specified name of the attribute (ie "Money")
    String getName();
    // Returns the value of the attribute (ie 34)
    int getValue();
    void addPropertyChangeListener(PropertyChangeListener listener);
}
