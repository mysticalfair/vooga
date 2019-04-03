package state.Agent;

import state.Attack.IAttack;
import state.Behavior.IBehavior;

import java.awt.*;
import java.util.List;

/**
 * @Author:Luke_Truitt
 * Agent used by backend and authoring
 */
public class Agent implements IAgent {

    private int id;
    private Point location;
    private String imageURL;
    private boolean isAttacking;
    private int health;
    private IBehavior behavior;
    private IAttack attack;


    public Agent(Point location, String url, boolean attacking, int health, IBehavior behavior, IAttack attack) {
        this.location = location;
        this.imageURL = url;
        this.isAttacking = attacking;
        this.health = health;
        setBehavior(behavior);
        setAttack(attack);
    }

    public int getId() { return this.id; }
    public Point getXY() {
        return new Point(this.location);
    }

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

    public void setId(int id) {this.id = id;}

    public void setXY(Point location) {
        this.location = location;
    }

    public void setImageURL(String url) {
        this.imageURL = url;
    }

    public void setIsAttacking(boolean attacking) {
        this.isAttacking = attacking;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public IBehavior getBehavior() {
        return this.behavior;
    }

    public void setBehavior(IBehavior behavior) {
        this.behavior = behavior;
    }

    public IAttack getAttack() {
        return this.attack;
    }

    public void setAttack(IAttack attack) {
        this.attack = attack;
    }

    public void update(List<IAgent> agents) {
        //TODO:: Implement the update method
    }

}
