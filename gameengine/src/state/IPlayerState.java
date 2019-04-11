package state;

import state.agent.IPlayerAgent;
import state.attribute.IPlayerAttribute;
import state.objective.IPlayerObjective;

/**
 * @Author:Luke_Truitt
 * This is the version of State used by Player
 */
public interface IPlayerState {
    Iterable<IPlayerAgent> getImmutableOptions();
    Iterable<IPlayerAgent> getImmutableAgents();
    Iterable<IPlayerObjective> getImmutableObjectives();
    Iterable<IPlayerAttribute> getImmutableAttributes();
}
