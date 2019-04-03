package state;

import java.util.List;

/**
 * @Author:Luke_Truitt
 * This is the version of State used by
 */
public interface IStateComplete extends IState {
    List<IAgentComplete> getMutableAgents();
}
