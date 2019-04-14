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
public class Agent implements IAgent, IAgentDefinition, Cloneable {

    private int id;
    private int width;
    private int height;
    private int attackDamage;
    private double direction;
    private double xVelocity;
    private double yVelocity;
    protected List<ActionDecision> actionDecisions;
    private PlayerAgent playerAgent;
    /**
     * Agent constructor.
     * @param id agent ID
     * @param x,y initial location
     * @param team the agent's respective team
     */
    public Agent(int id, int x, int y, String name, String team, int health, int width, int height, double speed, double direction, int attackDamage) {
        this.id = id;
        this.attackDamage = attackDamage;
        this.width = width;
        this.height = height;
        this.xVelocity = speed * Math.sqrt(2);
        this.yVelocity = speed * Math.sqrt(2);
        this.direction = direction;

        this.actionDecisions = new ArrayList<>();
        // TODO set imageURL somewhere
    }

    /**
     * Update the agent's state, by moving and executing action decisions
     * @param agents All other agents in play
     */
    @Override
    public void update(List<Agent> agents, double delta_time) throws CloneNotSupportedException {

        for (ActionDecision decision: actionDecisions)
            decision.execute(agents, delta_time);

    }

    public double getX() {
        return playerAgent.getX();
    }

    public double getY() {
        return playerAgent.getY();
    }

    public String getName() {
        return playerAgent.getName();
    }
    public String getTeam() {
        return playerAgent.getTeam();
    }
    public void stop() {
        this.xVelocity = 0;
        this.yVelocity = 0;
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
    public double calculateDistance(IAgent agent){
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
     * Decreases the health value of the agent.
     * @param healthDeduction amount by which to deduct the health of the agent.
     */
    public void loseHealth(int healthDeduction) {
        playerAgent.setHealth(playerAgent.getHealth() - healthDeduction);
    }

    /**
     * Increases the health value of the agent.
     * @param healthIncrease amount by which to increase the health of the agent.
     */
    public void gainHealth(int healthIncrease) {
        playerAgent.setHealth(playerAgent.getHealth() + healthIncrease);
    }
    /**
     * Updates the x and y velocity vectors of the agent
     * @param xVelocity x velocity the agent will now have
     * @param yVelocity y velocity the agent will now have
     */
    public void updateVelocity(double xVelocity, double yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
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
     * Returns the current x velocity of the agent
     * @return xVelocity current x velocity the agent
     */
    public double getXVelocity() {
        return xVelocity;
    }

    /**
     * Returns the current y velocity of the agent
     * @return yVelocity current y velocity the agent
     */
    public double getYVelocity() {
        return yVelocity;
    }


    /**
     * Determines if two agents are intersecting.
     * @param agent check if this agent is intersecting with this agent.
     */
    public boolean isColliding(IAgent agent) {
        return createBoundingRect(this).intersects(createBoundingRect(agent));
    }

    private Rectangle createBoundingRect(IAgent agent) {
        int xTopLeft = (int)(agent.getX() - (agent.getWidth() / 2));
        int yTopLeft = (int)(agent.getY() + (agent.getHeight() / 2));
        return new Rectangle(xTopLeft, yTopLeft, agent.getWidth(), agent.getHeight());
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



    public int getAttackDamage() {
        return this.attackDamage;
    }
    public void setAttackDamage(int damage) {
        this.attackDamage = damage;
    }

    public int getHealth() {
        return this.playerAgent.getHealth();
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

    public boolean isDead() {return this.playerAgent.getHealth()<=0;}
}
