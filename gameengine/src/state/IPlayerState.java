package state;

import state.Agent.IPlayerAgent;
import state.Attribute.IPlayerAttribute;
import state.Objective.IPlayerObjective;

import java.util.List;

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
