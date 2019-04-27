package state;

import state.agent.Agent;
import state.agent.IPlayerAgent;
import state.attribute.IPlayerAttribute;
import state.attribute.IAttribute;
import state.objective.IPlayerObjective;
import state.objective.Objective;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Version of state that is passed to player
 * @author David Miron
 * @Author:Luke_Truitt
 */
public class LevelState implements Serializable, IPlayerLevelState {

    private List<Agent> placeableAgents;
    private List<Agent> agentsCurrent;
    private List<Objective> objectivesCurrent;
    private List<IAttribute> attributesCurrent;

    private PropertyChangeSupport pcs;
    public LevelState() {
        this.placeableAgents = new ArrayList<>();
        this.agentsCurrent = new ArrayList<>();
        this.objectivesCurrent = new ArrayList<>();
        this.attributesCurrent = new ArrayList<>();
        this.pcs =  new PropertyChangeSupport(this);
    }

    /**
     * For Engine
     */
    public List<Agent> getPlaceableAgents() {
        return this.placeableAgents;
    }

    public void removePlaceableAgent(int index) {
        if (placeableAgents.size() > index)
            placeableAgents.remove(index);
    }

    public void addPlaceableAgent(Agent agent) {
        placeableAgents.add(agent);
    }

    public List<Agent> getCurrentAgents() {
        return this.agentsCurrent;
    }

    public void removeCurrentAgent(int index) {
        if (agentsCurrent.size() > index)
            agentsCurrent.remove(index);
    }

    public void addCurrentAgent(Agent agent) {
        var oldAgents = this.agentsCurrent;
        agentsCurrent.add(agent);
        this.pcs.firePropertyChange("Add Agent", oldAgents, agent);
        System.out.println("adding agent in backend, length: " + this.agentsCurrent.size());
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
        System.out.println("MY LENGTH WITHOUT ME IS " + agentsWithoutSelf.size());
        return agentsWithoutSelf;
    }

    /*
     * For Author
     */
    public void placeAgent(Agent agent) {
        var agentsOld = this.agentsCurrent;
        this.agentsCurrent.add(agent);
    }
    public void defineObjective(Objective objective) {
        this.objectivesCurrent.add(objective);
    }
    public void defineAttribute(IAttribute attribute) {
        this.attributesCurrent.add(attribute);
    }

    /*
     * For Player - Iterate through and update your copy based on the corresponding ID.
     */
    public List<IPlayerAgent> getImmutableOptions() { return List.copyOf(this.placeableAgents); }

    public List<IPlayerAgent> getImmutableAgents() {
        return List.copyOf(this.agentsCurrent);
    }

    public List<IPlayerObjective> getImmutableObjectives() {
        return List.copyOf(this.objectivesCurrent);
    }

    public List<IPlayerAttribute> getImmutableAttributes() {
        return List.copyOf(this.attributesCurrent);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removeAgent(Agent agent) {
        if (this.agentsCurrent.contains(agent)){
            this.agentsCurrent.remove(agent);
        }
    }

}
