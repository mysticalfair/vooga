package state.Agent;

import state.Attack.IAttack;
import state.Behavior.IBehavior;

import java.awt.*;
import java.util.List;

/**
 * @Author:Luke_Truitt
 * Agent used by backend and authoring
 */
public class AgentComplete extends AgentPlayer implements IAgentComplete {

    private IBehavior behavior;
    private IAttack attack;

    public AgentComplete(Point location, String url, boolean attacking, int health) {
        super(location, url, attacking, health);
    }

    public AgentComplete(AgentPlayer player, IBehavior behavior, IAttack attack) {
        super(player.getXY(), player.getImageURL(), player.getIsAttacking(), player.getHealth());
        setBehavior(behavior);
        setAttack(attack);
    }

    public AgentComplete(Point location, String url, boolean attacking, int health, IBehavior behavior, IAttack attack) {
        super(location, url, attacking, health);
        setBehavior(behavior);
        setAttack(attack);
    }

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

    public void update(List<IAgentComplete> agents) {
        //TODO:: Implement the update method
    }

    public IAgent toImmutable() {
        return new AgentPlayer(this);
    }
}
