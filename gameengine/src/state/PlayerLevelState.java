package state;

import state.agent.IPlayerAgent;
import state.attribute.IPlayerAttribute;
import state.objective.IPlayerObjective;

import java.beans.PropertyChangeListener;

public class PlayerLevelState implements IPlayerLevelState {

    @Override
    public Iterable<IPlayerAgent> getImmutableOptions() {
        return null;
    }

    @Override
    public Iterable<IPlayerAgent> getImmutableAgents() {
        return null;
    }

    @Override
    public Iterable<IPlayerObjective> getImmutableObjectives() {
        return null;
    }

    @Override
    public Iterable<IPlayerAttribute> getImmutableAttributes() {
        return null;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
