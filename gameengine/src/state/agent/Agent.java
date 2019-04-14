package state.agent;

import gameengine.IActionDecisionDefinition;
import gameengine.IAgentDefinition;
import state.IRequiresGameEventMaster;
import state.actiondecision.ActionDecision;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author David Miron
 * @author Luke_Truitt
 * @author Jamie Palka
 * Agent used by backend and authoring
 */
public class Agent implements IAgentDefinition, IPlayerAgent, Cloneable {

    private int id;
    private int width;
    private int height;
    private double direction;
    protected List<ActionDecision> actionDecisions;
    private PlayerAgent playerAgent;
    /**
     * Agent constructor.
     * @param id agent ID
     * @param x,y initial location
     */
    public Agent(int id, int x, int y, String name, int width, int height, double direction) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.direction = direction;

        this.actionDecisions = new ArrayList<>();
        // TODO set imageURL somewhere
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
    public double getX() {
        return playerAgent.getX();
    }

    /**
     * Returns the Y location of the Agent.
     * @return int - Y location of the agent
     */
    public double getY() {
        return playerAgent.getY();
    }

    public String getName() {
        return playerAgent.getName();
    }

    /**
     * Returns the team of the Agent.
     * @return String containing team of the Agent
     */
    public String getTeam() {
        return playerAgent.getTeam();
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public void setHeight(int h) {
        this.height = h;
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
     * Returns the height and width in that order
     * @return an array with first element of height and second element of width
     */
    public int[] getEdges() {
        return new int[]{this.height, this.width};
    }

    /**
     * Clones the agent
     * @return a copy of the this agent
     */
    @Override
    public Agent clone() throws CloneNotSupportedException {
        return (Agent)super.clone();
        // TODO is this hacky or ok
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
        return this.height;
    }

    /**
     * Returns the width in that order
     * @return an array with first element of width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Returns the direction angle of the agent.
     * @return the angle the agent is pointing to.
     */
    public double getDirection() {
        return direction;
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

    public void addActionDecisionRaw(ActionDecision decision) {
        actionDecisions.add(decision);
    }
}
