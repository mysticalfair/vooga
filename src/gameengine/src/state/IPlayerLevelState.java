package state;

import state.agent.IPlayerAgent;
import state.attribute.IPlayerAttribute;
import state.objective.IPlayerObjective;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * @Author:Luke_Truitt
 * This is the version of LevelState used by Player
 */
public interface IPlayerLevelState {
    List<IPlayerAgent> getImmutableOptions();
    List<IPlayerAgent> getImmutableAgents();
    List<IPlayerObjective> getImmutableObjectives();
    List<IPlayerAttribute> getImmutableAttributes();
    boolean addAgentFromStore(int index, double x, double y);

    void addPropertyChangeListener(PropertyChangeListener listener);
}
