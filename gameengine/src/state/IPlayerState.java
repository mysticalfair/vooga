package state;

import state.agent.IPlayerAgent;
import state.attribute.IPlayerAttribute;
import state.objective.IObjective;

/**
 * @Author:Luke_Truitt
 * This is the version of LevelState used by Player
 */
public interface IPlayerState {
    Iterable<IPlayerAgent> getImmutableOptions();
    Iterable<IPlayerAgent> getImmutableAgents();
    Iterable<IObjective> getImmutableObjectives();
    Iterable<IPlayerAttribute> getImmutableAttributes();
}
