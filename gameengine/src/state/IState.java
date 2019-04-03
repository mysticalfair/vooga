package state;

import state.Agent.IAgent;
import state.Attribute.IAttribute;
import state.Objective.IObjective;

import java.util.List;

/**
 * @author David Miron
 * @author Jamie Palka
 * @Author:Luke_Truitt
 */
public interface IState {
    List<IAgent> getImmutableOptions();
    List<IAgent> getImmutableAgents();
    List<IObjective> getImmutableObjectives();
    List<IAttribute> getImmutableAttributes();
}
