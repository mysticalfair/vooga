package state;

import state.agent.IPlayerAgent;
import state.attribute.IPlayerAttribute;
import state.objective.IPlayerObjective;

import java.beans.PropertyChangeListener;

/**
 * @Author:Luke_Truitt
 * This is the version of LevelState used by Player
 */
public interface IPlayerLevelState {
    Iterable<IPlayerAgent> getImmutableOptions();
    Iterable<IPlayerAgent> getImmutableAgents();
    Iterable<IPlayerObjective> getImmutableObjectives();
    Iterable<IPlayerAttribute> getImmutableAttributes();

    void addPropertyChangeListener(PropertyChangeListener listener);
}
