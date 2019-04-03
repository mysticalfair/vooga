package state;

import java.util.List;

/**
 * @Author:Luke_Truitt
 * These are the extensions for the full agent. The one authoring and engine need.
 */
public interface IAgentComplete extends IAgent{
    void setXY();
    String setImageURL();
    Boolean setIsAttacking();
    int setHealth();
    void update(List<IAgent> agents);
    IAgent toImmutable();
}
