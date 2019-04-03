package state;

import state.Agent.IAgent;
import state.Agent.IAgentComplete;
import state.Attribute.IAttribute;
import state.Attribute.IAttributeComplete;
import state.Objective.IObjective;

import java.util.ArrayList;
import java.util.List;
 /**
 * @Author:Luke_Truitt
 * Overall, shared components of the different state types
 */
public abstract class State implements IState {
  protected List<IAgent> immutableAgentsOptions;
  protected List<IAgent> immutableAgentsCurrent;
  protected List<IObjective> immutableObjectivesCurrent;
  protected List<IAttribute> immutableAttributesCurrent;
}
