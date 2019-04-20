package State;

import engine.IPlayerLevel;
import state.IPlayerLevelState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class StateView implements PropertyChangeListener {
    private IPlayerLevel level;

    public StateView(IPlayerLevel level){
        super();
        this.init(level);
        level.addPropertyChangeListener(this);
    }

    public void init(IPlayerLevel level){
        this.level = level;
    }

    public void propertyChange(PropertyChangeEvent e) {
        // TODO: Get levelstate from new level and reassign and reattach listeners in levelStateView
    }
}
