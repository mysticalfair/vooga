package state;

import state.agent.IAgent;
import state.attribute.IAttribute;
import state.objective.IObjective;

import java.util.List;

/**
 * @author David Miron
 * @author Jamie Palka
 * @Author:Luke_Truitt
 */
public interface IState extends IPlayerState {
    // List of all the Agents that are available in the store, can be altered by anyone
    List<IAgent> getMutableOptions();
    // List of all the Agents on screen, can be altered by anyone
    List<IAgent> getMutableAgents();
    // List of all the Attributes, can be altered by anyone
    List<IAttribute> getMutableAttributes();
    // List of all the Objectives, can  be altered by anyone
    List<IObjective> getMutableObjectives();
    // Update the list of Available Agents
    void setOptionAgents(List<IAgent> optionAgents);
    // Update the list of Current Agents
    void setCurrentAgents(List<IAgent> currentAgents);
    // Update the list of Options
    void setObjectives(List<IObjective> objectives);
    // Update the list of Attributes
    void setAttributes(List<IAttribute> attributes);

    void removeAgent(IAgent agent);
    List<IAgent> getMutableAgentsExcludingSelf(IAgent agent);

    IAgent[] getAgents();
}
