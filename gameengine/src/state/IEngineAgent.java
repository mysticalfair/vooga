package state;

import java.util.List;

public interface IEngineAgent extends IAgent{
    void update(List<IAgent> agents);
}
