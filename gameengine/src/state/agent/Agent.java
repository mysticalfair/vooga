package state.agent;

import state.actiondecision.ActionDecision;

import java.awt.*;
import java.util.List;
/**
 * @author David Miron
 * @author Luke_Truitt
 * @author Jamie Palka
 * Agent used by backend and authoring
 */
public class Agent implements IAgent, IAgentDefinition, Cloneable {

    private int id;
    private int myX;
    private int myY;
    private String imageURL;
    private String team;
    private int health;
    private int width;
    private int height;
    private double direction;
    private double xVelocity;
    private double yVelocity;
    protected List<ActionDecision> actionDecisions;

    /**
     * Agent constructor.
     * @param id agent ID
     * @param x,y initial location
     * @param team the agent's respective team
     */
    public Agent(int id, int x, int y, String team, int health, int width, int height, double xVelocity, double yVelocity) {
        this.id = id;
        this.myX = x;
        this.myY = y;
        this.team = team;
        this.health = health;
        this.width = width;
        this.height = height;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        // TODO set imageURL somewhere
    }

    /**
     * Update the agent's state, by moving and executing action decisions
     * @param agents All other agents in play
     */
    @Override
    public void update(List<IAgent> agents) {

        for (ActionDecision decision: actionDecisions)
            decision.execute(agents);

    }

    public String getName() {
        // TODO: This
        return id + "";
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public void setImageURL(String url) {
        this.imageURL = url;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public void setName(String name) {
        // TODO: this
    }
    /**
     * Move the current agent a specified distance
     * @param x The vector representing the movement in x
     * @param y The vector representing the movement in y
     */
    public void move(int x, int y) {
        this.myX = this.myX + x;
        this.myY = this.myY + y;
    }

    /**
     * Returns the location of the Agent.
     * @return Point containing location of the Agent
     */
    public int[] getLocation(){
        return new int[]{myX, myY};
    }

    /**
     * Returns the team of the Agent.
     * @return String containing team of the Agent
     */
    public String getTeam(){
        return team;
    }

    /**
     * Returns the distance between this Agent and the given Agent
     * @param agent Agent to which the distance will be calculated
     * @return distance
     */
    public double calculateDistance(IAgent agent){
        return Math.sqrt(Math.pow(this.myX - agent.getLocation()[0], 2) + Math.pow(this.myY - agent.getLocation()[1], 2));
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
     * Decreases the health value of the agent.
     * @param healthDeduction amount by which to deduct the health of the agent.
     */
    public void loseHealth(int healthDeduction) {
        health -= healthDeduction;
    }

    /**
     * Increases the health value of the agent.
     * @param healthIncrease amount by which to increase the health of the agent.
     */
    public void gainHealth(int healthIncrease) {
        health += healthIncrease;
    }

    /**
     * Updates the x and y velocity vectors of the agent
     * @param xVelocity x velocity the agent will now have
     * @param yVelocity y velocity the agent will now have
     */
    public void updateVeolcity(double xVelocity, double yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }


    /**
     * Determines if two agents are intersecting.
     * @param agent check if this agent is intersecting with this agent.
     */
    public boolean isColliding(IAgent agent) {
        var currRect = new Rectangle(this.myX, this.myY, this.width, this.height);
        var otherRect = new Rectangle(agent.getLocation()[0], agent.getLocation()[1], agent.getWidth(), agent.getHeight());
        return currRect.intersects(otherRect);
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

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
}
