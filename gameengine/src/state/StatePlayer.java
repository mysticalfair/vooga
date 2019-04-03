package state;

import state.Agent.IAgent;
import state.Agent.IAgentComplete;
import state.Attribute.IAttribute;
import state.Attribute.IAttributeComplete;
import state.Objective.IObjective;
import state.Objective.IObjectiveComplete;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Luke_Truitt
 * Version of state that is passed to player
 */
public class StatePlayer extends State {


    public StatePlayer(StateComplete state) {
        initialize(state.getMutableOptions(), state.getMutableAgents(), state.getMutableObjectives(), state.getMutableAttributes());
    }

    public StatePlayer(List<IAgentComplete> agentsOptions, List<IAgentComplete> agentsCurrent,
                       List<IObjectiveComplete> objectivesCurrent, List<IAttributeComplete> attributesCurrent) {
        initialize(agentsOptions, agentsCurrent, objectivesCurrent, attributesCurrent);
    }

    private void initialize(List<IAgentComplete> agentsOptions, List<IAgentComplete> agentsCurrent,
                            List<IObjectiveComplete> objectivesCurrent, List<IAttributeComplete> attributesCurrent) {
        this.immutableAgentsOptions = toImmutableOptions(agentsOptions);
        this.immutableAgentsCurrent = toImmutableAgents(agentsCurrent);
        this.immutableObjectivesCurrent = toImmutableObjectives(objectivesCurrent);
        this.immutableAttributesCurrent = toImmutableAttributes(attributesCurrent);
    }

    /**
     * There has to be a better way of doing these four methods, I was thinking of making some abstract
     * class that all three types extended that had the .toImmutable() then we could do this in one method.
     * I tried but didn't get it.
     */
    public List<IAgent> toImmutableOptions(List<IAgentComplete> agentsOptions) {
        List<IAgent> immutableAgentsOptions = new ArrayList<>();
        for ( IAgentComplete agent : agentsOptions ) {
            immutableAgentsOptions.add(agent.toImmutable());
        }
        return immutableAgentsOptions;
    }

    public List<IAgent> toImmutableAgents(List<IAgentComplete> agentsCurrent) {
        List<IAgent> immutableAgentsCurrent = new ArrayList<>();
        for ( IAgentComplete agent : agentsCurrent ) {
            immutableAgentsCurrent.add(agent.toImmutable());
        }
        return immutableAgentsCurrent;
    }

    public List<IObjective> toImmutableObjectives(List<IObjectiveComplete> objectivesCurrent) {
        List<IObjective> immutableObjectives = new ArrayList<>();
        for ( IObjectiveComplete objective : objectivesCurrent ) {
            immutableObjectives.add(objective.toImmutable());
        }
        return immutableObjectives;
    }

    public List<IAttribute> toImmutableAttributes(List<IAttributeComplete> attributesCurrent) {
        List<IAttribute> immutableAttributes = new ArrayList<>();
        for ( IAttributeComplete attribute : attributesCurrent ) {
            immutableAttributes.add(attribute.toImmutable());
        }
        return immutableAttributes;
    }
    /**
     * End of duplicated code section
     */

    public List<IAgent> getImmutableOptions() {
        return List.copyOf(this.immutableAgentsOptions);
    }

    public List<IAgent> getImmutableAgents() {
        return List.copyOf(this.immutableAgentsCurrent);
    }

    public List<IObjective> getImmutableObjectives() {
        return List.copyOf(this.immutableObjectivesCurrent);
    }

    public List<IAttribute> getImmutableAttributes() {
        return List.copyOf(this.immutableAttributesCurrent);
    }


}
