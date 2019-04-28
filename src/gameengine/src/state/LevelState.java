package state;

import state.agent.Agent;
import state.agent.IPlayerAgent;
import state.attribute.IPlayerAttribute;
import state.attribute.IAttribute;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Version of state that is passed to player
 * @author David Miron
 * @author Luke Truitt
 * @author Jamie Palka
 */
public class LevelState implements Serializable, IPlayerLevelState {

    private List<Agent> agentsOptions;
    private List<Agent> agentsCurrent;
    private List<IAttribute> attributesCurrent;

    private PropertyChangeSupport pcs;

    public LevelState() {
        this.agentsOptions = new ArrayList<>();
        this.agentsCurrent = new ArrayList<>();
        this.attributesCurrent = new ArrayList<>();
        this.pcs = new PropertyChangeSupport(this);
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
        var oldAgents = this.agentsCurrent;
        agentsCurrent.add(agent);
        this.pcs.firePropertyChange("AddAgent", oldAgents, agentsCurrent);
    }

    public List<IAttribute> getMutableAttributes() {
        return this.attributesCurrent;
    }

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
        var agentsOld = this.agentsCurrent;
        this.agentsCurrent.add(agent);
        this.pcs.firePropertyChange("CurrentAgent", agentsOld, this.agentsCurrent);
    }

    public void defineAttribute(IAttribute attribute) {
        this.attributesCurrent.add(attribute);
    }

    /**
     * For Player - Iterate through and update your copy based on the corresponding ID.
     */
    public List<IPlayerAgent> getImmutableOptions() {
        return List.copyOf(this.agentsOptions);
    }

    public List<IPlayerAgent> getImmutableAgents() {
        return List.copyOf(this.agentsCurrent);
    }

    public List<IPlayerAttribute> getImmutableAttributes() {
        return List.copyOf(this.attributesCurrent);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removeAgent(Agent agent) {
        if (this.agentsCurrent.contains(agent)) {
            this.agentsCurrent.remove(agent);
        }
    }
}

