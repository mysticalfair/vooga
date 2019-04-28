package state;

import state.agent.Agent;
import state.agent.IPlayerAgent;
import state.attribute.Attribute;
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

    private String backgroundImageURL;
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

    public boolean addAgentFromStore(int index, double x, double y) {
        try {
            if(index < 0 || this.placeableAgents.size() - 1 > index ) {
                return false;
            }

            Agent agent = this.placeableAgents.get(index).clone();

            agent.setX(x);
            agent.setY(y);

            addPlaceableAgent(agent);

            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void addPlaceableAgent(Agent agent) {
        placeableAgents.add(agent);
    }

    public List<Agent> getCurrentAgents() {
        return this.agentsCurrent;
    }

    public void addCurrentAgent(Agent agent) {
        var oldAgents = this.agentsCurrent;
        agentsCurrent.add(agent);
        this.pcs.firePropertyChange("Add Agent", oldAgents, agent);
    }

    public void removeAgent(Agent agent) {
        if (this.agentsCurrent.contains(agent)){
            agentsCurrent.remove(agent);
            //TODO: change back to agent
            this.pcs.firePropertyChange("Remove Agent", agent, null);
        }
    }

    public void addAttribute(Attribute attribute) {
        attributesCurrent.add(attribute);
        this.pcs.firePropertyChange("Add Attribute", null, attribute);
    }

    public void removeAttribute(Attribute attribute) {
        if (this.attributesCurrent.contains(attribute)){
            attributesCurrent.remove(attribute);
            //TODO: change back to agent
            this.pcs.firePropertyChange("Remove Attribute", attribute, null);
        }
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

    @Override
    public String getBackgroundImageURL() {
        return backgroundImageURL;
    }

    @Override
    public void setBackgroundImageURL(String imageURL) {
        backgroundImageURL = imageURL;
    }

}
