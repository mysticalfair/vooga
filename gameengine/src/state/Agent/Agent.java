package state.Agent;

import java.awt.*;

/**
 * @Author:Luke_Truitt
 * These are the fields shared by all agents, on both front end and back end
 */
public abstract class Agent implements IAgent {
    protected Point location;
    protected String imageURL;
    protected boolean isAttacking;
    protected int health;
}
