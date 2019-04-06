package state.agent;

import java.util.List;

/**
 * @Author:Luke_Truitt
 * @author David Miron
 * These are the extensions for the full agent. The one authoring and engine need.
 */
public interface IAgent {
    void update(List<IAgent> agents);
}
