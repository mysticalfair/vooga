package state;

import state.Agent.IAgentComplete;
import state.Attribute.IAttributeComplete;
import state.Objective.IObjectiveComplete;

import java.util.List;

/**
 * @Author:Luke_Truitt
 * This is the version of State used by Authoring and Engine
 */
public interface IStateComplete extends IState {
    // List of all the Agents that are available in the store, can be altered by anyone
    List<IAgentComplete> getMutableOptions();
    // List of all the Agents on screen, can be altered by anyone
    List<IAgentComplete> getMutableAgents();
    // List of all the Attributes, can be altered by anyone
    List<IAttributeComplete> getMutableAttributes();
    // List of all the Objectives, can  be altered by anyone
    List<IObjectiveComplete> getMutableObjectives();
    // Update the list of Available Agents
    void setOptionAgents(List<IAgentComplete> optionAgents);
    // Update the list of Current Agents
    void setCurrentAgents(List<IAgentComplete> currentAgents);
    // Update the list of Options
    void setObjectives(List<IObjectiveComplete> objectives);
    // Update the list of Attributes
    void setAttributes(List<IAttributeComplete> attributes);
    // Turns it into IState for the Player
    IState toImmutable();
}
