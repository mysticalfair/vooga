package state.agent;

import authoring.IActionDecisionDefinition;
import authoring.IAgentDefinition;
import authoring.IPropertyDefinition;
import state.IRequiresBaseAgent;
import state.Property;
import state.actiondecision.ActionDecision;
import state.condition.Condition;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author David Miron
 * @author Luke_Truitt
 * @author Jamie Palka
 * Agent used by backend and authoring
 */
public class Agent implements IAgentDefinition, IPlayerAgent, Cloneable, Serializable {

    protected List<ActionDecision> actionDecisions;
    private PlayerAgent playerAgent;

    /**
     * Agent constructor.
     * @param id agent ID
     * @param x,y initial location
     */

    public Agent(int id, int x, int y, int width, int height, double direction, String name, String imageURL, List<? extends IActionDecisionDefinition> actionDecisions,
                List<? extends IPropertyDefinition> properties) {
        this.actionDecisions = (List<ActionDecision>)actionDecisions;
        injectBaseAgentWhereNecessary(this.actionDecisions);
        setProperties((List<Property>)properties);
        playerAgent = new PlayerAgent(id, x, y, width, height, name, direction, imageURL);

    }

    private void injectBaseAgentWhereNecessary(List<ActionDecision> actionDecisions) {
        for (ActionDecision ad: actionDecisions) {
            if (IRequiresBaseAgent.class.isAssignableFrom(ad.getAction().getClass()))
                ((IRequiresBaseAgent)ad.getAction()).injectBaseAgent(this);

            for (Condition condition: ad.getConditions()) {
                if (IRequiresBaseAgent.class.isAssignableFrom(condition.getClass()))
                    ((IRequiresBaseAgent)condition).injectBaseAgent(this);
            }
        }
    }

    /**
     * Update the agent's state, by moving and executing action decisions
     * @param agents All other agents in play
     */
    public void update(List<Agent> agents, double delta_time) throws CloneNotSupportedException {

        for (ActionDecision decision: actionDecisions)
            decision.execute(agents, delta_time);

    }

    /**
     * Returns the X location of the Agent.
     * @return int - X location of the agent
     */
    public int getX() {
        return (int) playerAgent.getX();
    }

    @Override
    public void setX(int x) {
        playerAgent.setX(x);
    }

    /**
     * Returns the Y location of the Agent.
     * @return int - Y location of the agent
     */
    public int getY() {
        return (int) playerAgent.getY();
    }

    @Override
    public void setY(int y) {
        playerAgent.setY(y);
    }

    public String getName() {
        return playerAgent.getName();
    }

    public void setWidth(int w) {
        this.playerAgent.setWidth(w);
    }

    public void setHeight(int h) {
        this.playerAgent.setHeight(h);
    }

    public String getImageURL() {
        return playerAgent.getImageURL();
    }

    /**
     * Move the current agent a specified distance
     * @param x The vector representing the movement in x
     * @param y The vector representing the movement in y
     */

    public void move(double x, double y) {
        this.playerAgent.setX(x);
        this.playerAgent.setY(y);
    }

    public void setName(String name ) { this.playerAgent.setName(name);}

    public void setImageURL(String url) { this.playerAgent.setImageURL(url);}

    /**
     * Returns the distance between this Agent and the given Agent
     * @param agent Agent to which the distance will be calculated
     * @return distance
     */
    public double calculateDistance(Agent agent){
        return Math.sqrt(Math.pow(this.playerAgent.getX() - agent.getX(), 2) + Math.pow(this.playerAgent.getY() - agent.getY(), 2));
    }

    /**
     * Clones the agent
     * @return a copy of the this agent
     */
    @Override
    public Agent clone() throws CloneNotSupportedException {
        Agent clonedAgent = (Agent)super.clone();
        // ensure internal structure of agent is cloned as well
        // clone the playerAgent, so they aren't always in the same position, etc.
        clonedAgent.playerAgent = playerAgent.clone();
        // clone conditions and actions within ActionDecisions (without cloning the event handler)
        List<ActionDecision> newActionDecisions = new ArrayList<>();
        for(ActionDecision ad : actionDecisions){
            newActionDecisions.add(ad.clone(clonedAgent));
        }
        actionDecisions = newActionDecisions;
        return clonedAgent;

    }


    /**
     * Updates location of the agent
     * @param x - the new x location to give to the agent
     * @param y - the new y location to give to the agent
     */
    public void setLocation(double x, double y) {
        playerAgent.setX(x);
        playerAgent.setY(y);
    }


    /**
     * Determines if two agents are intersecting.
     * @param agent check if this agent is intersecting with this agent.
     */
    public boolean isColliding(Agent agent) {
        return createBoundingRect(this).intersects(createBoundingRect(agent));
    }

    private Rectangle createBoundingRect(Agent agent) {
        int xTopLeft = (int)(agent.getX() - (agent.getWidth() / 2));
        int yTopLeft = (int)(agent.getY() + (agent.getHeight() / 2));
        return new Rectangle(xTopLeft, yTopLeft, agent.getWidth(), agent.getHeight());
    }

    /**
     * Returns the height in that order
     * @return an array with first element of height
     */
    public int getHeight() {
        return this.playerAgent.getHeight();
    }

    /**
     * Returns the width in that order
     * @return an array with first element of width
     */
    public int getWidth() {
        return this.playerAgent.getWidth();
    }

    /**
     * Returns the direction angle of the agent.
     * @return the angle the agent is pointing to.
     */
    public double getDirection() {
        return this.playerAgent.getDirection();
    }

    public void setDirection(double direction){
        playerAgent.setDirection(direction);
    }

    public List<IActionDecisionDefinition> getActionDecisions() {
        // TODO:
        return null;
    }

    public void removeActionDecision(int i) {
        // TODO:
    }

    public void addActionDecision(IActionDecisionDefinition def) {
        // TODO:
    }

    // TODO:  fill out all property-related methods

    @Override
    public List<? extends IPropertyDefinition> getProperties() {
        return null;
    }

    @Override
    public void removeProperty(String name) {
    }

    @Override
    public void addProperty(IPropertyDefinition property) {

    }

    @Override
    public <T> void setProperty(String name, T value) {
        this.playerAgent.setProperty(name, value);
    }

    public void addActionDecisionRaw(ActionDecision decision) {
        actionDecisions.add(decision);
    }

    public Object getProperty(String name) {
        for(Property property : this.playerAgent.getProperties()) {
            if(property.getName().equals(name)) {
                return property.getValue();
            }
        }
        return null;
    }

    @Deprecated
    public void setProperties(String[] properties, Object[] values) {
        for(int i = 0; i < properties.length; i++) {
            this.playerAgent.setProperty(properties[i], values[i]);
        }
    }

    /**
     * Takes a given list of properties and adds them all to the list of properties held by the playerAgent.
     * @param properties - properties to be transferred to the playerAgent
     */
    public void setProperties(List<Property> properties){
        for(Property p : properties) {
            this.playerAgent.setProperty(p);
        }
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
    }
}
