package state.agent;

import state.actiondecision.ActionDecision;
import state.action.movement.MovementAction;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.lang.reflect.*;

/**
 * @author David Miron
 * @author Luke_Truitt
 * @author Jamie Palka
 * Agent used by backend and authoring
 */
public class Agent implements IAgent, Cloneable {

    private int id;
    private Point2D.Double location;
    private String imageURL;
    private String team;
    private int health;

    protected List<ActionDecision> actionDecisions;

    /**
     * Agent constructor.
     * @param id agent ID
     * @param location initial location
     * @param team the agent's respective team
     */
    public Agent(int id, Point2D.Double location, String team, int health) {
        this.id = id;
        this.location = location;
        this.team = team;
        this.health = health;
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

    /**
     * Move the current agent a specified distance
     * @param movement The vector representing the movement
     */
    public void move(Point2D.Double movement) {
        this.location.setLocation(location.getX() + movement.getX(), location.getY() + movement.getY());
    }

    /**
     * Returns the location of the Agent.
     * @return Point containing location of the Agent
     */
    public Point2D.Double getLocation(){
        return location;
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
        return location.distance(agent.getLocation());
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



}
