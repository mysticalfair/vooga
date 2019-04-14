package state;

import state.agent.Agent;
import state.agent.IPlayerAgent;
import state.attribute.IPlayerAttribute;
import state.attribute.IAttribute;
import state.objective.IPlayerObjective;
import state.objective.Objective;

import java.util.ArrayList;
import java.util.List;

/**
 * Version of state that is passed to player
 * @author David Miron
 * @Author:Luke_Truitt
 */
public class LevelState {

    private List<Agent> agentsOptions;
    private List<Agent> agentsCurrent;
    private List<Objective> objectivesCurrent;
    private List<IAttribute> attributesCurrent;

    public LevelState() {
        this.agentsOptions = new ArrayList<>();
        this.agentsCurrent = new ArrayList<>();
        this.objectivesCurrent = new ArrayList<>();
        this.attributesCurrent = new ArrayList<>();
    }

    /**
     * For Engine
     */
    public List<Agent> getDefinedAgents() {
        return this.agentsOptions;
    }

    public void removeDefinedAgent(int index) {
        if (agentsOptions.size() > index)
            agentsOptions.remove(index);
    }

    public void addDefinedAgent(Agent agent) {
        agentsOptions.add(agent);
    }

    public List<Agent> getCurrentAgents() {
        return this.agentsCurrent;
    }

    public void removeCurrentAgent(int index) {
        if (agentsCurrent.size() > index)
            agentsCurrent.remove(index);
    }

    public void addCurrentAgent(Agent agent) {
        agentsCurrent.add(agent);
    }

    public List<Objective> getObjectives() {
        return this.objectivesCurrent;
    }

    public void setObjectives(List<Objective> objectivesCurrent) {
        this.objectivesCurrent = objectivesCurrent;
    }

    public List<IAttribute> getMutableAttributes() { return this.attributesCurrent; }

    public void setAttributes(List<IAttribute> attributesCurrent) {
        this.attributesCurrent = attributesCurrent;
    }

    public List<Agent> getMutableAgentsExcludingSelf(Agent agent) {
        List<Agent> agentsWithoutSelf = new ArrayList<>(agentsCurrent);
        agentsWithoutSelf.removeIf(a -> a == agent);
        return agentsWithoutSelf;
    }

    /**
     * For Author
     */
    public void defineAgent(Agent agent) {
        this.agentsOptions.add(agent);
    }
    public void placeAgent(Agent agent) {
        this.agentsCurrent.add(agent);
    }
    public void defineObjective(Objective objective) {
        this.objectivesCurrent.add(objective);
    }
    public void defineAttribute(IAttribute attribute) {
        this.attributesCurrent.add(attribute);
    }

    /**
     * For Player - Iterate through and update your copy based on the corresponding ID.
     */
    public Iterable<IPlayerAgent> getImmutableOptions() { return List.copyOf(this.agentsOptions); }

    public Iterable<IPlayerAgent> getImmutableAgents() {
        return List.copyOf(this.agentsCurrent);
    }

    public Iterable<IPlayerObjective> getImmutableObjectives() {
        return List.copyOf(this.objectivesCurrent);
    }

    public Iterable<IPlayerAttribute> getImmutableAttributes() {
        return List.copyOf(this.attributesCurrent);
    }

    public void removeAgent(Agent agent) {
        if(this.agentsCurrent.contains(agent)){
            this.agentsCurrent.remove(agent);
        }
    }

}
