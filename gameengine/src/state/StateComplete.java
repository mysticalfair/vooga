package state;

import state.Agent.IAgentComplete;
import state.Attribute.IAttributeComplete;
import state.Objective.IObjectiveComplete;

import java.util.List;

/**
 * @Author:Luke_Truitt
 * State class used by Authoring and Engine
 */
public class StateComplete extends StatePlayer implements IStateComplete {

    private List<IAgentComplete> agentsOptions;
    private List<IAgentComplete> agentsCurrent;
    private List<IObjectiveComplete> objectivesCurrent;
    private List<IAttributeComplete> attributesCurrent;

    public StateComplete(List<IAgentComplete> agentsOptions, List<IAgentComplete> agentsCurrent,
                         List<IObjectiveComplete> objectivesCurrent, List<IAttributeComplete> attributesCurrent) {
        super(agentsOptions, agentsCurrent, objectivesCurrent, attributesCurrent);
        this.agentsOptions = agentsOptions;
        this.agentsCurrent = agentsCurrent;
        this.objectivesCurrent = objectivesCurrent;
        this.attributesCurrent = attributesCurrent;
    }

    public List<IAgentComplete> getMutableOptions() {
        return this.agentsOptions;
    }

    public void setOptionAgents(List<IAgentComplete> agentOptions) {
        this.agentsOptions = agentOptions;
    }

    public List<IAgentComplete> getMutableAgents() {
        return this.agentsCurrent;
    }

    public void setCurrentAgents(List<IAgentComplete> agentCurrent) {
        this.agentsCurrent = agentCurrent;
    }

    public List<IObjectiveComplete> getMutableObjectives() {
        return this.objectivesCurrent;
    }

    public void setObjectives(List<IObjectiveComplete> objectivesCurrent) {
        this.objectivesCurrent = objectivesCurrent;
    }

    public List<IAttributeComplete> getMutableAttributes() { return this.attributesCurrent; }

    public void setAttributes(List<IAttributeComplete> attributesCurrent) {
        this.attributesCurrent = attributesCurrent;
    }

    public IState toImmutable() {
        return new StatePlayer(this);
    }
}
