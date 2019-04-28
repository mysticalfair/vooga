package state;

import state.agent.IPlayerAgent;
import state.attribute.IPlayerAttribute;
import state.objective.IPlayerObjective;

import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * @Author:Luke_Truitt
 * @author David Miron
 * This is the version of LevelState used by Player
 */
public interface IPlayerLevelState {
    List<IPlayerAgent> getImmutableOptions();
    List<IPlayerAgent> getImmutableAgents();
    List<IPlayerObjective> getImmutableObjectives();
    List<IPlayerAttribute> getImmutableAttributes();

    void addPropertyChangeListener(PropertyChangeListener listener);

    String getBackgroundImageURL();
    void setBackgroundImageURL(String imageURL);
}
