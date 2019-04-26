package state;

import state.agent.IPlayerAgent;
import state.attribute.IPlayerAttribute;
import state.objective.IObjective;

import java.beans.PropertyChangeListener;
import java.util.List;

public class PlayerLevelState implements IPlayerLevelState {

    @Override
    public List<IPlayerAgent> getImmutableOptions() {
        return null;
    }

    @Override
    public List<IPlayerAgent> getImmutableAgents() {
        return null;
    }

    @Override
    public List<IObjective> getImmutableObjectives() {
        return null;
    }

    @Override
    public List<IPlayerAttribute> getImmutableAttributes() {
        return null;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
