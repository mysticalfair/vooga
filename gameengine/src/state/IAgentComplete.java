package state;

import java.util.List;

/**
 * These are the extensions for the full agent. The one authoring and engine need.
 */
public interface IEngineAgent extends IAgent{
    void setCY();
    String getImageURL();
    Boolean getIsAttacking();
    int getHealth();
    void update(List<IAgent> agents);
}
