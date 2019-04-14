package state.agent;
import java.util.List;

/**
 * @author Jorge Raad
 * @author Luke Truitt
 * @author David Miron
 * @author Jamie Palka
 * These are the extensions for the full agent. The one authoring and engine need.
 */
public interface IAgent {
    void update(List<Agent> agents) throws CloneNotSupportedException;

    /**
     * Returns the X location of the Agent.
     * @return int - X location of the agent
     */
    double getX();

    /**
     * Returns the Y location of the Agent.
     * @return int - Y location of the agent
     */
    double getY();

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


    /**
     * Updates the x and y velocity vectors of the agent
     * @param xVelocity x velocity the agent will now have
     * @param yVelocity y velocity the agent will now have
     */
    void updateVelocity(double xVelocity, double yVelocity);

    /**
     * Returns the current x velocity of the agent
     * @return xVelocity current x velocity the agent
     */
    double getXVelocity();

    /**
     * Returns the current y velocity of the agent
     * @return yVelocity current y velocity the agent
     */
    double getYVelocity();

    /**
     * Updates location of the agent
     * @param x - the new x location to give to the agent
     * @param y - the new y location to give to the agent
     */
    void setLocation(double x, double y);

    /**
     * Stops the agent.
     */
    void stop();

    /**
     * Getting and setting the damage this agent does
     * @return it's damage
     */
    int getAttackDamage();

    boolean isDead();

    String getName();

    int getHealth();
}
