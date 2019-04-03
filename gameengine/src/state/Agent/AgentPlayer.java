package state.Agent;

import java.awt.*;

/**
 * @Author:Luke_Truitt
 * Class that only has the things Player needs. All are immutable, that's why the weird returns.
 */
public class AgentPlayer extends Agent {

    public AgentPlayer(AgentComplete agent) {
        this.location = agent.getXY();
        this.imageURL = agent.getImageURL();
        this.isAttacking = agent.getIsAttacking();
        this.health = agent.getHealth();
    }

    public AgentPlayer(Point location, String url, boolean attacking, int health) {
        this.location = location;
        this.imageURL = url;
        this.isAttacking = attacking;
        this.health = health;
    }

    public Point getXY() {
        return new Point(this.location);
    }

    /**
     * Not sure if these are the correct ways to return immutable versions but I tried.
     */
    public String getImageURL() {
        return "" + this.imageURL;
    }
    public Boolean getIsAttacking() {
        boolean attacking = this.isAttacking;
        return attacking;
    }
    public int getHealth() {
        int health = this.health;
        return health;
    }
}
