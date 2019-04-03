package state;

import java.util.List;

/**
 * @author David Miron
 * @author Jamie Palka
 */
public class State implements IStateAuthoring, IStatePlayer {

    @Override
    public List<IAgent> getAgents() {
        return null; // TODO
    }

    @Override
    public List<IObjective> getObjectives() {
        return null; // TODO
    }
}
