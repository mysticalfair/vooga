package state.Agent;

import state.Attack.IAttack;
import state.Behavior.IBehavior;

import java.awt.*;
import java.util.List;

/**
 * @Author:Luke_Truitt
 * These are the extensions for the full agent. The one authoring and engine need.
 */
public interface IAgentComplete extends IAgent{
    void setXY(Point location);
    void setImageURL(String image);
    void setIsAttacking(boolean attacking);
    void setHealth(int health);
    IBehavior getBehavior();
    void setBehavior(IBehavior behavior);
    IAttack getAttack();
    void setAttack(IAttack attack);
    void update(List<IAgentComplete> agents);
    IAgent toImmutable();
}
