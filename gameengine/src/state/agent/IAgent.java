package state.agent;

import state.action.movement.MovementAction;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * @author Jorge Raad
 * @author Luke Truitt
 * @author David Miron
 * @author Jamie Palka
 * These are the extensions for the full agent. The one authoring and engine need.
 */
public interface IAgent extends IPlayerAgent {
    void update(List<IAgent> agents);

    /**
     * Move the current agent a specified distance
     * @param x The vector representing the x location
     * @param y The vector representing the y location
     */
    void move(int x, int y);

    /**
     * Returns the location of the Agent.
     * @return Point containing location of the Agent
     */
    int[] getLocation();

    /**
     * Returns the team of the Agent.
     * @return String containing team of the Agent
     */
    String getTeam();

    /**
     * Returns the distance between this Agent and the given Agent
     * @param agent Agent to which the distance will be calculated
     * @return distance
     */
    double calculateDistance(IAgent agent);
    /**
     * Returns the width in that order
     * @return an array with first element of width
     */
    int getWidth();

    /**
     * Returns the height in that order
     * @return an array with first element of height
     */
    int getHeight();

    /**
     * Returns the direction angle of the agent.
     * @return the angle the agent is pointing to.
     */
    double getDirection();

    /**
     * Decreases the health value of the agent.
     * @param healthDeduction amount by which to deduct the health of the agent.
     */
    void loseHealth(int healthDeduction);

    /**
     * Increases the health value of the agent.
     * @param healthIncrease amount by which to increase the health of the agent.
     */
    void gainHealth(int healthIncrease);


    /**
     * Determines if two agents are intersecting.
     * @param agent check if this agent is intersecting with this agent.
     */
    boolean isColliding(IAgent agent);
}
