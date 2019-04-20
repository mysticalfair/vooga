package engine;

import state.IPlayerLevelState;

import java.beans.PropertyChangeListener;

public interface IPlayerLevel {
    IPlayerLevelState getLevelState();
    void addPropertyChangeListener(PropertyChangeListener listener);
}
